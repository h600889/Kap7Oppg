package no.hvl.dat100.Varelager;

import java.io.*;
import java.util.Scanner;

public class ProductsFile {
    private File productFile;

    ProductsFile(String fileName) {
        this.productFile = new File(fileName);
    }


    public File getProductFile() { return productFile; }

    /**
     * adds a product and then writes it to the productsfile.
     * @param product product to be added
     */
    public void writeProductToFile(Product product) {
        ProductStorage newStorage = readProductsFile();
        newStorage.addProduct(product);
        writeProductsFile(newStorage);
    }

    /**
     * replaces a product with another
     * @param newProduct replacement
     * @param productNr productnr of product to be replaced
     */
    public void editProductInFile(Product newProduct, int productNr) {
        ProductStorage newStorage = readProductsFile();
        newStorage.editProduct(newProduct, productNr);
        writeProductsFile(newStorage);
    }

    /**
     * writes a ProductStorage to the file (will overwrite preexisting file)
     * @param products products to be written to the file
     */
    public void writeProductsFile(ProductStorage products) {
        try {
            PrintWriter writeFile = new PrintWriter(productFile);
            for (Product p : products.getAllProducts()) {
                writeFile.println(p.toString());
            }
            writeFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("no!!!! thats not a real file elave me aALOENE");
        }
    }

    /**
     * returns a productstorage containing the same contents as the file
     * @return ProductStorage object
     */
    public ProductStorage readProductsFile() {
        ProductStorage products = new ProductStorage();
        try {
            Scanner readFile = new Scanner(productFile);
            while (readFile.hasNextLine()) {
                Scanner readProduct = new Scanner(readFile.nextLine());
                readProduct.useDelimiter(";");
                Product newProduct = new Product(readProduct.next(),
                        Double.parseDouble(readProduct.next()),Integer.parseInt(readProduct.next()));
                products.addProduct(newProduct);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("FUCK");
        }
        return products;
    }

    /**
     * searches for a certain searchterm and then returns a ProductStorage containing only products matching the searchterm
     * @param search search term to look for
     * @return ProductStorage containing products matching the searchterm
     */
    public ProductStorage searchProductsFile(String search) {
        ProductStorage matchedProducts = new ProductStorage();
        try {
            Scanner readFile = new Scanner(productFile);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                if (line.contains(search)) {

                    Scanner readProduct = new Scanner(line);
                    readProduct.useDelimiter(";");
                    Product newProduct = new Product(readProduct.next(),
                            Double.parseDouble(readProduct.next()),Integer.parseInt(readProduct.next()));
                    matchedProducts.addProduct(newProduct);
                }
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("A");
        }
        return matchedProducts;
    }

}
