package com.example.network.model.ip;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.network.model.entities.Address;
import com.example.network.model.entities.Company;
import com.example.network.model.entities.Geo;
import com.example.network.model.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JsonPlaceholderApi {
    private String urlPath;

    public JsonPlaceholderApi(String urlPath) {
        this.urlPath = urlPath;
    }

    public ArrayList<User> getUserList() throws IOException, JSONException {
        String userJsonStroke = getJsonFromServer(urlPath, 1000);
        JSONArray usersRoot = new JSONArray(userJsonStroke);

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i< usersRoot.length();i++) {

            JSONObject userRoot = usersRoot.getJSONObject(i);

            JSONObject userAddress = userRoot.getJSONObject("address");
            JSONObject userCompany = userRoot.getJSONObject("company");
            JSONObject addressGeo = userAddress.getJSONObject("geo");

            int userId = userRoot.getInt("id");
            String userName = userRoot.getString("name");
            String userNameName = userRoot.getString("username");
            String userEmail = userRoot.getString("email");
            String userPhone = userRoot.getString("phone");
            String userWebSite = userRoot.getString("website");

            String addressStreet = userAddress.getString("street");
            String addressSuite = userAddress.getString("suite");
            String addressCity = userAddress.getString("city");
            String addressZipcode = userAddress.getString("zipcode");

            double geoLat = addressGeo.getDouble("lat");
            double getLon = addressGeo.getDouble("lng");

            String companyName = userCompany.getString("name");
            String companyCatchPhrase = userCompany.getString("catchPhrase");
            String companyBs = userCompany.getString("bs");

            Geo geo = new Geo(geoLat, getLon);
            Address address = new Address(addressStreet, addressSuite, addressCity, addressZipcode, geo);
            Company company = new Company(companyName, companyCatchPhrase, companyBs);
            User user = new User(userId, userName, userNameName, userEmail, address, userPhone, userWebSite, company);
            users.add(user);
        }


        return users;
    }

    private String getJsonFromServer(String urlPath, int timeout) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();

        int serverResponseCode = connection.getResponseCode();
        switch (serverResponseCode) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String tmpLine;
                while ((tmpLine = br.readLine()) != null) {
                    sb.append(tmpLine).append("\n");
                }
                br.close();
                return sb.toString();
        }
        return null;
    }
}