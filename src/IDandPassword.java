import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class IDandPassword {
    public HashMap<String, String> loginInfo = new HashMap<>();
    public HashMap<String, User> users = new HashMap<>();

    public IDandPassword() {
        initializeLoginInfo();
        loadUsers();
        updateLoginInfoWithLoadedUsers();
    }

    private void initializeLoginInfo() {
        loginInfo.put("Nilesh", "123");
        loginInfo.put("Shanilka", "123N");
    }

    private void updateLoginInfoWithLoadedUsers() {
        for (User user : users.values()) {
            loginInfo.put(user.getUserName(), user.getPassword());
        }
    }

    public void saveUsers() {
        try (FileWriter writer = new FileWriter("users.txt")) {
            for (User user : users.values()) {
                writer.write(String.format("%s:%s:%d%n", user.getUserName(), user.getPassword(), user.getPurchaseCount()));
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving users: " + e.getMessage());
        }
    }

    public void loadUsers() {
        File file = new File("users.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int purchaseCount = Integer.parseInt(parts[2]);
                    users.put(username, new User(username, password, purchaseCount));
                }
            }
        } catch (IOException e) {
            System.out.println("No saved users found or an error occurred. Starting with an empty user list.");
        }
    }

    public HashMap<String, String> getLoginInfo() {
        return new HashMap<>(loginInfo);
    }
}

