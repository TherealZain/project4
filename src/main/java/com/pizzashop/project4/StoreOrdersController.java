package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller class for managing store orders in a pizza ordering application.
 * This class handles the user interface related to viewing
 * and managing all orders placed in the store,
 * including displaying order details, cancelling orders,
 * and exporting order data.
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class StoreOrdersController {

    @FXML
    private ListView<String> allOrdersList;
    @FXML
    private ComboBox<String> orderSelect;
    @FXML
    private TextField orderTotal;
    @FXML
    private Button exportButton;
    private MainMenuController mainController;
    private ObservableList<String> orderItems = FXCollections.observableArrayList();
    private StoreOrders storeOrders;

    /**
     * Sets the main controller.
     *
     * @param controller The main menu controller to be used.
     */
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    /**
     * Initializes the controller, setting up the necessary objects and
     * state for managing store orders.
     * This method is called after the FXML fields have been injected.
     */
    public void initialize(){
        storeOrders = StoreOrders.getInstance();
        ObservableList<String> orderNumbers = FXCollections.observableArrayList();
        allOrdersList.setItems(orderItems);
        for(Order order : storeOrders.getOrders()){
            int orderNumber = order.getOrderNum();
            orderNumbers.add(Integer.toString(orderNumber));
        }
        orderSelect.setItems(orderNumbers);
        orderSelect.setOnAction(event -> onComboBoxAction());
    }

    /**
     * Handles actions when a selection is made in the order selection ComboBox.
     * This method updates the display based on the selected order.
     */
    private void onComboBoxAction() {
        String selectedOrder = orderSelect.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            displaySelectedOrder(selectedOrder);
        }
    }

    /**
     * Displays the selected order's details in the user interface.
     *
     * @param orderNumber The number of the order to display.
     */
    private void displaySelectedOrder(String orderNumber){
        orderItems.clear();
        Order order = storeOrders.getOrderById(Integer.parseInt(orderNumber));
        for(Pizza pizza : order.getPizzas()){
            orderItems.add(pizza.toString());
        }
        orderTotal.setText(order.getOrderTotal());
    }

    /**
     * Clears the display when no order is selected.
     */
    private void displayNone(){
        orderItems.clear();
        orderTotal.setText("0.00");
    }

    /**
     * Handles the action of the export button.
     * This method is responsible for exporting the store orders.
     */
    @FXML
    private void handleExportButton(){
        if(!storeOrders.storeOrdersEmpty()){
            storeOrders.export((Stage) exportButton.getScene().getWindow());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No orders in Store Orders");
            alert.setContentText("Try again when customers have placed orders");
            alert.showAndWait();
        }
    }

    /**
     * Handles the action of the cancel button.
     * This method is responsible for removing the
     * selected order from the store orders.
     */
    @FXML
    private void handleCancelButton() {
        String selectedOrder = orderSelect.getSelectionModel().getSelectedItem();
        int selectedIndex = orderSelect.getSelectionModel().getSelectedIndex();
        if (selectedOrder != null) {
            Order order = storeOrders.getOrderById(Integer.parseInt(selectedOrder));
            if (storeOrders.removeOrder(order)) {
                orderSelect.getItems().remove(selectedIndex);
                if (!orderSelect.getItems().isEmpty()) {
                    if (selectedIndex >= orderSelect.getItems().size()) {
                        orderSelect.getSelectionModel().selectLast();
                    } else {
                        orderSelect.getSelectionModel().select(selectedIndex);
                    }
                    onComboBoxAction();
                } else {
                    displayNone();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Order removed");
                alert.setContentText("Order # " + order.getOrderNum() + " removed.");
                alert.showAndWait();
            }
        }
    }
}
