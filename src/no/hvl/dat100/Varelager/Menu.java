package no.hvl.dat100.Varelager;

public class Menu {

    private ProductsFile productsFile;
    private User user;

    public Menu(ProductsFile productsFile) {
        this.productsFile = productsFile;
        this.user = new User(new ProductsFile(javax.swing.JOptionPane.showInputDialog("enter username")));
        user.createUser();
    }

    /**
     * launches main menu, letting you pick between different methods(which are all in this class)
     * @return true if the user wants to quit, false otherwise
     */
    public boolean mainMenu() {
        boolean finished = false;
        switch (Integer.parseInt
                (javax.swing.JOptionPane.showInputDialog
                        ("chose smth\n(1) view products\n(2) search products\n(3) add product\n(4) edit product" +
                                "\n(5) buy prduct\n(6) view inventory\n(7) switch user\n(8) quit"))) {
            case 1 -> javax.swing.JOptionPane.showMessageDialog(null, productsFile.readProductsFile().toString());
            case 2 -> search();
            case 3 -> addProduct();
            case 4 -> editProduct();
            case 5 -> buyProduct();
            case 6 -> javax.swing.JOptionPane.showMessageDialog(null, user.toString());
            case 7 -> {
                user = new User(new ProductsFile(javax.swing.JOptionPane.showInputDialog("enter new nme")));
                user.createUser();
            }
            default -> finished = true;
        }
        return finished;
    }

    /**
     * searches for a product in storage
     */
    public void search() {
        String search = javax.swing.JOptionPane.showInputDialog("search");
        if (productsFile.searchProductsFile(search).toString().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "prduct not found");
        } else {
            javax.swing.JOptionPane.showMessageDialog
                    (null, productsFile.searchProductsFile(search));
        }
    }

    /**
     * adds a product to storage
     */
    public void addProduct() {
        boolean correctProduct = false;
        String productName;
        int productPrice;
        int productStock;

        do {
            productName = javax.swing.JOptionPane.showInputDialog("name:");
            productPrice = Integer.parseInt
                    (javax.swing.JOptionPane.showInputDialog("price:"));
            productStock = Integer.parseInt
                    (javax.swing.JOptionPane.showInputDialog("stock:"));

            if (javax.swing.JOptionPane.showInputDialog("is this ok? (Y/N)\n" +
                    "name:" + productName + "\nprice:" + productPrice + "\nstock:" + productStock).toLowerCase().startsWith("Y")) {
                correctProduct = true;
            }
        } while (correctProduct);
        productsFile.writeProductToFile(new Product(productName, productPrice, productStock));
    }

    /**
     * edits a product in storage
     */
    public void editProduct() {
        int productNr = Integer.parseInt
                (javax.swing.JOptionPane.showInputDialog
                        (productsFile.readProductsFile().toString() + "\n enter the number of the prduct u wanna edit:"));
        Product oldProduct = productsFile.readProductsFile().findProduct(productNr);

        double newPrice = Double.parseDouble(javax.swing.JOptionPane.showInputDialog(oldProduct.toNiceString() + "\nnew price:"));
        String newName = javax.swing.JOptionPane.showInputDialog(oldProduct.toNiceString() + "\nnew name:");
        int newStock = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(oldProduct.toNiceString() + "\nnew stock:"));

        Product newProduct = new Product(newName, newPrice, newStock);
        productsFile.editProductInFile(newProduct, productNr);

        newProduct.setProductNr(productNr);
        javax.swing.JOptionPane.showMessageDialog(null, "product is now:\n" + newProduct.toNiceString());
    }

    /**
     * buys a product in storage
     */
    public void buyProduct() {
        int productNr = Integer.parseInt
                (javax.swing.JOptionPane.showInputDialog
                        (productsFile.readProductsFile().toString() + "\n enter the number of the prduct u wanna buy:"));
        Product product = productsFile.readProductsFile().findProduct(productNr);

        if (product.getStock() > 0 && user.getFunds() >= product.getPrice()) {
            user.buyProduct(product);

            ProductStorage products = productsFile.readProductsFile();
            products.findProduct(productNr).removeStock();
            productsFile.writeProductsFile(products);
        } else if (user.getFunds() < product.getPrice()) {
            javax.swing.JOptionPane.showMessageDialog(null, "u dont have enough money");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "product is not in stock");
        }
    }
}
