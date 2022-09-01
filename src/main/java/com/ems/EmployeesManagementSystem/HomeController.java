package com.ems.EmployeesManagementSystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label greeter;

    @FXML
    protected void initialize() {
        greeter.setText("Greetings " + EmployeeManager.userName + ". Hope you have a nice day :)");//This is also useless, but it's nice to have a home;
    }
}
