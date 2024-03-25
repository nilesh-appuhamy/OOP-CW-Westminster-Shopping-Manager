
/*
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ElectronicsTest {

    @Test
    public void testConstructorAndGetters() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        assertEquals("E123", electronics.getProductID());
        assertEquals("Smartphone", electronics.getProductName());
        assertEquals(5, electronics.getQuantity());
        assertEquals(499.99, electronics.getPrice(), 0.001); // Adjust the delta based on your precision requirement
        assertEquals("Samsung", electronics.getBrand());
        assertEquals(12, electronics.getWarrantyPeriod());
    }

    @Test
    public void testSetters() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        electronics.setBrand("Apple");
        electronics.setWarrantyPeriod(24);

        assertEquals("Apple", electronics.getBrand());
        assertEquals(24, electronics.getWarrantyPeriod());
    }

    @Test
    public void testToString() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        String expectedToString = "\n------- Electronics Details --------\n" +
                "Product ID      : E123\n" +
                "Product Name    : Smartphone\n" +
                "Quantity        : 5\n" +
                "Price           : 499.99\n" +
                "Brand           : Samsung\n" +
                "Warranty Period : 12 months\n";

        assertEquals(expectedToString, electronics.toString());
    }

    @Test
    public void testToRowString() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        String expectedToRowString = String.format(
                "| %-15s | %-15s | %-15d | %-20.2f | %-10s | %-10d |",
                "E123", "Smartphone", 5, 499.99, "Samsung", 12);

        assertEquals(expectedToRowString, electronics.toRowString());
    }


    @Test
    public void testSaveToString() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        String expectedSaveToString = "Electronics, E123, Smartphone, 5, 499.99, Samsung, 12";

        assertEquals(expectedSaveToString, electronics.saveToString());
    }

    @Test
    public void testGetProductCategory() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        assertEquals("Electronics", electronics.getProductCategory());
    }

    @Test
    public void testGetInfo() {
        Electronics electronics = new Electronics("E123", "Smartphone", 5, 499.99, "Samsung", 12);

        assertEquals("Samsung, 12", electronics.getInfo());
    }
}

*/
