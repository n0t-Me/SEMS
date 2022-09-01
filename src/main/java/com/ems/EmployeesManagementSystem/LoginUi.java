package com.ems.EmployeesManagementSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;


public class LoginUi {//The useless Login
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label errLabel;

    @FXML
    protected void onAction() throws IOException {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String storedHash = "$2a$12$WE3oYxhaXkTk8Y2UTE56QeLC2aloCVWutVPVgeodessun0lNIJ6k."; //P@ssw0rd

        if (username.equals("admin") && BCrypt.checkpw(password, storedHash)) {
            EmployeeManager.userName = username;
            FXMLLoader employeeManagerHomeFXML = new FXMLLoader(EmployeeManager.class.getResource("home.fxml"));
            EmployeeManager.sceneController.addPane("home", employeeManagerHomeFXML.load());
            EmployeeManager.sceneController.activate("home");
        } else if (username.equals("")) {
            errLabel.setText("Please enter a username");
        } else if (password.equals("")) {
            errLabel.setText("Please enter a password");
        } else {
            errLabel.setText("Invalid Creds");
        }
    }
}
