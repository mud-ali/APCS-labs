module huff {
    requires javafx.controls;
    requires javafx.fxml;

    opens huff to javafx.fxml;
    exports huff;
}
