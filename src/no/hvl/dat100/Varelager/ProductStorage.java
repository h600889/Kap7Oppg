package no.hvl.dat100.Varelager;

public class ProductStorage {
    private Product[] products;
    private int amount;

    public ProductStorage() {
        products = new Product[100];
        amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * makes an array that cointains all the products.
     * @return a Product array of length amount, containting all Products in storage.
     */
    public Product[] getAllProducts() {
        Product[] allProducts = new Product[amount];
        System.arraycopy(products,0,allProducts,0,amount);
        return allProducts;
    }

    /**
     * adds a product to the end of storage
     * @param product product to be added
     */
    public void addProduct(Product product) {
        products[amount++] = product;
        product.setProductNr(amount);
    }

    /**
     * converts storage to a string, for use in menu
     * @return storage as a string
     */
    public String toString() {
        String productsStr = "";
        for (Product p : getAllProducts()) {
            productsStr += p.toNiceString();
        }
        return productsStr;
    }

    /**
     * replaces a product, using productnumber to find the correct product to replace
     * @param product replacement product
     * @param productNr productnumber to be replaced
     */
    public void editProduct(Product product, int productNr) {
        products[productNr-1] = product; product.setProductNr(productNr);
    }

    /**
     * finds a product using its productnumber
     * @param productNr productnr to find
     * @return product corresponding to productnr
     */
    public Product findProduct(int productNr) {
        return products[productNr-1];
    }

    /**
     * finds a specific product
     * @param product product to find
     * @return product if it exists, null otherwise.
     */
    public Product findProduct(Product product) {
        if (product == null) { return null; }
        for (Product p : getAllProducts()) {
            if (p.getName().equals(product.getName())) {
                return p;
            }
        }
        return null;
    }

    /**
     * removes a product from storage
     * @param product product to be removed
     * @return true if product was removed, false otherwise
     */
    public boolean remove(Product product) {
        if (findProduct(product) == null) { return false; }
        boolean isRemoved = false;
        int i = 0;
        while (!isRemoved && i < amount) {
            if (products[i].equals(product)) {
                products[i] = null;
                isRemoved = true;
            }
            i++;
        }


        if (isRemoved) {
            for (int j = 1; j < amount; j++) {
                if (products[j-1] == null) {
                    products[j-1] = products[j];
                    products[j] = null;
                }
            }
        }
        amount--;
        return isRemoved;
    }
}
