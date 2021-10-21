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

    public Product[] getAllProducts() {
        Product[] allProducts = new Product[amount];
        System.arraycopy(products,0,allProducts,0,amount);
        return allProducts;
    }

    public void addProduct(Product product) {
        products[amount++] = product;
        product.setProductNr(amount);
    }

    public String toString() {
        String productsStr = "";
        for (Product p : getAllProducts()) {
            productsStr += p.toNiceString();
        }
        return productsStr;
    }

    public void editProduct(Product product, int productNr) {
        products[productNr-1] = product;
    }

    public Product findProduct(int productNr) {
        return products[productNr-1];
    }

    public Product findProduct(Product product) {
        for (Product p : getAllProducts()) {
            if (p.getName().equals(product.getName())) {
                return p;
            }
        }
        return null;
    }
}
