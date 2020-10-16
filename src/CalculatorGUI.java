import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI {
    public final static String DEFAULT_FONT = Font.SERIF;
    public final static int DEFAULT_FONT_SIZE = 40;
    public final static int DISPLAY_MAX_DIGIT = 13;
    public static final int FRAME_WIDTH = 320;
    public static final int FRAME_HEIGHT = 380;

    private final CalculatorUtils calculator;

    public JPanel mainPanel;

    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;

    private JButton dotButton;
    private JButton equalButton;


    private JButton sumButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton powButton;


    private JButton backspaceButton;
    private JButton reverseSignButton;
    private JButton clearButton;
    private JButton percentageButton;
    private JButton squareButton;

    private JTextPane displayTextPane;

    /**
     * default constructors
     */
    public CalculatorGUI() {
        this(DEFAULT_FONT, DEFAULT_FONT_SIZE);
    }

    /**
     * constructors
     *
     * @param font     the font of the display
     * @param fontSize the size of the font in the display
     */
    public CalculatorGUI(String font, int fontSize) {
        calculator = new CalculatorUtils();

        addButtonsNames();
        displayTextPaneInit(font, fontSize);
        addMouseListeners();
        addKeyListeners();
    }

    /**
     * set the name for each available button
     */
    private void addButtonsNames() {
        // normal commands have names that associate to their keys on the keyboard
        a1Button.setName("1");
        a2Button.setName("2");
        a3Button.setName("3");
        a4Button.setName("4");
        a5Button.setName("5");
        a6Button.setName("6");
        a7Button.setName("7");
        a8Button.setName("8");
        a9Button.setName("9");
        a0Button.setName("0");

        dotButton.setName(".");
        equalButton.setName("=");

        sumButton.setName("+");
        subtractButton.setName("-");
        multiplyButton.setName("*");
        divideButton.setName("/");
        powButton.setName("^");

        // special commands have their name
        backspaceButton.setName("backspace");
        reverseSignButton.setName("reverse sign");
        clearButton.setName("clear");
        percentageButton.setName("percent");
        squareButton.setName("square");
    }

    /**
     * initiate TextPane
     */
    private void displayTextPaneInit(String font, int fontSize) {
        // make display uneditable
        displayTextPane.setEditable(false);

        // set paragraph attributes
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attributes, font);
        StyleConstants.setFontSize(attributes, fontSize);
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
        displayTextPane.setParagraphAttributes(attributes, true);

        // display to text pane
        displayTextPane.setText(calculator.displayToString());
    }

    /**
     * add mouse listeners
     */
    private void addMouseListeners() {
        JButton[] buttons = {a1Button, a2Button, a3Button, a4Button, a5Button,
                            a6Button, a7Button, a8Button, a9Button, a0Button,
                            dotButton, equalButton,
                            sumButton, subtractButton, multiplyButton,
                            divideButton, powButton};
        ActionListener listener = e -> {
            JButton button = (JButton) e.getSource();
            String buttonName = button.getName();
            calculator.addChar(buttonName.charAt(0));
            displayTextPane.setText(calculator.displayToString());
        };
        for (JButton button : buttons) button.addActionListener(listener);

        clearButton.addActionListener(e -> {
            calculator.clearCalculator();
            displayTextPane.setText(calculator.displayToString());
        });

        backspaceButton.addActionListener(e -> {
            calculator.backspace();
            displayTextPane.setText(calculator.displayToString());
        });

        percentageButton.addActionListener(e -> {
            calculator.getPercentage();

            displayTextPane.setText(calculator.displayToString());
        });

        reverseSignButton.addActionListener(e -> {
            calculator.reverseSign();

            displayTextPane.setText(calculator.displayToString());
        });

        squareButton.addActionListener(e -> {
            calculator.square();
            displayTextPane.setText(calculator.displayToString());
        });
    }

    /**
     * read keyboard dispatcher (class)
     */
    private class CalculatorKeyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                pushButton(e);
            }
            return false;
        }
    }

    /**
     * push button assosciate with the key event
     *
     * @param keyEvent the key event
     */
    private void pushButton(KeyEvent keyEvent) {
        // numbers
        if (keyEvent.getKeyChar() == '0') a0Button.doClick();
        if (keyEvent.getKeyChar() == '1') a1Button.doClick();
        if (keyEvent.getKeyChar() == '2') a2Button.doClick();
        if (keyEvent.getKeyChar() == '3') a3Button.doClick();
        if (keyEvent.getKeyChar() == '4') a4Button.doClick();
        if (keyEvent.getKeyChar() == '5') a5Button.doClick();
        if (keyEvent.getKeyChar() == '6') a6Button.doClick();
        if (keyEvent.getKeyChar() == '7') a7Button.doClick();
        if (keyEvent.getKeyChar() == '8') a8Button.doClick();
        if (keyEvent.getKeyChar() == '9') a9Button.doClick();

        //specials
        if (keyEvent.getKeyChar() == '.') dotButton.doClick();

        if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) backspaceButton.doClick();
        if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) backspaceButton.doClick();

        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) equalButton.doClick();

        // operator
        if (keyEvent.getKeyChar() == '+') sumButton.doClick();
        if (keyEvent.getKeyChar() == '-') subtractButton.doClick();
        if (keyEvent.getKeyChar() == '*') multiplyButton.doClick();
        if (keyEvent.getKeyChar() == '/') divideButton.doClick();
    }

    /**
     * add keyboard listeners
     */
    private void addKeyListeners() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new CalculatorKeyDispatcher());
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Calculator");

        CalculatorGUI calculatorGUI = new CalculatorGUI();

        mainFrame.setContentPane(calculatorGUI.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(320, 380);

        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}
