module com.danyeon {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.danyeon to javafx.fxml;
    exports com.danyeon;
}
