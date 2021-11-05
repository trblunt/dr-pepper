module com.trblunt.DrPepper {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires transitive javafx.fxml;

    opens com.trblunt.DrPepper to javafx.fxml;
    exports com.trblunt.DrPepper;
    exports com.trblunt.DrPepper.types;
}
