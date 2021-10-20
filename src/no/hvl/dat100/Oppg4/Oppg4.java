package no.hvl.dat100.Oppg4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Oppg4 {
    public static void main(String[] args) {
        removeEmptyLines("newfile");
    }

    public static void removeEmptyLines (String fileName) {
        File file = new File(fileName);

        try {
            Scanner readFile = new Scanner(file);
            String string = "";
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                if (!line.isEmpty()) {
                    string += line + "\n";
                }
            }
            readFile.close();
            PrintWriter writeFile = new PrintWriter(file);
            writeFile.println(string);
            writeFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
