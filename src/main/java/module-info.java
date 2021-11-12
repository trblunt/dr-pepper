module com.trblunt.DrPepper {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires javafaker;
    requires java.sql;

    opens com.trblunt.DrPepper to javafx.fxml;
    exports com.trblunt.DrPepper;
    exports com.trblunt.DrPepper.types;
}
