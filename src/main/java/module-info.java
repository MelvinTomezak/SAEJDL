module interfaces.saelavrai {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens interfaces.saelavrai to javafx.fxml;
    exports interfaces.saelavrai;
}