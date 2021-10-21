package no.hvl.dat100.Varelager;

import java.io.*;
import java.util.Scanner;

public class ProductsFile {
    private File productFile;

    ProductsFile(String fileName) {
        this.productFile = new File(fileName);
    }

    public File getProductFile() {
        return productFile;
    }

    public void writeProductToFile(Product product) {
        ProductStorage newStorage = readProductsFile();
        newStorage.addProduct(product);
        writeProductsFile(newStorage);
    }

    public void editProductInFile(Product newProduct, int productNr) {
        ProductStorage newStorage = readProductsFile();
        newStorage.editProduct(newProduct, productNr);
        writeProductsFile(newStorage);
    }

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
