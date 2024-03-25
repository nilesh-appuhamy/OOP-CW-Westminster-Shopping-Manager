/*import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager shoppingManager;

    @BeforeEach
    public void setup() {
        shoppingManager = new WestminsterShoppingManager();
    }

    @Test
    public void testAddProduct() {
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);

        shoppingManager.addProduct(electronics);

        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(1, productList.size());
        assertEquals(electronics, productList.get(0));
    }

    @Test
    public void testUpdateProductQuantity() {
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        shoppingManager.addProduct(electronics);

        boolean result = shoppingManager.updateProductQuantity("E001", 3);

        assertTrue(result);
        assertEquals(8, electronics.getQuantity());
    }

    @Test
    public void testRemoveProduct() {
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        shoppingManager.addProduct(electronics);

        shoppingManager.removeProduct("E001");

        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(0, productList.size());
    }

    @Test
    public void testGetAllProducts() {
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        Clothing clothing = new Clothing("C001", "T-Shirt", 10, 19.99, "M", "Blue");

        shoppingManager.addProduct(electronics);
        shoppingManager.addProduct(clothing);

        List<Product> productList = shoppingManager.getAllProducts();
        assertEquals(2, productList.size());
        assertTrue(productList.contains(electronics));
        assertTrue(productList.contains(clothing));
    }

    @Test
    public void testGetProductByID() {
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        shoppingManager.addProduct(electronics);

        Product retrievedProduct = shoppingManager.getProductByID("E001");

        assertNotNull(retrievedProduct);
        assertEquals(electronics, retrievedProduct);
    }

   *//* @Test
    public void testSaveAndLoadProducts() throws IOException {
        Electronics electronics = new Electronics("E001", "Laptop", 5, 999.99, "Dell", 12);
        Clothing clothing = new Clothing("C001", "T-Shirt", 10, 19.99, "M", "Blue");

        shoppingManager.addProduct(electronics);
        shoppingManager.addProduct(clothing);

        shoppingManager.saveProducts();

        // Create a new instance of WestminsterShoppingManager to load products
        WestminsterShoppingManager loadedShoppingManager = new WestminsterShoppingManager();
        loadedShoppingManager.loadProducts();

        List<Product> loadedProductList = loadedShoppingManager.getAllProducts();

        assertEquals(2, loadedProductList.size());
        assertTrue(loadedProductList.contains(electronics));
        assertTrue(loadedProductList.contains(clothing));

        // Clean up: Delete the temporary file created during testing
        File fileToDelete = new File("productsData.txt");

        // Print more information about the file
        System.out.println("File path: " + fileToDelete.getAbsolutePath());
        System.out.println("File exists: " + fileToDelete.exists());

        // Try to delete the file and print the result
        boolean deleteResult = fileToDelete.delete();
        System.out.println("File deleted: " + deleteResult);

        // If deletion fails, attempt to delete on JVM exit
        if (!deleteResult) {
            fileToDelete.deleteOnExit();
        }
    }*//*




}*/

