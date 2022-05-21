package br.pucrs.application.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GeoCodingService {

    private JSONParser jsonParser;
    private static RestTemplate restTemplate;

    public GeoCodingService() {
        jsonParser = new JSONParser();
        restTemplate = new RestTemplate();
    }


    public Map<String, Double> getLatitudeLongitude(String address) {
        Map<String, Double> res = new HashMap<>();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search");
        Map<String, String> params = Map.of("format", "json", "addressdetails", "1", "q", address);
        params.forEach(builder::queryParam);

        URI uri = builder.build().encode().toUri();
        ResponseEntity<String> aux  = restTemplate.getForEntity(uri, String.class);


        System.out.println(aux.getBody());
        Object obj = JSONValue.parse(Objects.requireNonNull(aux.getBody()));
        if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            if (array.size() > 0) {
                JSONObject jsonObject = (JSONObject) array.get(0);
                String lon = (String) jsonObject.get("lon");
                String lat = (String) jsonObject.get("lat");
                res.put("lon", Double.parseDouble(lon));
                res.put("lat", Double.parseDouble(lat));

            }
        }
        return res;
    }

}
