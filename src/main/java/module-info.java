module com.trblunt.DrPepper {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.trblunt.DrPepper to javafx.fxml;
    exports com.trblunt.DrPepper;
}
