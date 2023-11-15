module com.pizzashop.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pizzashop.project4 to javafx.fxml;
    exports com.pizzashop.project4;
}