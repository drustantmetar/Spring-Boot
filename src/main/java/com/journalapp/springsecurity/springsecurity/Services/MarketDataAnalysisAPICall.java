package com.journalapp.springsecurity.springsecurity.Services;

import com.journalapp.springsecurity.springsecurity.api.response.MarketAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketDataAnalysisAPICall {
    public static final String apikey1="1Q3h5GFWxupaMseOWutAXVhcfm9pp47n";
    // there is fault is kye My code is correct
    public static final String API = "https://api.massive.com/v3/reference/dividends?apiKey=apikey1";

    // The RestTemplete is in - built provide by spring boot
    @Autowired
    private RestTemplate restTemplate;

    public MarketAnalysisResponse getInfo()
    {
        String finalAPI = API.replace("apikey",apikey1);
        System.out.println(finalAPI);
        // To convert JSON response into the java object is call Deserialization
        ResponseEntity<MarketAnalysisResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, MarketAnalysisResponse.class);

        return response.getBody();

    }
}
