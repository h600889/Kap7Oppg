package no.hvl.dat100.Oppg5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oppg5 {
    public static void main(String[] args) {
        System.out.println(compareFiles("file1", "file2"));
    }

    public static String compareFiles(String fileOne, String fileTwo) {

        String differentLines = "";
        int lineNr = 0;
        try {
            Scanner readFileOne = new Scanner(new File(fileOne));
            Scanner readFileTwo = new Scanner(new File(fileTwo));

            while (readFileOne.hasNextLine() && readFileTwo.hasNextLine()) {
                lineNr++;
                String lineOne = readFileOne.nextLine();
                String lineTwo = readFileTwo.nextLine();
                if (!lineOne.equals(lineTwo)) {
                    differentLines += "Line #" + lineNr + "\nFile one: " + lineOne + "\nFile two: " + lineTwo + "\n-----\n";
                }
            }
            readFileOne.close();
            readFileTwo.close();

            if(differentLines.isEmpty()) {
                return "No differences found.";
            } else {
                return "Following differences found:\n-----\n" + differentLines;
            }
        } catch (FileNotFoundException e) {
            return "WHAT IS THIS FILE IT DOES NOT EXIST LEAVE ME ALONE";
        }

    }
}
