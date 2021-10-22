package no.hvl.dat100.Varelager;

import java.io.IOException;

public class User {
    private ProductsFile possesions;
    private String name;
    private double funds;

    public User(ProductsFile possesions) {
        this.possesions = possesions;
        this.name = possesions.getProductFile().getName();
        //only looks for funds in the file if the file exists
        if (!createUser()) {
            this.funds = possesions.readProductsFile().findProduct(1).getPrice();
        }
    }

    public ProductsFile getPossesions() { return possesions; }

    public double getFunds() { return funds; }

    /**
     * removes funds from the user's balance
     * @param amt amount to be removed
     */
    public void removeFunds(double amt) {
        funds -= amt;
        possesions.editProductInFile(new Product(name,funds,0), 1);
    }

    /**
     * adds funds to the user's balance
     * @param amt amount to be added
     */
    public void addFunds(double amt) {
        funds += amt;
        possesions.editProductInFile(new Product(name,funds,0), 1);
    }

    /**
     * buys product from storage. product gets removed from storage, added to user's possesions, and the price gets reducted from user.
     * @param product product to be removed
     */
    public void buyProduct(Product product) {
        ProductStorage products = possesions.readProductsFile();
        Product foundProduct = products.findProduct(product);
        if (foundProduct != null) {
            foundProduct.addStock();
        } else {
            products.addProduct(product);
            products.findProduct(product).setStock(1);
        }
        possesions.writeProductsFile(products);
        removeFunds(product.getPrice());
    }

    /**
     * puts information abt user and sutff in a string
     * @return string with user and product list
     */
    public String toString() {
        ProductStorage productsStorage = possesions.readProductsFile();
        String possesionsStr = "name: " + productsStorage.findProduct(1).getName() +
                " - balance: " + productsStorage.findProduct(1).getPrice() + "\n";
        productsStorage.remove(productsStorage.findProduct(1));
        for (Product p : productsStorage.getAllProducts()) {
            possesionsStr += "\n" + p.getName() + " - " + p.getStock();
        }
        return possesionsStr;
    }

    /**
     * checks if it needs to create a new file for the user
     * @return true if it made a new file, false otherwise
     */
    public boolean createUser() {
        if (!possesions.getProductFile().exists()) {
            try {
                //creates file for user
                funds = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("how muc money"));
                possesions.getProductFile().createNewFile();
                ProductStorage newUser = possesions.readProductsFile();
                newUser.addProduct(new Product(possesions.getProductFile().getName(), funds, 0));
                possesions.writeProductsFile(newUser);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
