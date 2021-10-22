package no.hvl.dat100.Varelager;

public class Main {
    public static void main(String[] args) {
        ProductsFile productsFile = new ProductsFile("Products");

        boolean finished;
        Menu menu = new Menu(productsFile);
        do {
            finished = menu.mainMenu();
        } while (!finished);


    }
}
