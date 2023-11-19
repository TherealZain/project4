package com.pizzashop.project4;

import com.pizzashop.project4.enums.Toppings;
import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BuildOwnController {
    @FXML
    private ListView<String> toppingsList;
   // private MultipleSelectionModel<String> selectionModel = toppingsList.getSelectionModel();
    @FXML
    private MenuButton sizeSelect;
    @FXML
    private CheckBox extraCheese;
    @FXML
    private CheckBox extraSauce;
    @FXML
    private MainMenuController mainController;
    @FXML
    private TextArea priceDisplay;
    private Pizza buildYourOwn = PizzaMaker.createPizza("BYO");

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void initialize() {
        // Create and populate the list with items
        toppingsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String price = String.valueOf(buildYourOwn.price());
        priceDisplay.setText(price);

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Toppings value : Toppings.values()) {
            items.add(value.name());
        }
        toppingsList.setItems(items);

    }


    public void handleToppings() {
        ObservableList<String> selectedItems = toppingsList.getSelectionModel().getSelectedItems();

        selectedItems.addListener((ListChangeListener<String>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    handlePriceChange();
                }
            }
        });
    }

    private void handlePriceChange() {
        String price = String.valueOf(buildYourOwn.price());
        priceDisplay.setText(price);
    }
}
