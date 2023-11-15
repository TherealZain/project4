package com.pizzashop.project4;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController{
    Stage currentOrder = new Stage();
    Stage specialtyPizza = new Stage();
    Stage storeOrder = new Stage();

    @FXML
    protected void displayBuildOwn() {
        Stage buildOwn = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("build-own.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 500, 400);
            buildOwn.setScene(scene);
            buildOwn.show();
            BuildOwnController BuildOwnController = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            BuildOwnController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading build-own.fxml.");
            alert.setContentText("Couldn't load build-own.fxml.");
            alert.showAndWait();
        }
    }




}