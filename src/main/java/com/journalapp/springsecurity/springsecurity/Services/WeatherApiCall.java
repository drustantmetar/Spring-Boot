package com.journalapp.springsecurity.springsecurity.Services;


import com.journalapp.springsecurity.springsecurity.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiCall {
    @Autowired
    RestTemplate restTemplate;

    public static final String key="05cc381d504b7d53c47f090ab377ede2";
    public static final String API="http://api.weatherstack.com/current?access_key=keyvalue&query=city";


    public WeatherResponse WeatherService(String city)
    {
        String finalAPI = API.replace("keyvalue",key).replace("city",city);
        System.out.println(finalAPI);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
