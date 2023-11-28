package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CurrentOrderController {

    @FXML
    private ListView<String> pizzaList;
    @FXML
    private TextField orderNumField,subTotalField, salesTaxField, orderTotalField;
    private Order order;
    private StoreOrders storeOrders;
    private static final double SALES_TAX_RATE = 0.06625;
    private MainMenuController mainController = new MainMenuController();

    public void initialize() {
        storeOrders = StoreOrders.getInstance();
    }

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
