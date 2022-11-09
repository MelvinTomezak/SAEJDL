module interfaces.saelavrai {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;


    opens interfaces.saelavrai to javafx.fxml;
    exports interfaces.saelavrai;
}