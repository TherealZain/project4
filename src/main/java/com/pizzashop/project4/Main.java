package com.pizzashop.project4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;

import static javafx.application.Application.launch;

/**
 * Main application class for the pizza shop application.
 * This class extends {@link javafx.application.Application}
 * and serves as the entry point for the JavaFX application.
 */
public class Main extends Application{

    /**
     * Starts the primary stage of the application.
     * This method is called after the application has been initialized and is ready to start.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws IOException If the FXML file for the main menu cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 841, 591);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the application.
     * This is the entry point method that is called to start the JavaFX application.
     *
     * @param args Command line arguments passed to the application. Not used in this application.
     */
    public static void main(String[] args) {
        launch();
    }
}
