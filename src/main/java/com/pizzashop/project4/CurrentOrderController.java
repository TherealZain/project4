package com.pizzashop.project4;


import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;

/**
 * Controller class for managing the current order in a pizza ordering application.
 * This class is responsible for handling the user interface related to the current pizza order,
 * including displaying order details, removing pizzas from the order, and placing the order.
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class CurrentOrderController {

    @FXML
    private ListView<String> pizzaList;
    @FXML
    private TextField orderNumField,subTotalField, salesTaxField, orderTotalField;
    private Order order;
    private StoreOrders storeOrders;
    private static final double SALES_TAX_RATE = 0.06625;
    private MainMenuController mainController = new MainMenuController();

    /**
     * Initializes the controller, setting up the necessary objects and state.
     * This method is called after the FXML fields have been injected.
     */
    public void initialize() {
        storeOrders = StoreOrders.getInstance();
    }

    /**
     * Sets the main controller.
     *
     * @param controller The main menu controller to be used.
     */
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    /**
     * Sets the current order.
     *
     * @param order The order object to be managed by this controller.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Updates the display of the current order.
     * This includes displaying the pizzas in the order and calculating the total costs.
     */
    public void updateOrderDisplay() {
        ObservableList<String> orderItems = FXCollections.observableArrayList();
        if (order != null) {
            ArrayList<Pizza> pizzaItems =  order.getPizzas();
            for(Pizza pizza : pizzaItems){
                if(pizza != null) {
                    orderItems.add(pizza.toString());
                }
            }
            pizzaList.setItems(orderItems);
        }
        calculateTotals(order.getPizzas());
    }

    /**
     * Handles the event of removing a pizza from the current order.
     * This is typically triggered by a user action in the UI.
     */
    @FXML
    public void handleRemovePizza(){
        int selectedIndex = pizzaList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            boolean removed = order.removePizza(selectedIndex);
            if (removed) {
                pizzaList.getItems().remove(selectedIndex);
                calculateTotals(order.getPizzas());
            }
    }
    }

    /**
     * Handles placing the current order.
     * This method attempts to add the order to the
     * store orders and shows an alert based on the success of the operation.
     */
    @FXML
    public void handlePlaceOrder(){
        if(storeOrders.addOrder(order)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText("Your order number is " + order.getOrderNum());
            alert.setContentText("Enjoy");
            alert.showAndWait();
            order = mainController.createNewOrder();
            updateOrderDisplay();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Trouble placing order.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    /**
     * Calculates the subtotal, sales tax, and total cost
     * of the current order and updates the UI fields accordingly.
     *
     * @param pizzaItems The list of pizzas in the current order.
     */
    public void calculateTotals(ArrayList<Pizza> pizzaItems){
        double subTotal = 0;
        for(Pizza pizza : pizzaItems){
            if(pizza != null) {
                subTotal += pizza.price();
            }
        }
        double salesTax = Math.round((subTotal*SALES_TAX_RATE)*100.0)/100.0;
        double totalCost = Math.round((subTotal + salesTax)*100.0)/100.0;
        String orderNumString = Integer.toString(order.getOrderNum());
        String subTotalString = String.format("%.2f", subTotal);
        String salesTaxString = String.format("%.2f", salesTax);
        String totalString = String.format("%.2f", totalCost);
        orderNumField.setText(orderNumString);
        subTotalField.setText("$" + subTotalString);
        salesTaxField.setText("$" + salesTaxString);
        orderTotalField.setText("$" + totalString);
    }


}
