module interfaces.saelavrai {
    requires javafx.controls;
    requires javafx.fxml;


    opens interfaces.saelavrai to javafx.fxml;
    exports interfaces.saelavrai;
}