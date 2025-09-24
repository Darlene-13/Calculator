package io.github.darlene.calculator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;


// This is the file that handles the logic
public class CalculatorController {
    //===FXML UI COMPONENTS =====
    @FXML private TextField textField;    // The display to show the numbers during the calculations and answers
    @FXML private CheckBox precisionToggle;   // Toggles between the bigDecimalMode and double mode for precision and accuracy
    @FXML private RadioButton radioButton;  // They help us in choosing the different angle modes that is between degrees and radians
    @FXML private ToggleGroup angleModeGroup;

    // ENUMERATION STATES == This represents the current state of the calculator they are constants
    public enum State {
        AWAITING_INPUT,  // INITIAL STATE
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

    //====TOKEN FOR SHUNTING YARD ALGORITHM =====
    public static class Token {
        TokenType type;  // Is it a number, is it a sign or function?
        String value; // what the block actually is
        int precedence;  // some blocks need to come before others during calculation eg * should be before +
        boolean rightAssociative;
        int arity; // NUmber of operands (1 for unary and 2 for binary) eg: + addition needs two values while sqrt needs just one value

        // Constructor for the algorithm
        public Token(TokenType type, String value){  //Type and value are basic information about the token
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

    // == VARIABLES (Calculator/ Memory State) ==== // Instance variables each CalculatorControler (object) has its own state. They store the calculators current memory and the behaviour while the user types the numbers and functions.
    private boolean useBigDecimal = false;
    private String displayText = "0";
    private StringBuilder currentExpression = new StringBuilder();  // More efficient than string as it changes  alot without creating new objects every time.
    private State currentState = State.AWAITING_INPUT;
    private AngleMode angleMode = AngleMode.DEGREES;
    private boolean expectingOperand = true;
    private String lastCompleteExpression = "";

    //==== CONSTANTS FOR PRECISION CONTROL
    private static final int PRECISION_SCALE = 15;  //final: can't be changed as they are constants
    private static final MathContext MATH_CONTEXT = new MathContext(34, RoundingMode.HALF_UP);

    // Operator precedence and properties == Acts like a look-up table
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();  // Finds the operator precedence in 0(1) time
    private static final Set<String> RIGHT_ASSOCIATIVE = new HashSet<>();  //Set checks if a function exists instantly like...FUNCTIONS.contains
    private static final Set<String> FUNCTIONS = new HashSet<>();

    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("^", 3);
        PRECEDENCE.put("%", 2);

        RIGHT_ASSOCIATIVE.add("^");

        FUNCTIONS.addAll(Arrays.asList("sqrt", "sin", "cos", "tan", "ln", "log", "abs"));;
    }

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
    public StringBuilder getCurrentExpression (){
        return currentExpression;
    }
    public void setCurrentExpression(StringBuilder currentExpression){
        this.currentExpression = currentExpression;
    }
    public boolean isExpectingOperand(){
        return expectingOperand;
    }
    public void setExpectingOperand(boolean expectingOperand){
        this.expectingOperand = expectingOperand;
    }
    public String getLastCompleteExpression(){
        return lastCompleteExpression;
    }
    public void getLastCompleteExpression(String lastCompleteExpression){
        this.lastCompleteExpression = lastCompleteExpression;
    }

    public State getCurrentState(){
        return currentState;
    }
    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }
    public AngleMode getAngleMode(){
        return angleMode;
    }
    public void setAngleMode(AngleMode angleMode){
        this.angleMode = angleMode;
    }


    //==== Initialization ========
    //==== Setting up the calculator when it starts ======
    @FXML
    public void initialize(){
        displayText = "0";
        updateDisplay();

        //Set up angle mode toggle Group
        if (angleModeGroup != null){
            degreeMode.setToggleGroup(angleModeGroup);  // Connects the degree and radian mode buttons so that only one can be active at a time. //Mutual Exclusive selection.
            radianMode.setToggleGroup(angleModeGroup);
            degreeMode.setSelected(true);
        }

        // Event Listeners for mode changes
        if (precisionToggle != null){
            precisionToggle.setOnAction(e -> togglePrecisionMode());
        }

        if ( angleModeGroup != null){
            angleModeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                angleMode = (newToggle == degreeMode) ? AngleMode.DEGREES : AngleMode.RADIANS;
            });
        }
    }

    // ======DISPLAY MANAGEMENT
    private void updateDisplay() {
        if (display != null){
            display.setText(displayText);
        }
        if (expressionDisplay != null){
            expressionDisplay.setText(currentExpression.toString())
        }
    }

    private void showError(){
        displayText = message != null ? message : "Error";
        currentState = State.ERROR;
        updateDisplay();
    }


    // === NUMBER FORMATTING ======
    private String formatNumberforDisplay(Number number){

    }

    private void togglePrecisionMode() {
    }


    public static void main(String[] args){


    }
}