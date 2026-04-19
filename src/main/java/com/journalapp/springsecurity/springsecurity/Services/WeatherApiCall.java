package com.journalapp.springsecurity.springsecurity.Services;


import com.journalapp.springsecurity.springsecurity.api.response.WeatherResponse;
import com.journalapp.springsecurity.springsecurity.cache.AppCache;
import com.journalapp.springsecurity.springsecurity.constant.constantValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiCall {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AppCache appCache;
//    public static final String key="7bae7d43e1b7906a760aac6e45d3108c";

    @Autowired
    RedisService redisService;

    @Value("${weather.api.key}")
    private String key;
    // now the API and its value store in DB and fetch from it. .. so comment below
    //  private String API="http://api.weatherstack.com/current?access_key=<key>&query=<city>";

    public WeatherResponse WeatherService(String city)
    {
        WeatherResponse weatherResponse = redisService.get("weather_of_"+city, WeatherResponse.class);
        if(weatherResponse!=null)
        {
            return weatherResponse;
        }
        else{
            // keys = enum declared into the AppCache


            String finalAPI= appCache.Cache.get(AppCache.keys.WEATHER_API_KEY.toString()).replace(constantValues.key,key).replace(constantValues.value,city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            if(response!=null)
            {
                redisService.set("weather_of"+city,response,300l);
            }
            return response.getBody();
        }

    }
}



// String finalAPI = appCache.replace("<key>",key).replace("<city>",city);
// enum declared into the AppCache = AppCache.keys.WEATHER_API_KEY.toString() so used here
// AppCache.keys.WEATHER_API_KEY.toString() or direct mentioned DB key values ("WEATHER_API_KEY")