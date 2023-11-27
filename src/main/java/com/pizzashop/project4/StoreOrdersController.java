package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

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

    private void onComboBoxAction() {
        String selectedOrder = orderSelect.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            // Code to handle the selection of an order
            displaySelectedOrder(selectedOrder);
        }
    }

    private void displaySelectedOrder(String orderNumber){
        orderItems.clear();
        Order order = storeOrders.getOrderById(Integer.parseInt(orderNumber));
        for(Pizza pizza : order.getPizzas()){
            orderItems.add(pizza.toString());
        }
        orderTotal.setText(order.getOrderTotal());
    }

    private void displayNone(){
        orderItems.clear();
        orderTotal.setText("0.00");
    }

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
    @FXML
    private void handleCancelButton(){
        String selectedOrder = orderSelect.getSelectionModel().getSelectedItem();
        int selectedIndex = orderSelect.getSelectionModel().getSelectedIndex();
        Order order = storeOrders.getOrderById(Integer.parseInt(selectedOrder));
       if(storeOrders.removeOrder(order)){
           orderSelect.getItems().remove(selectedIndex);
           displayNone();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Success");
           alert.setHeaderText("Order removed");
           alert.setContentText("Order # " + order.getOrderNum() + " removed.");
           alert.showAndWait();
       }
    }
}
