package com.slewsoft.tabxperim;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment
    implements  OnMapReadyCallback,
                View.OnClickListener {

    private static double DEFAULT_CRANE_RADIUS_FT = 150;
    private static final LatLng DISNEYLAND = new LatLng(33.812324, -117.918942);

    private GoogleMap mMap;
    private LocationHelper locationHelper = new LocationHelper();
    private List<DraggableCircle> craneMarkers = new ArrayList<>();
    private List<Marker> unitMarkers = new ArrayList<>();
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.map_fragment, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Button clickButton = mView.findViewById(R.id.go_to_address);
        clickButton.setOnClickListener(this);

        map.setContentDescription("blahdy blah");
        mMap = map;
//        mMap.setOnMarkerDragListener(this);
//        mMap.setOnMapLongClickListener(this);
//
//        mMap.setOnMarkerClickListener(this); // Displays a Toast

//
//        DraggableCircle circle = new DraggableCircle(SYDNEY, DEFAULT_RADIUS_METERS);
//        mCircles.add(circle);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions()
                .position(DISNEYLAND)
                .draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DISNEYLAND, 15));
    }

    @Override
    public void onClick(View view) {
        try {
            hideSoftKeyboard(view);

            String address = ((EditText) mView.findViewById(R.id.edit_address)).getText().toString();
            LatLng newLocation = locationHelper.getLocation(address, getActivity());

            unitMarkers.add(createUnit(mMap, newLocation));
            locationHelper.goToLocation(mMap, newLocation, 18);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Marker createUnit(GoogleMap map, LatLng location) {
        Marker unit = map.addMarker(new MarkerOptions()
                .position(location)
                .title("Unit 1")
                .draggable(true));
        unit.setTag(0);

        unit.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        unit.showInfoWindow();
        return unit;
    }

    private void hideSoftKeyboard(View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
