/**
 * calculator
 */
public class CalculatorUtils {
    enum Mode {
        NONE_MODE, // Doing nothing

        SUM_MODE, SUBTRACT_MODE, MULTIPLY_MODE, DIVIDE_MODE, POW_MODE,
    }
    private static final String numbers = "0123456789";
    private static final String operators = "+-*/^=";
    private static final String specials = ".";
    private static final String defaultDisplay = "0";
    private static final Double DEFAULT_FIRST_NUM = 0.0;
    private static final Double DEFAULT_SECOND_NUM = null;

    private StringBuilder display;
    private Double firstNum, secondNum;
    private Mode mode;
    private boolean displayUnmodifiable, goingToClear;
    private boolean displayEmpty, hasDot, continueCalculation;
    private char prevChar;

    /**
     * constructor
     */
    public CalculatorUtils(){
        clearCalculator();
        firstNum = DEFAULT_FIRST_NUM;
        secondNum = DEFAULT_SECOND_NUM;
    }

    /**
     * clear the calculator
     */
    public void clearCalculator(){
        setMode(Mode.NONE_MODE);
        firstNum = DEFAULT_FIRST_NUM;
        secondNum = DEFAULT_SECOND_NUM;
        clearDisplay();
    }

    /**
     * clear the display
     */
    private void clearDisplay(){
        display = new StringBuilder(defaultDisplay);
        displayUnmodifiable = false;
        goingToClear = false;
        displayEmpty = true;
        hasDot = false;
        continueCalculation = false;
    }

    /**
     * change the mode of the calculator
     * @param newMode the new mode
     */
    private void setMode(Mode newMode){
        mode = newMode;
    }

    /**
     * get the current mode of the calculator
     * @return the mode of the calculator
     */
    private Mode getMode(){
        return mode;
    }

    /**
     * add a char to the display
     * if it's a operator then change the mode accordingly
     * @param c the character
     */
    public void addChar(char c){

        if(specials.contains(String.valueOf(c))){
            if(c == '.'){
                if(!hasDot) appendDisplay(c);
                hasDot = true;
                displayEmpty = false;
            }
            prevChar = c;
            return;
        }

        if(operators.contains(String.valueOf(c))){
            if(c == prevChar) return;

            if(!firstNum.equals(DEFAULT_FIRST_NUM) && !displayEmpty) continueCalculation = true;

            //System.out.println(continueCalculation);

            if(c == '=' || continueCalculation){
                secondNum = displayToNum();
                double res = getResult();
                writeNumberToDisplay(res);
                displayUnmodifiable = true;
                setMode(Mode.NONE_MODE);


                prevChar = c;
                if(!continueCalculation) return;
                continueCalculation = false;
            }

            Mode switchMode = Mode.NONE_MODE;
            if(c == '+') switchMode = Mode.SUM_MODE;
            if(c == '-') switchMode = Mode.SUBTRACT_MODE;
            if(c == '*') switchMode = Mode.MULTIPLY_MODE;
            if(c == '/') switchMode = Mode.DIVIDE_MODE;
            if(c == '^') switchMode = Mode.POW_MODE;

            if(switchMode == getMode()) return;

            setMode(switchMode);

            firstNum = displayToNum();
            displayUnmodifiable = false;
            goingToClear = true;
            prevChar = c;
            return;
        }

        if (displayUnmodifiable) return;
        if(goingToClear) clearDisplay();

        if(numbers.contains(String.valueOf(c))){
            if(displayEmpty) {
                displayEmpty = false;
                display.setLength(0);
            }
            appendDisplay(c);

        }
        prevChar = c;
    }

    /**
     * return the result of the current mode
     * @return the result
     */
    private double getResult(){
        Mode mode = getMode();
        if(mode == Mode.SUM_MODE) return mathUtils.sum(firstNum, secondNum);
        if(mode == Mode.SUBTRACT_MODE) return mathUtils.subtract(firstNum, secondNum);
        if(mode == Mode.DIVIDE_MODE) return mathUtils.divide(firstNum, secondNum);
        if(mode == Mode.MULTIPLY_MODE) return mathUtils.multiply(firstNum, secondNum);
        if(mode == Mode.POW_MODE) return mathUtils.pow(firstNum, secondNum);
        return 0.0;
    }

    /**
     * write number to display
     * @param num the number
     */
    private void writeNumberToDisplay(double num){
        display.setLength(0);
        for(char c : String.valueOf(num).toCharArray()) display.append(c);
    }

    /**
     * append a char to display (absolute)
     * @param c the character
     */
    private void appendDisplay(char c){
        display.append(c);
    }

    /**
     * backspace
     */
    public void backspace(){
        if(display.length() > 0) {
            int index = display.length() - 1;
            char deletedChar = display.charAt(index);
            if(deletedChar == '.') hasDot = false;
            display.deleteCharAt(index);
        }
        if(display.length() == 0) {
            display.append(defaultDisplay);
            displayEmpty = true;
        }
    }

    /**
     * convert display to string
     * @return the string represents the display
     */
    public String displayToString(){
        return String.format("%s", displayToNum());
    }

    /**
     * convert display to double
     * @return the double represents the display
     */
    public double displayToNum(){
        return Double.parseDouble(display.toString());
    }

    /**
     * change the number in display into percentage
     */
    public void getPercentage(){
        double num = displayToNum();
        double res = mathUtils.percentage(num);
        writeNumberToDisplay(res);
    }

    /**
     * square the number in display
     */
    public void square(){
        double num = displayToNum();
        double res = mathUtils.square(num);
        writeNumberToDisplay(res);
    }

    /**
     * reverse the sign of the number in the display
     */
    public void reverseSign(){
        double num = displayToNum();
        double res = mathUtils.reverseSign(num);
        writeNumberToDisplay(res);
    }

    public static void main(String[] args) {
        CalculatorUtils calculator = new CalculatorUtils();
        char[] actions = {'3', '+', '5', '/','4', '='};
        for(char act : actions){
            calculator.addChar(act);
            System.out.printf("%c %s\n",act, calculator.displayToString());
        }
    }

}
