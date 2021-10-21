package no.hvl.dat100.Varelager;

import java.io.File;
import java.io.IOException;

public class User {
    private ProductsFile possesions;
    private String name;
    private double funds;

    //make anoter constructor for when user doesnt already exist
    public User(ProductsFile possesions) {
        this.possesions = possesions;
        this.name = possesions.readProductsFile().findProduct(1).getName();
        this.funds = possesions.readProductsFile().findProduct(1).getPrice();
    }

    public void removeFunds(double amt) {
        possesions.editProductInFile(new Product(name,funds-amt,0), 1);
        funds -= amt;
    }

    public void addFunds(double amt) {
        possesions.editProductInFile(new Product(name,funds+amt,0), 1);
        funds += amt;
    }

    public void buyProduct(Product product) {
        ProductStorage products = possesions.readProductsFile();
        if (products.findProduct(product) != null) {
            products.findProduct(product).addStock();
        } else {
            products.addProduct(product);
            products.findProduct(product).setStock(1);
            products.findProduct(product).setPrice(0);
        }
        possesions.writeProductsFile(products);
        removeFunds(product.getPrice());
    }

    public void createUser() {
        if (!possesions.getProductFile().exists()) {
            try {
                possesions.getProductFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
