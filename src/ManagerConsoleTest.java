/*

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerConsoleTest {

    private ManagerConsole managerConsole;
    private WestminsterShoppingManager shoppingManager;

    @BeforeEach
    public void setup() {
        managerConsole = new ManagerConsole();
        shoppingManager = new WestminsterShoppingManager();
    }

    @Test
    public void testAddProduct() {
        String inputString = "1\nE\nTestID\nTestProduct\n10\n20.5\nTestBrand\n6\n0\n";
        simulateUserInput(inputString);

        WestminsterShoppingManager.addProduct();

        // Assuming WestminsterShoppingManager has a method like addProduct(Product product)
        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(1, productList.size());
        assertEquals("TestID", productList.get(0).getProductID());
        assertEquals("TestProduct", productList.get(0).getProductName());
        // Add more assertions based on your actual implementation
    }

    @Test
    public void testRemoveProduct() {
        // Assuming there's a product to remove with ID "TestID"
        shoppingManager.addProduct(new Electronics("TestID", "TestProduct", 5, 15.5, "TestBrand", 6));

        String inputString = "2\nTestID\n0\n";
        simulateUserInput(inputString);

        WestminsterShoppingManager.removeProduct();

        // Assuming WestminsterShoppingManager has a method like removeProduct(String productID)
        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(0, productList.size());
    }

    // Add more test methods for other functionalities like updateProductQuantity, printAllProducts, etc.

    // Helper method to simulate user input
    private void simulateUserInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}

*/
