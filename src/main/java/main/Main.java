/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import model.Country;
import org.json.JSONObject;

/**
 *
 * @author sergio
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL client = new URL("http://services.groupkt.com/country/get/iso2code/IN");
        URLConnection connection = client.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String rest = new String();
        String line;
        while ((line = br.readLine()) != null) {
            rest += line;
        }
        
        System.out.println("response from the server: " + rest);
        br.close();
        
        JSONObject jSONObject = new JSONObject(rest);
        System.out.println("Json object: " + jSONObject);
        JSONObject  objRest = jSONObject.getJSONObject("RestResponse").getJSONObject("result");
        System.out.println("Object Rest: " + objRest);
        System.out.println("Object Rest toString: " + objRest.toString());
        
        Gson gson = new Gson();
        Country country = gson.fromJson(objRest.toString(), Country.class);
        System.out.println(country.getName());
        System.out.println(country.getAlpha2_code());
        System.out.println(country.getAlpha3_code());
    }
}
