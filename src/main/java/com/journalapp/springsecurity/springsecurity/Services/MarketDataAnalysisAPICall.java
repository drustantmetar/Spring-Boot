package com.journalapp.springsecurity.springsecurity.Services;

import com.journalapp.springsecurity.springsecurity.Model.users;
import com.journalapp.springsecurity.springsecurity.api.response.MarketAnalysisResponse;
import com.sun.net.httpserver.Headers;
import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

        // we can send also the post call to thrity party API

//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Key","Value");
//        String a="";
//        users user = users.builder().username("Drustant").password("Drustant");
//        HttpEntity<users> httpEntity = new HttpEntity(user,headers);
//        ResponseEntity<MarketAnalysisResponse> response1 = restTemplate.exchange(finalAPI,HttpMethod.POST,httpEntity,MarketAnalysisResponse.class);

        return response.getBody();
    }
}
