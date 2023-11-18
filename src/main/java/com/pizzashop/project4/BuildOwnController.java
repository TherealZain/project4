package com.pizzashop.project4;

import com.pizzashop.project4.enums.Toppings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class BuildOwnController {
    @FXML
    private ListView<String> toppingsList;
    private MainMenuController mainController;

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void initialize() {
        // Create and populate the list with items
        toppingsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> items = FXCollections.observableArrayList();
        for(Toppings value : Toppings.values()){
            items.add(value.name());
        }
        toppingsList.setItems(items);

    }
}
