module io.github.darlene.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.darlene.calculator to javafx.fxml;
    exports io.github.darlene.calculator;
}