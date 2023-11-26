package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class StoreOrdersController {

    @FXML
    private ListView<String> allOrdersList;
    @FXML
    private MenuButton orderSelect;
    @FXML
    private TextField orderTotal;
    private MainMenuController mainController;
    private StoreOrders storeOrders;
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void initialize(){
        storeOrders = StoreOrders.getInstance();

        for(Order order : storeOrders.getOrders()){
            int orderNumber = order.getOrderNum();
           MenuItem item = new MenuItem(Integer.toString(orderNumber));
           item.setOnAction(event -> {
               displaySelectedOrder(order);
           });
           orderSelect.getItems().add(item);
        }

    }

    private void displaySelectedOrder(Order order){
        ObservableList<String> orderItems = FXCollections.observableArrayList();
        for(Pizza pizza : order.getPizzas()){
            orderItems.add(pizza.toString());
        }
        allOrdersList.setItems(orderItems);
        orderTotal.setText(order.getOrderTotal());
    }
}
