package com.pizzashop.project4;


import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CurrentOrderController {

    @FXML
    private ListView<String> pizzaList;
    @FXML
    private TextField orderNumField,subTotalField, salesTaxField, orderTotalField;
    private Order order;
    private static final double SALES_TAX_RATE = 0.06625;
    private MainMenuController mainController = new MainMenuController();


    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void initialize(){

    }
    public void updateOrderDisplay() {
        ObservableList<String> orderItems = FXCollections.observableArrayList();
        double subTotal = 0;

        if (order != null) {
            ArrayList<Pizza> pizzaItems =  order.getPizzas();
            for(Pizza pizza : pizzaItems){
                orderItems.add(pizza.toString());
                subTotal+= pizza.price();
            }
            pizzaList.setItems(orderItems);
        }
        double salesTax = Math.round((subTotal*SALES_TAX_RATE)*100.0)/100.0;
        double totalCost = Math.round((subTotal + salesTax)*100.0)/100.0;
        String orderNumString = Integer.toString(order.getOrderNum());
        String subTotalString = Double.toString(subTotal);
        String salesTaxString = Double.toString(salesTax);
        String totalString = Double.toString(totalCost);
        orderNumField.setText(orderNumString);
        subTotalField.setText(subTotalString);
        salesTaxField.setText(salesTaxString);
        orderTotalField.setText(totalString);
    }


}
