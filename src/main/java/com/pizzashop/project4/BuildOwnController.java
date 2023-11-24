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

/**
 * Controller class for the 'Build Your Own Pizza' interface
 * This class handles user interactions for customizing a pizza
 * with various toppings, sauces, sizes, and extra options.
 * It provides methods to add and remove toppings, select sauce and size,
 * and manage the pizza order.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
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

    /**
     * Sets the main menu controller for this controller.
     *
     * @param controller The main menu controller to be set.
     */
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    /**
     * Sets the order associated with this pizza building session.
     *
     * @param order The order to be set.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Initializes the controller, setting up the user interface and default values.
     * This method configures the initial settings of the components and sets up event handlers.
     */
    public void initialize() {
        setUpUI();
        for (MenuItem item : sizeSelect.getItems()) {
            item.setOnAction(event -> {
                sizeSelect.setText(item.getText());
                handleSizeChange(Size.valueOf(item.getText().toUpperCase()));
            });
        }
        addButton.setOnAction(event -> handleAddTopping());
        removeButton.setOnAction(event -> handleRemoveTopping());
    }

    /**
     * Sets up the initial user interface components and their respective event handlers.
     * This method initializes the lists for toppings, configures actions for menu items, and sets up listeners for sauce selection and other changes.
     */
    private void setUpUI(){
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
    }

    /**
     * Handles the selection of a size from the size menu.
     * When a menu item for size is selected, this method
     * updates the displayed size on the menu button.
     *
     * @param menuItem The menu item representing the selected size.
     */
    private void handleMenuItemSelect(MenuItem menuItem) {
        selectedItem = menuItem;
        sizeSelect.setText(menuItem.getText());
    }


    /**
     * Converts a string to capitalized format.
     * This utility method capitalizes the first letter of the input string.
     *
     * @param input The string to be capitalized.
     * @return The capitalized string.
     */
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Handles the action of adding a topping to the pizza.
     * This method is triggered when the 'Add' button is clicked. I
     * t updates the pizza and price accordingly.
     */
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

    /**
     * Handles the action of removing a topping from the pizza.
     * This method is triggered when the 'Remove' button is clicked.
     * It updates the pizza and price accordingly.
     */
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


    /**
     * Handles the selection of sauce type for the pizza.
     * This method is triggered when a sauce option is selected
     * and updates the pizza's sauce accordingly.
     */
    @FXML
    private void handleSauce(){
        String sauce = sauceToggleGroup.getSelectedToggle().toString();
        if(sauce.contains("Tomato")){
            buildYourOwn.setSauce(Sauce.TOMATO);
        }else
            buildYourOwn.setSauce(Sauce.ALFREDO);
    }

    /**
     * Handles the selection of extra cheese or extra sauce.
     * This method is triggered when the extra cheese or extra sauce
     * checkboxes are toggled.
     */
    @FXML
    public void handleExtraSelect(){
        buildYourOwn.setExtraCheese(extraCheese.isSelected());
        buildYourOwn.setExtraSauce(extraSauce.isSelected());
        handlePriceChange();
    }

    /**
     * Updates the price display based on the current configuration of the pizza.
     * This method recalculates and updates the price display whenever changes
     * are made to the pizza.
     */
    private void handlePriceChange() {
        double price = buildYourOwn.price();
        priceDisplay.setText(String.format("%.2f", price));
    }

    /**
     * Clears the selected toppings list.
     * This method moves all items from the
     * selected toppings list back to the additional toppings list.
     */
    private void emptySelectedToppings(){
        ObservableList<String> itemsToMove = FXCollections.observableArrayList(selectedToppingsList.getItems());
        for (String item : itemsToMove) {
            selectedToppingsList.getItems().remove(item);
            additionalToppingsList.getItems().add(item);
        }
    }

    /**
     * Adds the configured pizza to the order.
     * This method is called when the user finalizes
     * their pizza customization and adds it to their order.
     */
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

    /**
     * Handles changes in pizza size.
     * This method updates the pizza size and recalculates the price when a different size is selected.
     *
     * @param newSize The new size selected for the pizza.
     */
    private void handleSizeChange(Size newSize) {
        buildYourOwn.setSize(newSize);
        handlePriceChange();
    }

}
