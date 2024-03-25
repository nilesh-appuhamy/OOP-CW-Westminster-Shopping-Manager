public class Clothing extends Product {
    private String size;
    private String color;

    // Constructor
    public Clothing(String productID, String productName, int quantity, double price, String size, String color) {
        super(productID, productName, quantity, price);
        this.size = size;
        this.color = color;
    }

    //  Getters
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    // Setters
    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // override toString method for clothing class
    @Override
    public String toString() {
        return  "\n Clothing Details -\n" +
                "Product ID      : " + productID + "\n" +
                "Product Name    : " + productName + "\n" +
                "Quantity        : " + quantity + "\n" +
                "Price           : " + price + "\n" +
                "Size            : " + size + "\n" +
                "Color           : " + color + "\n";
    }

    @Override
    public String toRowString() {
        return String.format(
                "| %-15s | %-15s | %-15d | %-20.2f | %-10s | %-10s |",
                getProductID(), getProductName(), getQuantity(), getPrice(), getSize(), getColor());
    }

    @Override
    public String saveToString() {
        return "Clothing, " + productID + ", " + productName + ", " + quantity + ", " + price + ", " + size + ", " + color;
    }

    @Override
    public String getProductCategory() {
        return "Clothing";
    }

    @Override
    public String getInfo() {
        return size + ", " + color;
    }
}