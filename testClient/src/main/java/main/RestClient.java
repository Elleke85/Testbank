package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RestClient {

    public RestClient() {
        super();
    }

    public static void main(String[] args) {
        RestClient client = new RestClient();
        Scanner input = new Scanner (System.in);

        // koppelen

        System.out.print("Kies 1 voor nieuwe pintransfer, kies 2 voor printen dagoverzicht: ");
        int modus = input.nextInt();

        while (modus == 1)  {
            client.doPinTransfer();
            System.out.print("Kies 1 voor nieuwe pintransfer, kies 2 voor printen dagoverzicht: ");
            modus = input.nextInt();
        }


    }

    private void doPinTransfer() {
        Scanner input = new Scanner (System.in);
        System.out.println("In te vullen door winkelier:");
        System.out.print("Hoeveel moet worden overgemaakt? : ");
        double amount = input.nextDouble();

        System.out.println();

        System.out.println("In te vullen door klant:");
        System.out.print("Van welk rekeningnr moet geld worden afgeschreven? : ");
        int account = input.nextInt();
        System.out.print("Vul je wachtwoord in: ");
        String password = input.next();
        transferMoney(account,amount, password);

    }

    private void transferMoney (int account, double amount, String password) {
        URL url;
        HttpURLConnection con;
        try {
            url = new URL("http://localhost:8081/account/" + account + "/" + amount + "/" + password);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream response = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(response);
            BufferedReader in = new BufferedReader(reader);
            String answer = in.readLine();
            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doExistsrequest (int id) {
        URL url;
        HttpURLConnection con;
        StringBuffer content = null; // voor opvangen resultaat?
        try {
            url = new URL("http://localhost:8082/customer/" + id);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content != null) {
            System.out.println(content.toString());
        }
    }


}
