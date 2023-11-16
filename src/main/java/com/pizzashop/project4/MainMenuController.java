package com.pizzashop.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController{
    Stage currentOrder = new Stage();
    Stage specialtyPizza = new Stage();

    @FXML
    protected void displayBuildOwn() {
        Stage buildOwn = new Stage();
        AnchorPane root;
        buildOwn.setTitle("Build Your Own");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("build-own.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 400);
            buildOwn.setScene(scene);
            buildOwn.show();
            BuildOwnController buildOwnController = loader.getController();
            buildOwnController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayStoreOrders() {
        Stage storeOrders = new Stage();
        AnchorPane root;
        storeOrders.setTitle("Store Orders");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store-orders.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 400);
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

    @FXML
    protected void displayCurrentOrder() {
        Stage currentOrder = new Stage();
        AnchorPane root;
        currentOrder.setTitle("Current Order");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 400);
            currentOrder.setScene(scene);
            currentOrder.show();
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displaySpecialtyPizzas() {
        Stage specialityPizzas = new Stage();
        AnchorPane root;
        specialityPizzas.setTitle("Speciality Pizzas");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("specialty-pizzas.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 400);
            specialityPizzas.setScene(scene);
            specialityPizzas.show();
            SpecialtyPizzasController specialtyPizzasController = loader.getController();
            specialtyPizzasController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }







}