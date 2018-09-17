package com.slewsoft.tabxperim;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class LocationHelper {

    public void goToLocation(GoogleMap map, LatLng ll, float zoom) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        map.moveCamera(update);
    }

    public LatLng getLocation(final String address, final Context context) throws IOException {
        Geocoder gc = new Geocoder(context);
        Address adr = gc.getFromLocationName(address, 1).get(0);

        return new LatLng(adr.getLatitude(), adr.getLongitude());
    }

}
