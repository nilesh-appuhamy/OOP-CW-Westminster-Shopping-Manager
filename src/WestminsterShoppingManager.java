import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // store product list
    private static List<Product> productList;

    // Constructor to initialize the productList
    public WestminsterShoppingManager() {
        productList = new ArrayList<>();
    }

    // Override the components
    // Add products
    private boolean Size() {
        return productList.size() < 50;
    }

    @Override
    public void addProduct(Product product) {
        if (Size()) {
            productList.add(product);
            System.out.println("Product added successfully.");
            System.out.println(productList.get(productList.size() - 1).getProductID() + " " + productList.get(productList.size() - 1).getProductName());
            System.out.println("Product added successfully.");
        } else {
            System.out.println("can't add anymore you are on the limit.(50 products)");
        }
    }

    // update products quantity
    public boolean updateProductQuantity(String productId, int addedQuantity) {
        for (Product product : productList) {
            if (product.getProductID().equals(productId)) {
                int newQuantity = product.getQuantity() + addedQuantity;
                product.setQuantity(newQuantity);
                return true;  // updated successfully
            }
        }
        return false;  // Product not found
    }

    // remove products
    public void removeProduct(String productId) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID().equals(productId)) {
                iterator.remove();
                System.out.println("Product deleted successfully.");
                return;
            }
        }
        System.out.println("Product not found with ID: " + productId);
    }

    // get products list
    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    // search products by using ID
    @Override
    public Product getProductByID(String productID) {
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }

    // print all products
    @Override
    public void printAllProducts(String productType) {
        System.out.println(" -- List of Products --  ");
        String header;

        String headerElectric = String.format(
                "%-18s %-26s %-18s %-18s %-18s %-18s",
                "productID", "productName", "quantity", "price", "brand", "warrantyPeriod");

        String headerClothing = String.format(
                "%-20s %-30s %-20s %-20s %-20s %-20s",
                "productID", "productName", "quantity", "price", "size", "color");

        // Print header
        if (productType.toUpperCase().startsWith("E")) {
            header = headerElectric;
        } else {
            header = headerClothing;
        }

        System.out.println(header);


        // display products with the formatting
        for (Product product : productList) {
            if (productType.toUpperCase().startsWith("E") && product instanceof Electronics) {
                System.out.println(product.toRowString());
                System.out.println("---------------------------------------------");
            } else if (productType.toUpperCase().startsWith("C") && product instanceof Clothing) {
                System.out.println(product.toRowString());
                System.out.println("---------------------------------------------");
            }
        }

    }
    //  save all products to the file
    @Override
    public void saveProducts() {
        try (FileWriter writer = new FileWriter("products.txt")) {
            for (Product product : productList) {
                writer.write(product.saveToString() + "\n");  // Write each product's details to the file
            }
            System.out.println("Products saved successfully to products.txt");
        } catch (IOException e) {
            System.out.println("Error saving products to the file: " + e.getMessage());
        }
    }

    @Override
    public void loadProducts() {
        try (Scanner scanner = new Scanner(new File("products.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();
                // Split the line into product attributes, trimming any leading/trailing whitespace
                String[] products = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < products.length; i++) {
                    products[i] = products[i].trim(); // Trim each element
                }

                //Create Product object based on the product type
                Product product;
                if (products[0].startsWith("E")) {
                    product = new Electronics(products[1], products[2],
                            Integer.parseInt(products[3]),
                            Double.parseDouble(products[4]),
                            products[5], Integer.parseInt(products[6]));
                } else {
                    product = new Clothing(products[1], products[2],
                            Integer.parseInt(products[3]),
                            Double.parseDouble(products[4]),
                            products[5], products[6]);
                }

                productList.add(product);
            }
            System.out.println("Products loaded successfully from products.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file")) {
                // File doesn't exist, print a custom message
                System.out.println("No any saved itmes in the directory. ");
            } else {
                // Other error, print the original message
                System.out.println("Error loading products: " + e.getMessage());
            }
        }
    }

    private int countElectronics() {
        int count = 0;
        for (Product product : productList) {
            if (product.getProductID().startsWith("E")) {
                count++;
            }
        }
        return count;
    }

    private int countClothings() {
        int count = 0;
        for (Product product : productList) {
            if (product.getProductID().startsWith("C")) {
                count++;
            }
        }
        return count;
    }

    //  get total
    public int getTotalProducts() {
        return productList.size();
    }

}

