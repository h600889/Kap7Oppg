package no.hvl.dat100.Oppg9;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class NettsideLeser {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://zoebat.gay");
            URLConnection connection = url.openConnection();
            Scanner reader = new Scanner(new InputStreamReader(connection.getInputStream()));
            while (reader.hasNextLine()) {
                System.out.println(RenskHTML.cleanHTML(reader.nextLine()));
            }
            reader.close();
        } catch (MalformedURLException e) {
            System.out.println("URL is NOT valid!!!!!");
        } catch (IOException e) {
            System.out.println("FUCK");
        }
    }
}
