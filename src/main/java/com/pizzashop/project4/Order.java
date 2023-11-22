package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;

import java.util.ArrayList;

public class Order {
    private int orderNum;
    private ArrayList<Pizza> pizzaOrders;

    public Order(int orderNum){
        this.orderNum = orderNum;
        pizzaOrders = new ArrayList<>();
    }

    public void addToOrder(Pizza pizza){
        pizzaOrders.add(pizza);
    }

    public int getOrderNum(){return orderNum;}
    public ArrayList<Pizza> getPizzas(){return pizzaOrders;}
}
