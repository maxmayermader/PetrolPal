package org.example.map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
import io.swagger.annotations.ApiOperation;
import org.example.account.accountInfo;
import org.example.gas.GasController;
import org.example.gas.GasInfo;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Geoapify {

    public  static double lon;

    public  static double lat;
    public static void addessConvert(GasInfo info) {
        String address = "https://api.geoapify.com/v1/geocode/search?text=";
        String Keyy = "eff5d691e2444c8eb5c093ea1e661be2";
        String location = info.getAddress();
        // Update the URL
        String url = address + location + "&apiKey=" + Keyy;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode features = rootNode.get("features");
            if (features != null && features.isArray() && features.size() > 0) {
                JsonNode firstFeature = features.get(0);
                JsonNode geometry = firstFeature.get("geometry");
                if (geometry != null) {
                    JsonNode coordinates = geometry.get("coordinates");
                    if (coordinates != null && coordinates.isArray() && coordinates.size() == 2) {
                        info.setLon(coordinates.get(0).asDouble());
                        info.setLat(coordinates.get(1).asDouble());
                        //lat = coordinates.get(1).asDouble();
                        //.setLat(lat);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }

