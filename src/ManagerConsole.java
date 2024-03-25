import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ManagerConsole {

    private static void displayMenu() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Please select an option:");
        System.out.println("1- Add new product");
        System.out.println("2- Remove existing product");
        System.out.println("3- Re-stock (add extra) product");
        System.out.println("4- Print the list of products");
        System.out.println("5- Save to file");
        System.out.println("6- Open customer GUI");
        System.out.println("0- Quit");
        System.out.println("-------------------------------------------------");
    }
    //validate the choice
    private static boolean validID(int choice) {
        return !(choice >= 0 && choice <= 6);
    }


    //ask the details of input product
    private static String validProductType(Scanner input) {
        while (true) {
            System.out.println("What kind of a product are you trying to add?");
            System.out.println("Enter E for an electronic item \nEnter C for a clothing item");
            System.out.print("Enter your response here (E/C): ");
            String response = input.next();

            if (response.equalsIgnoreCase("E")) {
                return "electronic";
            } else if (response.equalsIgnoreCase("C")) {
                return "clothing";
            } else {
                System.out.println("Not a valid product type. Choose E or C!");
            }

        }

    }

    public static String getProductDetails(Scanner input, String detail) {
        System.out.println(" "+detail+" ");
        return input.next();
    }
    public static int getProductDetailsInt(Scanner input, String detail) {
        System.out.println("\n " + detail + " ");
        return input.nextInt();

    }
    public static double getProductDetailsDouble(Scanner input, String detail) {
        System.out.println("\n " + detail + " ");
        return input.nextDouble();

    }

    public static String validateID (Scanner input, String productType, List < Product > productList){
        while (true) {
            String productID = getProductDetails(input, "Please Enter The ProductID:");

            // Check for duplicate IDs
            boolean idExists = false;
            for (Product product : productList) {
                if (product.getProductID().equals(productID)) {
                    System.out.println("Product ID already exists. Please choose a different ID.");
                    idExists = true;
                    break;
                }
            }

            if (!idExists) {
                return productID;  // Return valid ID
            }
        }



    }








    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();


        // load the content in text file with system data
        shoppingManager.loadProducts();


        System.out.println("****************************************");
        System.out.println("|             Welcome to              |");
        System.out.println("|        Westminster Shopping!        |");
        System.out.println("****************************************");


        //display the menu to choose
        displayMenu();

        boolean Quit = false;
        while (!Quit) {
            System.out.print("\nEnter Your Choice Here: ");
            int choice;
            try {
                choice = input.nextInt();
                input.nextLine();

                if (validID(choice)) {
                    System.out.println("Please Enter a valid choice between 0 - 6");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter a Number...");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    // Add new product

                    String productType = validProductType(input);// get the type of product(Clothing or Electronic)
                    String productID = validateID(input,productType, shoppingManager.getAllProducts());
                    String productName = getProductDetails(input, "Please Enter The Product Name:");

                    int quantity = getProductDetailsInt(input, "How many " + productName
                            + "s are adding to the system:");
                    double price = getProductDetailsDouble(input, "How much is one " + productName + ":");

                    // add products separately for clothing and electronics
                    if (productType.toUpperCase().startsWith("E")) {
                        String brand = getProductDetails(input, "What is the product brand:");
                        int warrantyPeriod = getProductDetailsInt(input, "How long is the warranty for one "
                                + productName + " (in months):");

                        // create an electronic product based on the details
                        Electronics electronicProduct = new Electronics(productID, productName, quantity, price, brand, warrantyPeriod);
                        shoppingManager.addProduct(electronicProduct);
                        System.out.println(quantity + " " + productName + "/s successfully added to the system");

                    } else if (productType.toUpperCase().startsWith("C")) {

                        String size = getProductDetails(input, "What is the size of the " + productName + " :");
                        String color = getProductDetails(input, "What is the color of the " + productName + " :");

                        // create a clothing product based on the details
                        Clothing clothingProduct = new Clothing(productID, productName, quantity, price, color, size);
                        shoppingManager.addProduct(clothingProduct);
                        System.out.println(quantity + " " + productName + "/s successfully added to the system");
                    }
                    break;
                case 2:
                    // Remove a product from the system
                    System.out.print("ProductID: ");
                    String productId = input.next();
                    shoppingManager.removeProduct(productId);
                    break;
                case 3:
                    // update existing product
                    shoppingManager.printAllProducts("Electronics");
                    shoppingManager.printAllProducts("Clothing");
                    String productIdToUpdate = getProductDetails(input, "Enter the product ID to update the quantity:");

                    int addingAmount = getProductDetailsInt(input, "How many products going to add:");

                    boolean productFound = shoppingManager.updateProductQuantity(productIdToUpdate, addingAmount);
                    break;

                case 4:
                    // Print list of products
                    System.out.print("What you want to print? (Clothing ['C'] or Electronics ['E']): ");
                    String productTypeToPrint = input.next();
                    shoppingManager.printAllProducts(productTypeToPrint);

                    break;

                case 5:
                    // Save products to a file
                    shoppingManager.saveProducts();
                    break;

                case 6:
                    // Open GUI
                    GUI gui = new GUI();
                    break;

                case 0:
                    //quit the program
                    Quit = true;
                    break;

                default:
                    System.out.println("Not a valid input. Try again...");


            }
            displayMenu();

        }


    }

}

