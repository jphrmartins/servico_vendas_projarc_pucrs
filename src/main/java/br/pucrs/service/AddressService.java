package br.pucrs.service;

import br.pucrs.constants.StoreAddress;
import org.springframework.stereotype.Component;

@Component
public class AddressService {
    final static int R = 6371;

    public static double distanceFromStore(double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - StoreAddress.latitude);
        double lonDistance = Math.toRadians(lon2 - StoreAddress.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(StoreAddress.latitude)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double distanceInKM(double lat1, double lat2, double lon1, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
