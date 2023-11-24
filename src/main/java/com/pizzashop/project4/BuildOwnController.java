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
    private ListView<String> additionalToppingsList, selectedToppingsList;
    @FXML
    private Button addButton, removeButton;
    @FXML
    private RadioButton tomatoSauce, alfredoSauce;
    @FXML
    private ToggleGroup sauceToggleGroup;
    @FXML
    private MenuButton sizeSelect;
    @FXML
    private MenuItem smallItem, mediumItem, largeItem, selectedItem;
    @FXML
    private CheckBox extraCheese, extraSauce;
    @FXML
    private MainMenuController mainController;
    @FXML
    private TextField priceDisplay;
    private Order order;
    private Pizza buildYourOwn = PizzaMaker.createPizza("BYO");
    private static final int MAX_TOPPINGS = 7;
    private static final int REQUIRED_TOPPINGS = 3;

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void initialize() {
        String price = String.format("%.2f", buildYourOwn.price());
        priceDisplay.setText(price);
        smallItem.setOnAction(event -> handleMenuItemSelect(smallItem));
        mediumItem.setOnAction(event -> handleMenuItemSelect(mediumItem));
        largeItem.setOnAction(event -> handleMenuItemSelect(largeItem));
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

    private void handleMenuItemSelect(MenuItem menuItem) {
        selectedItem = menuItem;
        sizeSelect.setText(menuItem.getText());
    }

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
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

    @FXML
    private void handleSauce(){
        String sauce = sauceToggleGroup.getSelectedToggle().toString();
        if(sauce.contains("Tomato")){
            buildYourOwn.setSauce(Sauce.TOMATO);
        }else
            buildYourOwn.setSauce(Sauce.ALFREDO);
    }

    @FXML
    public void handleExtraSelect(){
        buildYourOwn.setExtraCheese(extraCheese.isSelected());
        buildYourOwn.setExtraSauce(extraSauce.isSelected());
        handlePriceChange();
    }

    private void handlePriceChange() {
        double price = buildYourOwn.price();
        priceDisplay.setText(String.format("%.2f", price));
    }

    private void emptySelectedToppings(){
        ObservableList<String> itemsToMove = FXCollections.observableArrayList(selectedToppingsList.getItems());
        for (String item : itemsToMove) {
            selectedToppingsList.getItems().remove(item);
            additionalToppingsList.getItems().add(item);
        }
    }

    @FXML
    private void handleAddToOrder(){
        if(selectedToppingsList.getItems().size() < REQUIRED_TOPPINGS){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Pizza must have at least 3 toppings");
            alert.setContentText("Must have 3 or more toppings");
            alert.showAndWait();
            return;
        }
        mainController.addPizza(buildYourOwn);
        buildYourOwn = PizzaMaker.createPizza("BYO");
        sauceToggleGroup.selectToggle(tomatoSauce);
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);
        sizeSelect.setText(smallItem.getText());
        emptySelectedToppings();
        handlePriceChange();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Order added to Cart");
        alert.setContentText("Order successfully added");
        alert.showAndWait();
    }

    private void handleSizeChange(Size newSize) {
        buildYourOwn.setSize(newSize);
        handlePriceChange();
    }

}
