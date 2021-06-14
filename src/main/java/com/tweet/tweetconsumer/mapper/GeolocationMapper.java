package com.tweet.tweetconsumer.mapper;

import com.tweet.tweetconsumer.repository.entity.GeolocationEntity;
import twitter4j.GeoLocation;

public class GeolocationMapper {

    public static GeolocationEntity geolocation4jToGeolocationEntity(GeoLocation geoLocation) {
        if(geoLocation == null) {
            return null;
        }
        return new GeolocationEntity(geoLocation.getLongitude(), geoLocation.getLatitude());
    }
}
