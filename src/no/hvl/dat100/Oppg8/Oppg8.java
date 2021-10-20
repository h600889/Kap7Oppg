package no.hvl.dat100.Oppg8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oppg8 {
    public static void main(String[] args) {
        int[] wordCounts = wordsUnderTwelve("hihi");
        System.out.println("1: " + wordCounts[1] +
                "\n2: " + wordCounts[2] +
                "\n3: " + wordCounts[3] +
                "\n4: " + wordCounts[4] +
                "\n5: " + wordCounts[5] +
                "\n6: " + wordCounts[6] +
                "\n7: " + wordCounts[7] +
                "\n8: " + wordCounts[8] +
                "\n9: " + wordCounts[9] +
                "\n10: " + wordCounts[10] +
                "\n11: " + wordCounts[11] +
                "\n>12: " + wordCounts[0]);
    }

    public static int[] wordsUnderTwelve(String fileName) {
        try {
            Scanner readFile = new Scanner(new File(fileName));
            int[] wordCounts = new int[12];
            readFile.useDelimiter("(\\.|,| |\n)+");
            while (readFile.hasNext()) {
                String line = readFile.next();
                if (line.length() >= 12) {
                    wordCounts[0]++;
                } else {
                    wordCounts[line.length()]++;
                }
            }
            return wordCounts;
        } catch (FileNotFoundException e) {
            System.out.println("aa!!!!");
        }
        return null;
    }
}
