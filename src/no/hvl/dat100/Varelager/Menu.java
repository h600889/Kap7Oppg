package no.hvl.dat100.Varelager;

public class Menu {

    private ProductsFile productsFile;
    private User user;

    public Menu(ProductsFile productsFile) {
        this.productsFile = productsFile;
        this.user = new User(new ProductsFile(javax.swing.JOptionPane.showInputDialog("enter username")));
    }

    /**
     * launches main menu, letting you pick between different methods(which are all in this class)
     * @return true if the user wants to quit, false otherwise
     */
    public boolean mainMenu() {
        boolean finished = false;
        try {
            switch (Integer.parseInt
                    (javax.swing.JOptionPane.showInputDialog
                            ("""
                                    chose smth
                                    (1) view products
                                    (2) search products
                                    (3) view inventory
                                    (4) edit product
                                    (5) add product
                                    (6) switch user
                                    (7) quit"""))) {
                case 1 -> buyProduct(productsFile.readProductsFile());
                case 2 -> buyProduct(search());
                case 3 -> sellProduct(user.getProducts());
                case 4 -> editProduct();
                case 5 -> addProduct();
                case 6 -> user = new User(new ProductsFile(javax.swing.JOptionPane.showInputDialog("enter new nme")));
                default -> finished = true;
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "thats not a number"); return false;
        }

        return finished;
    }

    /**
     * searches for a product in storage
     */
    public ProductStorage search() {
        String search = javax.swing.JOptionPane.showInputDialog("search");
        if (productsFile.searchProductsFile(search).toString().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "prduct not found");
        } else {
            return productsFile.searchProductsFile(search);
        }
        return null;
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
    public void buyProduct(ProductStorage productsList) {
        if (productsList == null) { return; }
        int productNr;
        try {
            productNr = Integer.parseInt
                    (javax.swing.JOptionPane.showInputDialog
                            (productsList + "\n enter the number of the prduct u wanna buy:"));
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "u didnt neter a number"); return;
        }
        if (productNr <= 0) { return; }
        Product product = productsList.findProduct(productNr);

        if (product.getStock() <= 0) { javax.swing.JOptionPane.showMessageDialog(null, "product not in stock"); return; }
        if (user.getFunds() < product.getPrice()) { javax.swing.JOptionPane.showMessageDialog(null, "u dont have enough money"); return; }

        user.buyProduct(product);

        ProductStorage products = productsFile.readProductsFile();
        products.findProduct(productNr).removeStock();
        productsFile.writeProductsFile(products);
    }

    public void sellProduct(ProductStorage productsList) {
        if (productsList == null) { return; }
        int productNr;
        try {
            productNr = Integer.parseInt
                    (javax.swing.JOptionPane.showInputDialog
                            (user.toString(productsFile.readProductsFile()) + "\n enter the number of the prduct u wanna sell:"));
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "u didnt neter a number"); return;
        }
        if (productNr <= 0) { return; }
        Product product = productsList.findProduct(productNr);

        if (product.getStock() <= 0) { javax.swing.JOptionPane.showMessageDialog(null, "product not in stock"); return; }

        user.sellProduct(product);

        ProductStorage products = productsFile.readProductsFile();
        products.findProduct(productNr).addStock();
        productsFile.writeProductsFile(products);
    }
}
