package no.hvl.dat100.Oppg2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Oppg2 {
    public static void main (String[] args) {
        resFile(new File("tall"));
    }

    public static void resFile(File f) {
        File newFile = new File("Res" + f.getName());

        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            Scanner readFile = new Scanner(f);
            PrintWriter writeFile = new PrintWriter(newFile);
            int ints = 0;
            int sum = 0;
            while (readFile.hasNextLine()) {
                Scanner findInts = new Scanner(readFile.nextLine());
                while (findInts.hasNextInt()) {
                    ints++;
                    sum += findInts.nextInt();
                }
                writeFile.println("amt of #: " + ints + ", sum: " + sum);
                sum = 0;
                ints = 0;
            }
            writeFile.close();
            readFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
