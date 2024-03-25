import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// The GUI class that implements the ActionListener and ListSelectionListener interfaces.
public class GUI implements ActionListener, ListSelectionListener {

    // Declare fields for the GUI components and data structures used.
    private ArrayList<Product> products;
    private ShoppingCart shoppingCart;
    private JLabel categoryL, detailsL, productL;
    private JComboBox comboBox;
    private JTable table;
    private JButton cartBtn, addCart;
    private DefaultTableModel model;

    // Constructor for the GUI class.
    public GUI(ArrayList<Product> products) {

        // Initializes the products and shoppingCart.
        this.products = products;
        shoppingCart = new ShoppingCart();

        // Creating the main application window.
        JFrame frame = new JFrame("Westminster Shopping");
        frame.setSize(600, 550);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Adding a label and drop-down box for category selection
        categoryL = new JLabel("Select Category");
        categoryL.setBounds(80, 20, 160, 25);
        panel.add(categoryL);

        comboBox = new JComboBox(new String[]{"All", "Electronics", "Clothing"});
        comboBox.setSelectedItem("All");
        comboBox.addActionListener(this);
        comboBox.setBounds(240, 20, 160, 25);
        panel.add(comboBox);

        // Setting up the table to display products.
        model = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price(Rs.)", "Info"}, 0);
        for (Product product : products) {
            Object[] productArray = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice(), product.getInfo()};
            model.addRow(productArray);
        }

        // Configure the table and add it to a scroll pane
        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(150);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(25, 70, 550, 250);
        panel.add(sp);

        detailsL = new JLabel("Selected Product - Details");
        Font f = new Font("", Font.BOLD, 12);
        detailsL.setFont(f);
        detailsL.setBounds(30, 320, 200, 25);
        panel.add(detailsL);

        productL = new JLabel("Select a product to view details");
        productL.setBounds(30, 340, 200, 125);
        panel.add(productL);

        ListSelectionModel listModel = table.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);

        // Adding buttons for interacting with the shopping cart.
        cartBtn = new JButton("View Cart");
        cartBtn.setBounds(430, 10, 150, 25);
        cartBtn.addActionListener(this);
        panel.add(cartBtn);

        addCart = new JButton("Add To Cart");
        addCart.setBounds(200, 470, 170, 25);
        addCart.addActionListener(this);
        panel.add(addCart);


        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // This function creates a new frame that displays the shopping cart
    public void shoppingCartFrame() {
        JFrame frame = new JFrame("Shopping Cart");
        frame.setSize(600, 450);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Product", "Quantity", "Price"}, 0);

        JScrollPane scrollPane = new JScrollPane(new JTable(model));
        scrollPane.setBounds(10, 10, 580, 200);
        panel.add(scrollPane);

        HashMap<Product, Integer> map = shoppingCart.getProducts();
        model.setRowCount(0);
        double total = 0;
        boolean threeItems = false;
        int electronicsCount = 0;
        int clothingCount = 0;
        double discount = 0;
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            Object[] arr = {product.getProductID() + ", " + product.getProductName() + ", " + product.getInfo(), quantity, (quantity * product.getPrice())};
            model.addRow(arr);
            total += (quantity * product.getPrice());
            if(product.getProductCategory().equalsIgnoreCase("Electronics")){
                electronicsCount += entry.getValue();
            }
            else if(product.getProductCategory().equalsIgnoreCase("Clothing")){
                clothingCount += entry.getValue();
            }
            if(electronicsCount >= 3 || clothingCount >= 3){
                threeItems = true;
            }
        }
        JLabel totalL = new JLabel("<html>Total&nbsp;&nbsp;&nbsp;&nbsp;"
                + String.format("%.2f", total) + " £</html>");
        totalL.setBounds(400, 250, 200, 30);
        panel.add(totalL);

        if (threeItems) {
            discount = (total * 0.20);
            JLabel discountLbl = new JLabel(
                    "<html>Three items in same Category Discount (20%)&nbsp;&nbsp;&nbsp;&nbsp;-"
                            + String.format("%.2f", discount) + " £</html>");
            discountLbl.setBounds(150, 300, 400, 25);
            frame.add(discountLbl);
        }

        JLabel finalL = new JLabel("<html>Final Total&nbsp;&nbsp;&nbsp;&nbsp;"
                + String.format("%.2f", (total - discount)) + " £</html>");
        finalL.setFont(new Font("", Font.BOLD, 12));
        finalL.setBounds(370, 350, 400, 25);
        panel.add(finalL);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Implementation of the actionPerformed method from the ActionListener interface.
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("View Cart")){
            shoppingCartFrame();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Add To Cart")){
            int idx = table.getSelectedRow();
            if (idx != -1) {
                Product product = products.get(idx);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Quantity: "));
                if(products.get(idx).getQuantity() > 0 && quantity <= products.get(idx).getQuantity()){
                    products.get(idx).decreaseQuantity(quantity);
                    shoppingCart.addProduct(product, quantity);
                    JOptionPane.showMessageDialog(null, product.getProductName()+" Added To Cart!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Insufficient Quantity!");
                }
            }
        }
        if(e.getActionCommand().equalsIgnoreCase("comboBoxChanged")){
            String category = (String) comboBox.getSelectedItem();
            model.setRowCount(0);
            for (Product product : products) {
                if(category.equalsIgnoreCase("All")){
                    Object[] arr = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
                    model.addRow(arr);
                }
                else if(product.getProductCategory().equalsIgnoreCase(category)){
                    Object[] arr = {product.getProductID(), product.getProductName(), product.getProductCategory(), product.getPrice()};
                    model.addRow(arr);
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Product selectedProduct = products.get(selectedRow);
            String productDetails = generateProductDetails(selectedProduct);
            productL.setText(productDetails);
        }
    }

    private String generateProductDetails(Product product) {
        String category = product.getProductCategory();
        StringBuilder stringBuilder = new StringBuilder("<html>"
                + "<b>Product ID:</b> " + product.getProductID() + "<br/>"
                + "<b>Name:</b> " + product.getProductName() + "<br/>"
                + "<b>Category:</b> " + category + "<br/>"
                + "<b>Price:</b> £" + product.getPrice() + "<br/>");

        if (category.equalsIgnoreCase("Electronics")) {
            Electronics electronics = (Electronics) product;
            stringBuilder.append("<b>Brand:</b> ").append(electronics.getBrand()).append("<br/>")
                    .append("<b>Warranty Period:</b> ")
                    .append(electronics.getWarrantyPeriod()).append("<br/>");
        } else if (category.equalsIgnoreCase("Clothing")) {
            Clothing c = (Clothing) product;
            stringBuilder.append("<b>Size:</b> ").append(c.getSize()).append("<br/>")
                    .append("<b>Colour:</b> ")
                    .append(c.getColor()).append("<br/>");
        }
        stringBuilder.append("<b>Items Available:</b> ").append(product.getQuantity())
                .append("</html>");
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        IDandPassword iDandPassword = new IDandPassword();
        LogInPage logInPage = new LogInPage(iDandPassword.getLoginInfo());
    }
    public GUI() {
        init();
    }

    public void init() {
        IDandPassword iDandPassword = new IDandPassword();
        LogInPage logInPage = new LogInPage(iDandPassword.getLoginInfo());
    }
}
