package io.github.darlene.calculator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.math.MathContext;
import java.math.RoundingMode;


// This is the file that handles the logic
public class CalculatorController {
    //===FXML UI COMPONENTS =====
    @FXML
    private TextField textField;
    @FXML private CheckBox precisionToggle;
    @FXML private RadioButton radioButton;
    @FXML private ToggleGroup radioGroup;

    // getter and setter of using big decimal
    public boolean isUseBigDecimal(){
        return useBigDecimal;
    }

    public void setUseBigDecimal(boolean useBigDecimal){
        this.useBigDecimal = useBigDecimal;
    }

    // getDisplay getter and setter methods
    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    // firstOperand getter and setter methods
    public Number getFirstOperand (){
        return firstOperand;
    }

    public void setFirstOperand(Number firstOperand){
        this.firstOperand = firstOperand;
    }

    //getter and setter for pendingOperator
    public String setPendingOperator(){
        return pendingOperator;
    }

    public void getPendingOperator(String pendingOperator){
        this.pendingOperator = pendingOperator;
    }

    //getter and setter for lastOperator
    public String setLastOperator(){
        return lastOperator;
    }

    public void getLastOperator(String lastOperator){
        this.lastOperator = lastOperator;
    }

    // getter and setter for lastOperand
    public Number setLastOperand() {
        return lastOperand;
    }

    public void getLastOperand(Number lastOperand){
        this.lastOperand = lastOperand;
    }

    // getter and setter for the current state
    public State getCurrentState(){
        return currentState;
    }

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    // getter and setter for Angle Mode
    public AngleMode getAngelMode(){
        return angleMode;
    }

    public void setAngleMode(AngleMode angleMode){
        this.angleMode = angleMode;
    }


    // ENUMERATION STATES
    public enum State {
        AWAITING_FIRST,  // INITIAL STATE
        OPERATOR_SELECTED, // Operator clicked, waiting for second number
        AWAITING_SECOND, //Typing second number
        SHOWING_RESULT, // Result displayed, ready for new operation
        ERROR    //State incase-of a 0 division or anything else
    }

    public enum AngleMode{
        DEGREES, RADIANS
    }

    // == Fields (Calculator/ Memory State) ====
    private boolean useBigDecimal = false;
    private String displayText = "";
    private Number firstOperand = null;
    private String pendingOperator = null;
    private String lastOperator = null;
    private Number lastOperand = null;
    private State currentState = State.AWAITING_FIRST;
    private AngleMode angleMode = AngleMode.DEGREES;

    // Constants for precision control.
    private static final int PRECISION_SCALE = 15;
    private static final MathContext MATH_CONTEXT = new MathContext(34, RoundingMode.HALF_UP);

    //==== Initialization ========
    @FXML
    public void initialize(){
        //Initialize the display
        displayText = "0";
        updateDisplay();

        // set up angle mode toggle group
        if (angleModeGroup != null){
            degree.Mode.setToggleGroup(angleModeGroup);
            radianMode.setToggleGroup(angleModeGroup);
            degreeMode.setSelected(true); // Set degree to always be default
        }

        // Add listeners for mode changes
        if (precisionToggle != null){
            precisionToggle.setOnAction(e -> togglePrecisionMode());
        }

        if (angleModeGroup != null){
            angleModeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                if (newToggle == degreeMode){
                    angleMode = AngleMode.DEGREES;
                } else if (newToggle == radianMode){
                    angleMode = AngleMode.RADIANS;
                }
            });
        }
    }

    // ====HELPER FUNCTIONS =====

    private void updateDisplay(){
        if(display != null){
            display.setText(displayText);
        }
    }
    private Number parseDisplayToNumber(){
        try{
            if (useBigDecimal){
                return new BigDecimal(displayText);
            } else {
                return Double.parseDouble(displayText);
            }
        } catch (NumberFormatException e){
            return useBigDecimal ? BigDecimal.ZERO : 0.0;
        }
    }









    public static void main(String[] args){


    }
}