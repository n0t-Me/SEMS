package com.ems.EmployeesManagementSystem;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SceneController {
    private final HashMap<String, Pane> paneMap = new HashMap<>();
    private final Scene main;

    public SceneController(Scene main) {
        this.main = main;
    }

    protected void addPane(String name, Pane pane) {
        paneMap.put(name, pane);
    }

    protected void removePane(String name) {
        paneMap.remove(name);
    }

    protected boolean isPaneExist(String name) {
        return paneMap.containsKey(name);
    }

    protected void activate(String name) {
        main.setRoot(paneMap.get(name));
    }
}
//Stolen from stackoverflow