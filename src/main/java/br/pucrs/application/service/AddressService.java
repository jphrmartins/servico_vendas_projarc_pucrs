package br.pucrs.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.application.constants.StoreAddress;

import java.io.IOException;
import java.util.Map;

@Component
public class AddressService {
    final static int R = 6371;

    @Autowired
    private GeoCodingService geoCodingService;

    public static double distanceFromStore(double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - StoreAddress.latitude);
        double lonDistance = Math.toRadians(lon2 - StoreAddress.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(StoreAddress.latitude)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


    public double distanceFromStore(String address) {
        Map<String, Double> coords = geoCodingService.getLatitudeLongitude(address);
        return distanceFromStore(coords.get("lat"), coords.get("lon"));
    }

}
