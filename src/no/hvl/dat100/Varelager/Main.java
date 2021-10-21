package no.hvl.dat100.Varelager;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ProductsFile productsFile = new ProductsFile("Products");
        User user = new User(new ProductsFile(javax.swing.JOptionPane.showInputDialog("enter username")));
        user.createUser();

        boolean finished;
        Menu menu = new Menu(productsFile, user);
        do {
            finished = menu.mainMenu();
        } while (!finished);


    }
}
