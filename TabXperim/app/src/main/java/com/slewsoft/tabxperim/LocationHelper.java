package com.slewsoft.tabxperim;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class LocationHelper {
    private static final double RADIUS_OF_EARTH_METERS = 6371009;

    public void goToLocation(GoogleMap map, LatLng ll, float zoom) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        map.moveCamera(update);
    }

    public LatLng getLocation(final String address, final Context context) throws IOException {
        Geocoder gc = new Geocoder(context);
        Address adr = gc.getFromLocationName(address, 1).get(0);

        return new LatLng(adr.getLatitude(), adr.getLongitude());
    }

    /** Generate LatLng of radius marker */
    public LatLng toRadiusLatLng(LatLng center, double radiusMeters) {
        double radiusAngle = Math.toDegrees(radiusMeters / RADIUS_OF_EARTH_METERS) /
                Math.cos(Math.toRadians(center.latitude));
        return new LatLng(center.latitude, center.longitude + radiusAngle);
    }

    public double toRadiusMeters(LatLng center, LatLng radius) {
        float[] result = new float[1];
        Location.distanceBetween(center.latitude, center.longitude, radius.latitude, radius.longitude, result);
        return result[0];
    }

}
