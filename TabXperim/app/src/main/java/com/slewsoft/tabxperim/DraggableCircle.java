package com.slewsoft.tabxperim;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DraggableCircle {
    private final Marker mCenterMarker;
    private final Circle mCircle;
    private double mRadiusMeters;
    private GoogleMap mMap;
    private LocationHelper locationHelper = new LocationHelper();
    private MarkerHelper markerHelper = new MarkerHelper();

    public DraggableCircle(String title, GoogleMap map, LatLng center, double radiusFt) {
        mMap = map;
        mRadiusMeters = locationHelper.toMeter(radiusFt);
        mCenterMarker = mMap.addMarker(new MarkerOptions()
                .position(center)
                .title(title)
                .draggable(true));
        mCenterMarker.setTag(markerHelper.createCraneInfo("", ""));

        mCenterMarker.showInfoWindow();

        mCircle = mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(mRadiusMeters)
                .strokeWidth(2)
                .strokeColor(Color.RED)
//                .fillColor(mFillColorArgb)
                .clickable(true));
    }

    public boolean onMarkerMoved(Marker marker) {
        if (marker.equals(mCenterMarker)) {
            mCircle.setCenter(marker.getPosition());
            return true;
        }
        return false;
    }

    public void setClickable(boolean clickable) {
        mCircle.setClickable(clickable);
    }
}
