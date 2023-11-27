package com.pizzashop.project4;

import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import com.pizzashop.project4.pizzas.Pizza;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class for the 'Specialty Pizzas' interface
 * This class handles user interactions for selecting between 5 preset
 * specialty pizzas: Deluxe, Meatzza, Pepperoni, Seafood, and Supreme.
 * It provides methods to select size, add extra cheese/sauce,
 * and manage the pizza order.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public class SpecialtyPizzasController {

    @FXML
    private ImageView specialtyPizzasImage;
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
    private MainMenuController mainController;
    private Order order;
    private Pizza specialtyPizza = PizzaMaker.createPizza("Deluxe");

    /**
     * Sets the main menu controller for this controller.
     *
     * @param controller The main menu controller to be set.
     */
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    /**
     * Sets the order associated with this pizza selection session.
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
        specialtyPizzaSelect.setItems(FXCollections.observableArrayList("Deluxe", "Supreme", "Meatzza", "Pepperoni", "Seafood"));
        specialtyPizzaSelect.setOnAction(event -> handleSpecialtySelect());
        sizeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> handleSizeChange());
        extraCheese.setOnAction(event -> handleExtraSelect());
        extraSauce.setOnAction(event -> handleExtraSelect());
        smallSize.setSelected(true);
        specialtyPizzaSelect.getSelectionModel().selectFirst();
        Platform.runLater(() -> {
            specialtyPizzaSelect.getSelectionModel().select("Deluxe");
            handleSpecialtySelect();
        });
    }

    /**
     * Converts a string to capitalized format.
     * This utility method capitalizes the first letter of the input string.
     *
     * @param input The string to be capitalized.
     * @return The capitalized string.
     */
    private String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Handles the action of selecting one of the 5 specialty pizzas.
     * The method is triggered when one of the ComboBox's options are selecte.
     * It updates the toppings, sauce, price, image, size, and extras accordingly.
     */
    private void handleSpecialtySelect() {
        String selected = specialtyPizzaSelect.getSelectionModel().getSelectedItem();
        specialtyPizza = PizzaMaker.createPizza(selected);
        updateToppingsDisplay(specialtyPizza);
        updateSauceDisplay(specialtyPizza);
        handlePriceChange();
        updatePizzaImage(selected);
        handleSizeChange();
        sizeToggleGroup.selectToggle(smallSize);
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);

    }

    /**
     * Updates the image of the specialty pizza chosen.
     * This method causes the FXML interface to display the proper image
     * corresponding to the specific specialty pizza that the user selects.
     *
     * @param pizzaType as String
     */
    @FXML
    private void updatePizzaImage(String pizzaType) {
        String imagePath = switch (pizzaType) {
            case "Deluxe" -> "/images/deluxe_pizza.png";
            case "Supreme" -> "/images/supreme_pizza.png";
            case "Meatzza" -> "/images/meatzza_pizza.png";
            case "Pepperoni" -> "/images/pepperoni_pizza.jpeg";
            case "Seafood" -> "/images/seafood_pizza.png";
            default -> null;
        };

        if (imagePath != null) {
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                specialtyPizzasImage.setImage(image);
            } catch (Exception ignored) {

            }
        }
    }

    /**
     * Updates the toppings that are displayed, corresponding to the
     * specific specialty pizza.
     * This method changes the toppings displayed when a new specialty
     * pizza is selected.
     *
     * @param pizza as Pizza object
     */
    private void updateToppingsDisplay(Pizza pizza) {
        ObservableList<String> toppings = FXCollections.observableArrayList();
        for (Toppings value : pizza.getToppings()) {
            toppings.add(capitalize(value.name().toLowerCase().replace('_', ' ')));
        }
        toppingsList.setItems(toppings);
    }

    /**
     * Updates the sauce that is displayed, corresponding to the specific
     * specialty pizza.
     * This method changes the sauce displayed when a new specialty
     * pizza is selected.
     *
     * @param pizza as Pizza object
     */
    private void updateSauceDisplay(Pizza pizza) {
        sauceDisplay.setText(capitalize(pizza.getSauce().toString().toLowerCase()));
    }

    /**
     * Handles changes in pizza size.
     * This method updates the pizza size and recalculates the price
     * when a different size is selected.
     */
    private void handleSizeChange() {
        RadioButton selectedSize = (RadioButton) sizeToggleGroup.getSelectedToggle();
        specialtyPizza.setSize(Size.valueOf(selectedSize.getText().toUpperCase()));
        handlePriceChange();
    }

    /**
     * Updates the price display based on the current configuration of the pizza.
     * This method recalculates and updates the price display whenever changes
     * are made to the pizza.
     */
    private void handlePriceChange() {
        double price = specialtyPizza.price();
        priceDisplay.setText(String.format("%.2f", price));
    }

    /**
     * Handles the selection of extra cheese or extra sauce.
     * This method is triggered when the extra cheese or extra sauce
     * checkboxes are toggled.
     */
    public void handleExtraSelect() {
        specialtyPizza.setExtraCheese(extraCheese.isSelected());
        specialtyPizza.setExtraSauce(extraSauce.isSelected());
        handlePriceChange();
    }

    /**
     * Handles the selection of extra cheese or extra sauce for adding to the user's order.
     * This method is called when the "Add to Order" button is clicked, and it applies the
     * corresponding price changes to the 'Pizza' instance to add the pizza to the order.
     *
     * @param pizza as Pizza object
     */
    public void handleExtraSelectForOrder(Pizza pizza){
        pizza.setExtraCheese(extraCheese.isSelected());
        pizza.setExtraSauce(extraSauce.isSelected());
        handlePriceChange();
    }

    /**
     * Adds the configured pizza to the order.
     * This method is called when the user finalizes their pizza
     * selection/customization and adds it to their order.
     */
    @FXML
    private void handleAddToOrder() {
        Pizza testPizza = PizzaMaker.createPizza(specialtyPizzaSelect.getSelectionModel().getSelectedItem());
        RadioButton selectedSize = (RadioButton) sizeToggleGroup.getSelectedToggle();
        handleExtraSelectForOrder(testPizza);
        testPizza.setSize(Size.valueOf((selectedSize.getText().toUpperCase())));
        order.addToOrder(testPizza);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Order added to Cart");
        alert.setContentText("Order successfully added");
        alert.showAndWait();
        resetOrderUI();
    }

    /**
     * Resets the UI of the 'Specialty Pizzas' interface to the default selections.
     * This method is called when the user clicks "Add to Order" so that they
     * can continue with customizing a new pizza for their order.
     */
    private void resetOrderUI() {
        sizeToggleGroup.selectToggle(smallSize);
        smallSize.setSelected(true);
        extraCheese.setSelected(false);
        extraSauce.setSelected(false);
        specialtyPizzaSelect.getSelectionModel().selectFirst();
        handleSpecialtySelect();
    }
}
