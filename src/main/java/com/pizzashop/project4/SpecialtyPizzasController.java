package com.pizzashop.project4;

import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SpecialtyPizzasController {

    @FXML
    private ComboBox<String> specialtyPizzaSelect;
    @FXML
    private ToggleGroup sizeToggleGroup;
    @FXML
    private RadioButton smallSize, mediumSize, largeSize;
    @FXML
    private CheckBox extraCheese, extraSauce;
    @FXML
    private TextField priceDisplay;
    private Pizza specialtyPizza;
    private MainMenuController mainController;
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }


    public void initialize() {
        specialtyPizzaSelect.setItems(FXCollections.observableArrayList("Deluxe", "Supreme", "Meat Lovers", "Pepperoni", "Seafood"));
        specialtyPizzaSelect.setOnAction(event -> handleSpecialtySelect());

        sizeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> handleSizeChange());

        extraCheese.setOnAction(event -> handlePriceChange());
        extraSauce.setOnAction(event -> handlePriceChange());

        // Default to the first specialty pizza
        specialtyPizzaSelect.getSelectionModel().selectFirst();
        handleSpecialtySelect();
    }

    private void handleSpecialtySelect() {
        String selected = specialtyPizzaSelect.getSelectionModel().getSelectedItem();
        specialtyPizza = PizzaMaker.createPizza(selected);
        updateToppingsDisplay();
        handlePriceChange();
    }

    private void updateToppingsDisplay() {
        // Update the display of toppings based on the selected specialty pizza
    }

    private void handleSizeChange() {
        RadioButton selectedSize = (RadioButton) sizeToggleGroup.getSelectedToggle();
        specialtyPizza.setSize(Size.valueOf(selectedSize.getText().toUpperCase()));
        handlePriceChange();
    }

    private void handlePriceChange() {
        double price = specialtyPizza.price();
        if (extraCheese.isSelected()) price += 1.0;
        if (extraSauce.isSelected()) price += 1.0;
        priceDisplay.setText(String.format("%.2f", price));
    }

    @FXML
    private void handleAddToOrder() {
        // Handle adding the configured specialty pizza to the order
        mainController.addPizza(specialtyPizza);
        // Show a confirmation alert
    }
}
