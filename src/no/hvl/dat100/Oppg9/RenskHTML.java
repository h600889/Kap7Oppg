package no.hvl.dat100.Oppg9;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenskHTML {
    public static String cleanHTML(String dirtyHTML) {
        Scanner parseHTML = new Scanner(dirtyHTML);
        String cleanedHTML = "";
        while (parseHTML.hasNext("^(<(.*?)>|  )")) {
            String line = parseHTML.nextLine();
            cleanedHTML += line;
        }
        return cleanedHTML;
    }
}
