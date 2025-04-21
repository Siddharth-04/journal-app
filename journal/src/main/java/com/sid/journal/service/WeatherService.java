package com.sid.journal.service;

import com.sid.journal.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey = "efbea67d5457e3821860c9d8bbf11915";
    private static final String API = "http://api.weatherstack.com/current?access_key=apiKey&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalApi = API.replace("CITY", city).replace("apiKey", apiKey);

        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = exchange.getBody();
        return body;
    }
}
