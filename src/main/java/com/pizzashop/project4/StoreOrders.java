package com.pizzashop.project4;

import java.util.ArrayList;

/**
 * Manages the collection of orders for a pizza store.
 * This class is responsible for maintaining a list of
 * all orders placed in the store and generating unique order numbers.
 *
 * It provides methods to get the next available order number and
 * keeps track of all orders placed.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class StoreOrders {
    private ArrayList<Order> storeOrders;
    private static int nextOrderNum;

    /**
     * Constructor for the StoreOrders class.
     * Initializes the store orders list and
     * sets the next available order number to 1.
     */
    public StoreOrders(){
        nextOrderNum = 1;
        storeOrders = new ArrayList<>();
    }

    /**
     * Retrieves and increments the next available order number.
     * This method ensures each order gets a unique number and
     * increments the counter for the next order.
     *
     * @return The next available order number as an integer.
     */
    public int getNextOrderNum(){
        int currOrderNum = nextOrderNum;
        nextOrderNum++;
        return currOrderNum;
    }

}
