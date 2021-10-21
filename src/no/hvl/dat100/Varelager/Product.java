package no.hvl.dat100.Varelager;

public class Product {
    private String name;
    private double price;
    private int stock;
    private int productNr;

    /**
     * constructor thingy for products :).
     *
     * @param name name of product.
     * @param price price of product
     * @param stock how much of the product is in stock
     */
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        productNr = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void addStock() {
        stock++;
    }

    public void removeStock() {
        stock--;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductNr() {
        return productNr;
    }

    public void setProductNr(int productNr) {
        this.productNr = productNr;
    }

    /**
     *
     * @return the product but like as a string
     */
    public String toString() {
        return  name + ";" + price + ";" + stock + ";" + productNr;
    }

    /**
     *
     * @return the product but like as a nice looking string
     */
    public String toNiceString() {
        return productNr + ". Product: $" + price + " " + name + ", " + stock + " in stock\n";
    }
}
