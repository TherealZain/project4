module com.pizzashop.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pizzashop.project4 to javafx.fxml;
    exports com.pizzashop.project4;
    exports com.pizzashop.project4.pizzas;
    opens com.pizzashop.project4.pizzas to javafx.fxml;
    exports com.pizzashop.project4.enums;
    opens com.pizzashop.project4.enums to javafx.fxml;
}