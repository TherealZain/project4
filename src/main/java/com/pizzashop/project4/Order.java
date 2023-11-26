package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;

import java.util.ArrayList;

/**
 * Represents an order in a pizza shop application.
 * This class encapsulates the details of a pizza order,
 * including the order number and a list of pizzas in the order.
 *
 * It provides methods to add pizzas to the order and retrieve order details.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class Order {
    private int orderNum;
    private ArrayList<Pizza> pizzaOrders;
    private static final double SALES_TAX_RATE = 0.06625;


    /**
     * Constructor for creating a new Order.
     * Initializes the order with a specific order number
     * and an empty list of pizzas.
     *
     * @param orderNum The unique number assigned to the order.
     */
    public Order(int orderNum){
        this.orderNum = orderNum;
        pizzaOrders = new ArrayList<>();
    }

    /**
     * Adds a Pizza to the order.
     * This method allows adding a new pizza to the
     * current list of pizzas in the order.
     *
     * @param pizza The Pizza object to be added to the order.
     */
    public void addToOrder(Pizza pizza){
        pizzaOrders.add(pizza);
    }

    /**
     * Gets the order number.
     * This method returns the unique number assigned to the order.
     *
     * @return The order number as an integer.
     */
    public int getOrderNum(){return orderNum;}
    /**
     * Gets the list of pizzas in the order.
     * This method returns an ArrayList containing all the pizzas added to the order.
     *
     * @return An ArrayList of Pizza objects.
     */
    public ArrayList<Pizza> getPizzas(){return pizzaOrders;}

    public boolean removePizza(int pizzaIndex){
        if (pizzaIndex >= 0 && pizzaIndex < pizzaOrders.size()) {
            pizzaOrders.remove(pizzaIndex);
            return true; // Successfully removed
        }
        return false; // Removal failed
    }

    public String getOrderTotal() {
        double subTotal = 0;
        for (Pizza pizza : pizzaOrders) {
            if (pizza != null) {
                subTotal += pizza.price();
            }
        }
        double salesTax = Math.round((subTotal*SALES_TAX_RATE)*100.0)/100.0;
        double totalCost = Math.round((subTotal + salesTax)*100.0)/100.0;
        return String.format("%.2f", totalCost);
    }
}
