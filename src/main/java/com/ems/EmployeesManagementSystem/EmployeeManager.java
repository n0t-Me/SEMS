package com.ems.EmployeesManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeManager extends Application {
    public static SceneController sceneController;
    public static String userName;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginUIFXML = new FXMLLoader(EmployeeManager.class.getResource("login-ui.fxml"));//This is useless no security added. But I'm bored sooo :)
        Scene scene = new Scene(loginUIFXML.load(), 1600, 800);
        sceneController = new SceneController(scene);
        stage.setTitle("Employee Manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}