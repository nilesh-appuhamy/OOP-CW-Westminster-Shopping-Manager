import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String userName;
    private String password;
    private int purchaseCount;

    // Constructor with username and password. Sets purchase count to 0 by default.
    public User(String userName, String password) {
        this.userName = userName;
        // Validate the password using the private method validatePassword.
        // If the password is not valid, throw an exception.
        if (validatePassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must contain at least one capital letter and one number.");
        }
        this.purchaseCount = 0;
    }

    // Constructor with username, password, and purchase count.
    public User(String userName, String password, int purchaseCount) {
        this.userName = userName;
        // Validate the password using the private method validatePassword.
        // If the password is not valid, throw an exception.
        if (validatePassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must contain at least one capital letter and one number.");
        }
        this.purchaseCount = purchaseCount;
    }

    // Private method to validate the password.
    private static boolean validatePassword(String password) {
        // Regex to check for at least one uppercase letter and one digit.
        String regex = "^(?=.*[A-Z])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        // When setting a new password, validate it using the private method.
        if (validatePassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must contain at least one capital letter and one number.");
        }
    }

    // Increment the purchase count by 1.
    public void incrementPurchaseCount() {
        purchaseCount++;
    }

    // Method to validate user credentials.
    public boolean validateCredentials(String enteredUsername, String enteredPassword) {
        return userName.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

