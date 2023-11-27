package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Controller class for the main menu in a pizza ordering application.
 * This class manages the main user interface and interactions, allowing
 * users to navigate through different stages of pizza ordering.
 * It includes functionalities to display various types of pizza customization
 * and ordering screens, and manage the current and store orders.
 *
 * Methods in this class handle the creation and display of different stages
 * for building your own pizza, viewing store orders, viewing the current order,
 * and selecting specialty pizzas.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class MainMenuController{
    private StoreOrders storeOrders = StoreOrders.getInstance();
    private Order order = new Order(storeOrders.getNextOrderNum());
    private BuildOwnController buildOwnController;
    private CurrentOrderController currentOrderController;
    private SpecialtyPizzasController specialtyPizzasController;


    /**
     * Displays the interface for building a custom pizza.
     * It loads the "build-own.fxml" and sets up the necessary controllers and scene.
     * In case of an error, it displays an alert with the error message.
     */
    @FXML
    protected void displayBuildOwn() {
        Stage buildOwn = new Stage();
        buildOwn.setResizable(false);
        AnchorPane root;
        buildOwn.setTitle("Customize Your Pizza");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("build-own.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 600);
            buildOwn.setScene(scene);
            buildOwn.show();
            buildOwnController = loader.getController();
            buildOwnController.setMainController(this);
            buildOwnController.setOrder(order);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Displays the store orders interface.
     * It loads the "store-orders.fxml" and sets up the necessary controllers and scene.
     * In case of an error, it displays an alert with the error message.
     */
    @FXML
    protected void displayStoreOrders() {
        Stage storeOrders = new Stage();
        storeOrders.setResizable(false);
        AnchorPane root;
        storeOrders.setTitle("Store Orders");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store-orders.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            storeOrders.setScene(scene);
            storeOrders.show();
            StoreOrdersController storeOrdersController = loader.getController();
            storeOrdersController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading store-orders.fxml.");
            alert.setContentText("Couldn't load store-orders.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Displays the current order interface.
     * It loads the "current-order.fxml" and sets up the necessary controllers and scene.
     * In case of an error, it displays an alert with the error message.
     */
    @FXML
    protected void displayCurrentOrder() {
        Stage currentOrder = new Stage();
        currentOrder.setResizable(false);
        AnchorPane root;
        currentOrder.setTitle("Current Order");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order.fxml"));
           root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 650, 415);
            currentOrder.setScene(scene);
            currentOrder.show();
            currentOrderController = loader.getController();
            currentOrderController.setMainController(this);
            System.out.println(order);
            currentOrderController.setOrder(order);
            currentOrderController.updateOrderDisplay();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Displays the specialty pizzas interface.
     * It loads the "specialty-pizzas.fxml" and sets up the necessary controllers and scene.
     * In case of an error, it displays an alert with the error message.
     */
    @FXML
    protected void displaySpecialtyPizzas() {
        Stage specialtyPizzas = new Stage();
        specialtyPizzas.setResizable(false);
        AnchorPane root;
        specialtyPizzas.setTitle("Specialty Pizzas");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("specialty-pizzas.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 600);
            specialtyPizzas.setScene(scene);
            specialtyPizzas.show();
            specialtyPizzasController = loader.getController();
            specialtyPizzasController.setOrder(order);
            specialtyPizzasController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }


    /**
     * Creates a new order with a unique order number.
     * This method initializes a new order instance and is typically called
     * when starting a new pizza order.
     *
     * @return the new instance of order
     */
    public Order createNewOrder(){
        order = new Order(storeOrders.getNextOrderNum());
        specialtyPizzasController.setOrder(order);
        currentOrderController.setOrder(order);
        buildOwnController.setOrder(order);
        return order;
    }

    /**
     * Adds a pizza to the current order.
     * This method takes a Pizza object and adds it to the current order.
     *
     * @param pizza The Pizza object to be added to the order.
     */
    public void addPizza(Pizza pizza){
        order.addToOrder(pizza);

    }
}