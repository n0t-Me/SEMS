package com.ems.EmployeesManagementSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class NavigationController {
    @FXML
    protected void onNavBtnClick(ActionEvent evt) throws IOException {
        Button selectedBtnNav = (Button) evt.getSource();
        String selectedNav = selectedBtnNav.getText().toLowerCase();
        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeManager.class.getResource(selectedNav.toLowerCase() + ".fxml"));
        EmployeeManager.sceneController.addPane(selectedNav, fxmlLoader.load());
        EmployeeManager.sceneController.activate(selectedNav);
    }
}
