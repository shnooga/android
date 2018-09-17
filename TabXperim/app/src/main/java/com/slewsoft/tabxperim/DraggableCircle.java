package com.slewsoft.tabxperim;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;

import java.util.Arrays;
import java.util.List;

public class DraggableCircle {
    private static final double DEFAULT_RADIUS_METERS = 40;
    private static final double RADIUS_OF_EARTH_METERS = 6371009;

    private static final int MAX_WIDTH_PX = 50;
    private static final int MAX_HUE_DEGREES = 360;
    private static final int MAX_ALPHA = 255;

    private static final int PATTERN_DASH_LENGTH_PX = 100;
    private static final int PATTERN_GAP_LENGTH_PX = 200;
    private static final Dot DOT = new Dot();
    private static final Dash DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final Gap GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_DOTTED = Arrays.asList(DOT, GAP);
    private static final List<PatternItem> PATTERN_DASHED = Arrays.asList(DASH, GAP);
    private static final List<PatternItem> PATTERN_MIXED = Arrays.asList(DOT, GAP, DOT, DASH, GAP);
    private final Marker mCenterMarker;
    private final Circle mCircle;
    private double mRadiusMeters;
    private GoogleMap mMap;

    public DraggableCircle(GoogleMap map, LatLng center, double radiusFt) {
        mMap = map;
        mRadiusMeters = radiusFt/3.2808;
        mCenterMarker = mMap.addMarker(new MarkerOptions()
                .position(center)
                .draggable(true));
        mCenterMarker.showInfoWindow();

        mCircle = mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(mRadiusMeters)
                .strokeWidth(3)
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

    /** Generate LatLng of radius marker */
//    public LatLng toRadiusLatLng(LatLng center, double radiusMeters) {
//        double radiusAngle = Math.toDegrees(radiusMeters / RADIUS_OF_EARTH_METERS) /
//                Math.cos(Math.toRadians(center.latitude));
//        return new LatLng(center.latitude, center.longitude + radiusAngle);
//    }
//
//    public double toRadiusMeters(LatLng center, LatLng radius) {
//        float[] result = new float[1];
//        Location.distanceBetween(center.latitude, center.longitude,
//                radius.latitude, radius.longitude, result);
//        return result[0];
//    }

//    public void onStyleChange() {
//        mCircle.setStrokeWidth(mStrokeWidthBar.getProgress());
//        mCircle.setStrokeColor(mStrokeColorArgb);
//        mCircle.setFillColor(mFillColorArgb);
//    }
//
//    public void setStrokePattern(List<PatternItem> pattern) {
//        mCircle.setStrokePattern(pattern);
//    }


    public void setClickable(boolean clickable) {
        mCircle.setClickable(clickable);
    }
}
