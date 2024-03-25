import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignUpPage implements ActionListener {
    // Swing components are now private to encapsulate the UI elements.
    private JFrame frame;
    private JButton signUpButton, resetButton, logInButton;
    private JTextField userIDField;
    private JPasswordField userPasswordField, reEnterPasswordField;
    private JLabel userIDLabel, userPasswordLabel, reEnterPasswordLabel, messageLabel;
    private IDandPassword iDandPassword;
    private JPanel formPanel, buttonPanel;

    public SignUpPage(IDandPassword iDandPasswordOriginal) {
        this.iDandPassword = iDandPasswordOriginal;
        initializeComponents();
        layoutComponents();
        setUpFrame();
    }

    private void initializeComponents() {
        // Initialize labels
        userIDLabel = new JLabel("Username:");
        userPasswordLabel = new JLabel("Password:");
        reEnterPasswordLabel = new JLabel("Repeat Password:");
        messageLabel = new JLabel();
        messageLabel.setFont(new Font("POPPINS", Font.ITALIC, 25));

        // Initialize text fields
        userIDField = new JTextField();
        userPasswordField = new JPasswordField();
        reEnterPasswordField = new JPasswordField();

        // Initialize buttons
        signUpButton = createButton("Sign Up", this);
        resetButton = createButton("Reset", this);
        logInButton = createButton("Log In", this);

        // Initialize panels
        formPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener(listener);
        return button;
    }

    private void layoutComponents() {
        // Adding components to form panel
        formPanel.add(userIDLabel);
        formPanel.add(userIDField);
        formPanel.add(userPasswordLabel);
        formPanel.add(userPasswordField);
        formPanel.add(reEnterPasswordLabel);
        formPanel.add(reEnterPasswordField);
        formPanel.add(messageLabel);

        // Adding buttons to button panel
        buttonPanel.add(signUpButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(logInButton);
    }

    private void setUpFrame() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Use BoxLayout for the main frame layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(formPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetFields();
        } else if (e.getSource() == signUpButton) {
            performSignUp();
        } else if (e.getSource() == logInButton) {
            navigateToLoginPage();
        }
    }

    private void resetFields() {
        userIDField.setText("");
        userPasswordField.setText("");
        reEnterPasswordField.setText("");
        messageLabel.setText("");
    }

    private void performSignUp() {
        String userID = userIDField.getText();
        String password = new String(userPasswordField.getPassword());
        String reEnterPassword = new String(reEnterPasswordField.getPassword());

        if (!password.equals(reEnterPassword)) {
            setMessage("Passwords do not match", Color.RED);
        } else if (iDandPassword.loginInfo.containsKey(userID)) {
            setMessage("Username already exists", Color.RED);
        } else {
            registerUser(userID, password);
            setMessage("Sign Up Successful", Color.GREEN);
            navigateToLoginPage();
        }
    }

    private void setMessage(String text, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(text);
    }

    private void registerUser(String userID, String password) {
        iDandPassword.loginInfo.put(userID, password);
        iDandPassword.users.put(userID, new User(userID, password));

    }

    private void navigateToLoginPage() {
        frame.dispose();
        new LogInPage(iDandPassword.getLoginInfo());
    }
}