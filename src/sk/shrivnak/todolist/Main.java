package sk.shrivnak.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.shrivnak.todolist.datamodel.ToDoData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("ToDoList");
        primaryStage.setScene(new Scene(root, 470, 410));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {

        try {
            ToDoData.getInstance().saveToDoItems();
        }catch (IOException e){
            e.getMessage();
        }
    }

    @Override
    public void init() throws Exception {
        try {
            ToDoData.getInstance().loadToDoItems();
        }catch (IOException e){
            e.getMessage();
        }
    }
}
