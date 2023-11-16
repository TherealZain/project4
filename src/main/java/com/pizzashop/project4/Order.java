package com.pizzashop.project4;

import java.util.ArrayList;

public class Order {
    private int orderNum;
    private ArrayList<Pizza> pizzaOrders;

    public Order(int orderNum){
        this.orderNum = orderNum;
        pizzaOrders = new ArrayList<>();
    }
}
