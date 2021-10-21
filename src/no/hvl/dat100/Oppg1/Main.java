package no.hvl.dat100.Oppg1;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean readOrWrite = javax.swing.JOptionPane.showInputDialog("(R)ead or (W)rite").toUpperCase().startsWith("R");
        String message = "";
        String fileContents = "";
        File fileName = new File (javax.swing.JOptionPane.showInputDialog("Enter filename:"));

        try {
             if (readOrWrite) {
                 Scanner readFile = new Scanner(fileName);
                 while (readFile.hasNextLine()) {
                     fileContents += readFile.nextLine() + "\n";
                 }
                 readFile.close();
                 if (fileContents.toLowerCase().contains("zoe") || fileContents.toLowerCase().contains("ivy") ||
                         fileContents.toLowerCase().contains("gay") || fileContents.toLowerCase().contains("aaa")) {
                     message = fileContents.length() + " characters.\nwarning: contents r gay\n\n" + fileContents;
                 } else {
                     message = fileContents.length() + " characters.\n\n" + fileContents;
                 }
             } else {
                 if (!fileName.exists()) {
                     fileName.createNewFile();
                 }
                PrintWriter writeFile = new PrintWriter(new FileWriter(fileName, true));
                fileContents = javax.swing.JOptionPane.showInputDialog("write!!!");
                writeFile.println(fileContents);
                writeFile.close();
                if (fileContents.toLowerCase().contains("zoe") || fileContents.toLowerCase().contains("ivy") ||
                        fileContents.toLowerCase().contains("gay") || fileContents.toLowerCase().contains("aaa")) {
                    message = "gay has been written!!\nuve written " + fileContents.length() + " characters that r GAY!!!!";
                } else {
                    message = "file has been written!\n" + fileContents.length() + " characters";
                }
             }
         } catch (FileNotFoundException e) {
             message = "File doesn't exist!";
         } catch (FileAlreadyExistsException e) {
             message = "File already existS!!!!";
         } catch (IOException e) {
             message = "smth went wrong whoopsie";
         } finally {
             javax.swing.JOptionPane.showMessageDialog(null, message);
         }
    }

}
