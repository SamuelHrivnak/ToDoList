package sk.shrivnak.todolist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sk.shrivnak.todolist.datamodel.ToDoData;
import sk.shrivnak.todolist.datamodel.ToDoItem;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {
    @FXML
    private ListView<ToDoItem> listView ;
    @FXML
    private TextArea textArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private AnchorPane mainAnchorPane;

    private List<ToDoItem> toDoItems;

    public void initialize(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
                if (newValue != null){
                    ToDoItem item = listView.getSelectionModel().getSelectedItem();
                    textArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
                    deadlineLabel.setText(df.format(item.getDeadLine()));
                }
            }
        });

        listView.getItems().setAll(ToDoData.getInstance().getToDoItems());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectFirst();

    }

    @FXML
    public void showDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());

        try {
            Parent root = FXMLLoader.load(getClass().getResource("toDoItemDialog.fxml"));
            dialog.getDialogPane().setContent(root);

        }catch (IOException e){
            System.out.println("Dialog Failed exception");
            e.printStackTrace();
            return;
        }


    }

}
