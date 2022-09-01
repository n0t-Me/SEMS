package com.ems.EmployeesManagementSystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SettingsController {//idk why?
    @FXML
    private TextField hostTF, usernameTF, portTF, nameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label errSettingsLabel;

    @FXML
    protected void onAction() {
        String host = hostTF.getText();
        String username = usernameTF.getText();
        int port = Integer.parseInt(portTF.getText());
        String dbName = nameTF.getText();
        String password = passwordPF.getText();
        if (host.isBlank() || port == 0 || username.isBlank() || dbName.isBlank()) {
            errSettingsLabel.setText("Please enter valid settings");
        } else if (SQLHandler.setNewSettings(host, port, dbName, username, password)) {
            errSettingsLabel.setText("Updated Successfully");
        } else {
            errSettingsLabel.setText("Error connecting to database with given settings.\nUsing old settings.");
        }
    }

}
