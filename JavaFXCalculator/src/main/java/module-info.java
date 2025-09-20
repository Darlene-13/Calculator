module io.github.darlene13.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.darlene13.calculator to javafx.fxml;
    exports io.github.darlene13.calculator;
}