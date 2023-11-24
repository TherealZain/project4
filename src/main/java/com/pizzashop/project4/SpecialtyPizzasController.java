package com.pizzashop.project4;

import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    private ListView<String> toppingsList;
    @FXML
    private TextField sauceDisplay;
    Order order;
    private Pizza specialtyPizza;
    private MainMenuController mainController;
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void initialize() {
        specialtyPizzaSelect.setItems(FXCollections.observableArrayList("Deluxe", "Supreme", "Meatzza", "Pepperoni", "Seafood"));
        specialtyPizzaSelect.setOnAction(event -> handleSpecialtySelect());

        sizeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> handleSizeChange());
        smallSize.setSelected(true);

        extraCheese.setOnAction(event -> handlePriceChange());
        extraSauce.setOnAction(event -> handlePriceChange());

        specialtyPizzaSelect.getSelectionModel().selectFirst();
        handleSpecialtySelect();
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private void handleSpecialtySelect() {
        String selected = specialtyPizzaSelect.getSelectionModel().getSelectedItem();
        specialtyPizza = PizzaMaker.createPizza(selected);
        updateToppingsDisplay(specialtyPizza);
        updateSauceDisplay(specialtyPizza);
        handleSizeChange();
    }

    private void updateToppingsDisplay(Pizza pizza) {
        ObservableList<String> toppings = FXCollections.observableArrayList();
        for (Toppings value : pizza.getToppings()) {
            toppings.add(capitalize(value.name().toLowerCase().replace('_', ' ')));
        }
        toppingsList.setItems(toppings);
    }

    private void updateSauceDisplay(Pizza pizza) {
        sauceDisplay.setText(capitalize(pizza.getSauce().toString().toLowerCase()));
    }

    private void handleSizeChange() {
        RadioButton selectedSize = (RadioButton) sizeToggleGroup.getSelectedToggle();
        specialtyPizza.setSize(Size.valueOf(selectedSize.getText().toUpperCase()));
        handlePriceChange();
    }

    private void handlePriceChange() {
        double price = specialtyPizza.price();
        priceDisplay.setText(String.format("%.2f", price));
    }

    @FXML
    public void handleExtraSelect(){
        specialtyPizza.setExtraCheese(extraCheese.isSelected());
        specialtyPizza.setExtraSauce(extraSauce.isSelected());
        handlePriceChange();
    }

    @FXML
    private void handleAddToOrder() {
        mainController.addPizza(specialtyPizza);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Order added to Cart");
        alert.setContentText("Order successfully added");
        alert.showAndWait();

        resetOrderUI();
    }

    private void resetOrderUI() {
        sizeToggleGroup.selectToggle(smallSize);
        smallSize.setSelected(true);

        extraCheese.setSelected(false);
        extraSauce.setSelected(false);

        specialtyPizzaSelect.getSelectionModel().selectFirst();

        handleSpecialtySelect();
    }
}
