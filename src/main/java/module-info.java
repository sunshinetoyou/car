module com.danyeon {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.danyeon to javafx.fxml;
    exports com.danyeon;
}
