module interfaces.saelavrai {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.logging;
    requires java.sql;


    opens interfaces.saelavrai to javafx.fxml;
    exports interfaces.saelavrai;
}