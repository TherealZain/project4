package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    private static StoreOrders instance;
    private ArrayList<Order> storeOrders;
    private static int nextOrderNum;
    private static final int NOT_FOUND = -1;

    /**
     * Constructor for the StoreOrders class.
     * Initializes the store orders list and
     * sets the next available order number to 1.
     */
    private StoreOrders(){
        nextOrderNum = 1;
        storeOrders = new ArrayList<>();
    }

    public static StoreOrders getInstance() {
        if (instance == null) {
            instance = new StoreOrders();
        }
        return instance;
    }

    /**
     * Retrieves and increments the next available order number.
     * This method ensures each order gets a unique number and
     * increments the counter for the next order.
     *
     * @return The next available order number as an integer.
     */
    public int getNextOrderNum(){
        return nextOrderNum;
    }

    public ArrayList<Order> getOrders(){
        return storeOrders;
    }

    public boolean addOrder(Order order){
        if(order != null && !order.getPizzas().isEmpty()){
            storeOrders.add(order);
            nextOrderNum++;
            return true;
        }
        return false;
    }

    /**
     * exports all orders in to a test file for employee use
     * @param stage where FileChooser will open
     */
    public void export(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Orders");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for(Order order : storeOrders){
                    writer.write("Order #" + order.getOrderNum());
                    writer.newLine();
                    for(Pizza pizza : order.getPizzas()){
                        writer.write(pizza.toString());
                        writer.newLine();
                    }
                    writer.write("------------------------------------------------");
                    writer.newLine();
                }
            } catch (IOException ignored) {

            }

        }
    }
    /**
     * Checks if storeOrders contains any orders
     * @return true if no orders in storeOrders
     */
    public boolean storeOrdersEmpty(){
        return storeOrders.isEmpty();
    }

    /**
     * Removes order from arrayList
     * @param order to be removed
     * @return true if order found and can be removed
     */
    public boolean removeOrder(Order order){
        if(order != null){
            storeOrders.remove(order);
            return true;
        } else
            return false;
    }

    /**
     * Gets order by order number
     * @param id of order
     * @return order associated with ID
     */
    public Order getOrderById(int id) {
        for (Order order : storeOrders) {
            if (order.getOrderNum() == id) {
                return order;
            }
        }
        return null;
    }


}
