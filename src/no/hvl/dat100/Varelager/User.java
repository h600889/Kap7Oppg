package no.hvl.dat100.Varelager;

import java.io.IOException;

/**
 * User for handling users who can purchase and sell products.
 */
public class User {
    private ProductsFile possesions;
    private String name;
    private double funds;

    /**
     * constructor for user
     * @param possesions file storing information about the user
     */
    public User(ProductsFile possesions) {
        this.possesions = possesions;
        this.name = possesions.getProductFile().getName();
        //create user if doesnt exist, look for funds in the file if the file exists
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
     * sell a product from the user's inventory
     * @param product product to be sold
     */
    public void sellProduct(Product product) {
        ProductStorage products = possesions.readProductsFile();
        Product foundProduct = products.findProduct(product);
        if (foundProduct == null) { return; }
        foundProduct.removeStock();
        possesions.writeProductsFile(products);
        addFunds(product.getPrice());
        clean(product);
    }

    /**
     * puts information abt user and sutff in a string
     * @return string with user and product list
     */
    public String toString(ProductStorage productStorage) {
        ProductStorage productsStorage = possesions.readProductsFile();
        String possesionsStr = "name: " + productsStorage.findProduct(1).getName() +
                " - balance: " + productsStorage.findProduct(1).getPrice() + "\n";
        for (Product p : getProducts().getAllProducts()) {
            possesionsStr += "\n" + p.getProductNr() + ". " + p.getName() + " - " + p.getStock() + " (" + productStorage.findProduct(p).getPrice() + ")";
        }
        return possesionsStr;
    }

    /**
     * returns only products, not information about user
     * @return productstorage containing every line from possesions except the first (the one about user)
     */
    public ProductStorage getProducts() {
        ProductStorage newStorage = possesions.readProductsFile();
        newStorage.remove(newStorage.findProduct(1));
        return newStorage;
    }

    /**
     * gets rid of a product if its stock is 0
     * @param product product to be removed (if stock is 0)
     */
    public void clean(Product product) {
        ProductStorage cleanProducts = possesions.readProductsFile();
        if (cleanProducts.findProduct(product).getStock() <= 0) { cleanProducts.remove(product); }
        possesions.writeProductsFile(cleanProducts);
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
                newUser.addProduct(new Product(possesions.getProductFile().getName(), funds, 1));
                possesions.writeProductsFile(newUser);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
