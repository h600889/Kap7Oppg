package no.hvl.dat100.Oppg3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oppg3 {
    public static void main(String[] args) {
        javax.swing.JOptionPane.showMessageDialog(null, wordLengths(javax.swing.JOptionPane.showInputDialog("enter file name!!")));
    }

    public static String wordLengths(String fileName) {
        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;
        try {
            Scanner readFile = new Scanner(new File(fileName));
            while (readFile.hasNext()) {
                String word = readFile.next();
                if(word.length() <= 4) {
                    shortWords++;
                } else if (word.length() <= 10) {
                    mediumWords++;
                } else {
                    longWords++;
                }
            }
            readFile.close();
            int totalWords = shortWords + mediumWords + longWords;
            double shortWordPercentage = (double) shortWords/totalWords*100;
            double mediumWordPercentage = (double) mediumWords/totalWords*100;
            double longWordPercentage = (double) longWords/totalWords*100;
            return "short words: " + shortWords + " (" + (int) (shortWordPercentage+0.5) + "%)\n" +
                    "medium words: " + mediumWords + " (" + (int) (mediumWordPercentage+0.5) + "%)\n" +
                    "long words: " + longWords + " (" + (int) (longWordPercentage+0.5) + "%)\n";
        } catch (FileNotFoundException e) {
            return "cant find that file :(";
        }
    }
}
