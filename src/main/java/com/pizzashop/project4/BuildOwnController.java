package com.pizzashop.project4;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

public class BuildOwnController {
    @FXML
    private ListView<String> additionalToppingsList;
    @FXML
    private ListView<String> selectedToppingsList;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private ToggleGroup sauceToggleGroup;
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
    private TextField priceDisplay;
    private Pizza buildYourOwn = PizzaMaker.createPizza("BYO");
    private static final int MAX_TOPPINGS = 7;

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void initialize() {
        String price = String.format("%.2f", buildYourOwn.price());
        priceDisplay.setText(price);

        ObservableList<String> additionalItems = FXCollections.observableArrayList();
        for (Toppings value : Toppings.values()) {
            additionalItems.add(capitalize(value.name().toLowerCase().replace('_', ' ')));
        }
        additionalToppingsList.setItems(additionalItems);
        selectedToppingsList.setItems(FXCollections.observableArrayList());

        extraCheese.setOnAction(event -> handlePriceChange());
        extraSauce.setOnAction(event -> handlePriceChange());

        sauceToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String selectedSauce = selectedRadioButton.getText();

                if ("Tomato Sauce".equals(selectedSauce)) {
                    buildYourOwn.setSauce(Sauce.TOMATO);
                } else if ("Alfredo Sauce".equals(selectedSauce)) {
                    buildYourOwn.setSauce(Sauce.ALFREDO);
                }

                handlePriceChange();
            }
        });

        for (MenuItem item : sizeSelect.getItems()) {
            item.setOnAction(event -> {
                sizeSelect.setText(item.getText());
                handleSizeChange(Size.valueOf(item.getText().toUpperCase()));
            });
        }

        addButton.setOnAction(event -> handleAddTopping());
        removeButton.setOnAction(event -> handleRemoveTopping());
    }

    private String capitalize(String input) {
        char[] chars = input.toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other characters here if needed
                found = false;
            }
        }
        return String.valueOf(chars);
    }



    @FXML
    private void handleAddTopping() {
        String selected = additionalToppingsList.getSelectionModel().getSelectedItem();
        if(selectedToppingsList.getItems().size() == MAX_TOPPINGS){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot add more toppings");
            alert.setContentText("Maximum of 7 toppings");
            alert.showAndWait();
        }
        else if (selected != null) {
            additionalToppingsList.getItems().remove(selected);
            selectedToppingsList.getItems().add(selected);
            String enumString = selected.replace(" ", "").toUpperCase();
            Toppings topping = Toppings.valueOf(enumString);
            buildYourOwn.addToppings(topping);
            handlePriceChange();
        }
    }

    @FXML
    private void handleRemoveTopping() {
        String selected = selectedToppingsList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedToppingsList.getItems().remove(selected);
            additionalToppingsList.getItems().add(selected);
            String enumString = selected.replace(" ", "").toUpperCase();
            Toppings topping = Toppings.valueOf(enumString);
            buildYourOwn.removeToppings(topping);
            handlePriceChange();
        }
    }

    private void handlePriceChange() {
        double price = buildYourOwn.price();
        if (extraCheese.isSelected()) {
            price += 1.0;
        }

        if (extraSauce.isSelected()) {
            price += 1.0;
        }


        priceDisplay.setText(String.format("%.2f", price));
    }

    private void handleSizeChange(Size newSize) {
        buildYourOwn.setSize(newSize);
        handlePriceChange();
    }

}
