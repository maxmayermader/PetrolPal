package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestAPI {
    /**
    //https://developer.nrel.gov/docs/api-key/
    private final String key = "HUEewMlbB0EX4kBTvatOFtnuTGBjiEZfqfJC3zZs";
    private final String URL = "https://developer.nrel.gov/api/alt-fuel-stations/v1.json";
//42.0097969728638, -93.65909532007964 //need to remove some numbers to make it work, -shorten

    @GetMapping("/getNearestStation") //http://localhost:8080/getNearestStation?location=24.0097, -94.6594&radius=5
    public String getNearestStation(@RequestParam String location, @RequestParam int radius) {
        String url = URL + "?location=" + location + "&radius=" + radius + "&limit=1&api_key=" + key;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }
    @GetMapping("/getStation")
    public String getStation() {
        String url = URL + ".json?limit=1&api_key=" + key;


        return "GET Request to NREL API: " + url;
    }
**/
}