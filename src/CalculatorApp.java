import javax.swing.*;

/**
 * the calculator app
 */
public class CalculatorApp {
    public static final String DEFAULT_TITLE = "Calculator";


    private final CalculatorGUI appGUI;
    private final JFrame mainFrame;

    /**
     * default constructors
     */
    public CalculatorApp(){
        this(DEFAULT_TITLE);
    }

    /**
     * constructs
     * @param title the title of the app
     */
    public CalculatorApp(String title){
        appGUI = new CalculatorGUI();

        mainFrame = new JFrame();
        mainFrame.setTitle(title);
    }

    /**
     * run the app
     */
    public void run(){
        mainFrame.setContentPane(appGUI.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(CalculatorGUI.FRAME_WIDTH, CalculatorGUI.FRAME_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        app.run();
    }
}
