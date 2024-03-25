public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    // constructor
    public Electronics(String productID, String productName, int quantity, double price, String brand, int warrantyPeriod) {
        super(productID, productName, quantity, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }


    // Getters
    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }


    // Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // override the toString method
    @Override
    public String toString() {
        return "\n------- Electronics Details --------\n" +
                "Product ID      : " + productID + "\n" +
                "Product Name    : " + productName + "\n" +
                "Quantity        : " + quantity + "\n" +
                "Price           : " + price + "\n" +
                "Brand           : " + brand + "\n" +
                "Warranty Period : " + warrantyPeriod + " months\n";
    }

    @Override
    public String toRowString() {
        return String.format(
                "| %-15s | %-15s | %-15s | %-20s | %-10s | %-10s |",
                productID, productName, quantity, price, brand, warrantyPeriod);
    }

    @Override
    public String saveToString() {
        return "Electronics, " + productID + ", " + productName + ", " + quantity + ", " + price + ", " + brand + ", " + warrantyPeriod;
    }

    @Override
    public String getProductCategory() {
        return "Electronics";
    }

    @Override
    public String getInfo() {
        return brand + ", " + warrantyPeriod;
    }
}