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
    @FXML private TextField textField;    // The display to show the numbers during the calculations and answers
    @FXML private CheckBox precisionToggle;   // Toggles between the bigDecimalMode and double mode for precision and accuracy
    @FXML private RadioButton radioButton;  // They help us in choosing the different angle modes that is between degrees and radians
    @FXML private ToggleGroup angleModeGroup;

    // ENUMERATION STATES == This represents the current state of the calculator they are constants
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
    // Used in parsing expressions, each element in the expression is categorized left pare and right paren as just both sides parenthesis
    public enum TokenType {
        NUMBER, OPERATOR, FUNCTION, LEFT_PAREN, RIGHT_PAREN
    }

    // == VARIABLES (Calculator/ Memory State) ====
    private boolean useBigDecimal = false;
    private String displayText = "";   // It is a string because we are building the numbers character by character, and we parse them to a double of bigdecimal do convert it to numerics
    private Number firstOperand = null;
    private String pendingOperator = null;
    private String lastOperator = null;
    private Number lastOperand = null;
    private State currentState = State.AWAITING_FIRST;
    private AngleMode angleMode = AngleMode.DEGREES;

    //==== CONSTANTS FOR PRECISION CONTROL
    private static final int PRECISION_SCALE = 15;
    private static final MathContext MATH_CONTEXT = new MathContext(34, RoundingMode.HALF_UP);

    // GETTERS AND SETTERS FOR THE PRIVATE VARIABLES
    public boolean isUseBigDecimal(){
        return useBigDecimal;
    }
    public void setUseBigDecimal(boolean useBigDecimal){
        this.useBigDecimal = useBigDecimal;
    }

    public String getDisplayText() {
        return displayText;
    }
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
        updateDisplay();
    }

    // firstOperand getter and setter methods
    public Number getFirstOperand (){
        return firstOperand;
    }
    public void setFirstOperand(Number firstOperand){
        this.firstOperand = firstOperand;
    }

    //getter and setter for pendingOperator
    public String getPendingOperator(){
        return pendingOperator;
    }
    public void setPendingOperator(String pendingOperator){
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
    public Number getLastOperand() {
        return lastOperand;
    }
    public void setLastOperand(Number lastOperand){

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
    public AngleMode getAngleMode(){
        return angleMode;
    }
    public void setAngleMode(AngleMode angleMode){
        this.angleMode = angleMode;
    }


    //====TOKEN FOR SHUNTING YARD ALGORITHM =====
    public static class Token {
        TokenType type;
        String value;
        int precedence;
        boolean rightAssociative;
        int arity; // NUmber of operands (1 for unary and 2 for binary)

        public Token(TokenType type, String value){
            this.type = type;
            this.value = value;
            this.arity = 2; // Default binary
            this.rightAssociative = false;

            // Set precedent based on the operator
            switch (value){
                case "+": case "-": precedence = 1;
                    break;
                case "*": case "/": precedence = 2;
                    break;
                case "^": precedence = 3; rightAssociative = true;
                    break;
                case "sqrt": case "sin": case "cos": case "tan":
                case "ln": case "log": case "negate": case "abs":
                    precedence = 4; arity = 1;
                    break;
                case "(": case ")": precedence = 0;
                break;
                default: precedence = 0; break;
            }

        }
        @Override
        public String toString(){
            return value;
        }
    }

    //==== Initialization ========
    @FXML
    public void initialize(){
        //Initialize the display
        displayText = "0";
        updateDisplay();

        // set up angle mode toggle group
        if (angleModeGroup != null){
            degreeMode.setToggleGroup(angleModeGroup);
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