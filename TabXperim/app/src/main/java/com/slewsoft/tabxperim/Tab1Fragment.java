package com.slewsoft.tabxperim;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Tab1Fragment extends Fragment implements  OnMapReadyCallback, View.OnClickListener {
    private static final LatLng DISNEYLAND = new LatLng(33.812324, -117.918942);

    private GoogleMap mMap;
    private View mView;
    private LocationHelper mLocationHelper = new LocationHelper();
    public List<DraggableCircle> mCraneMarkers = new ArrayList<>();
    private List<Marker> mUnitMarkers = new ArrayList<>();
    private MapSiteEventHandler mEventHandler;


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
//        map.setContentDescription("blahdy blah");
        mMap = map;
        mEventHandler = new MapSiteEventHandler(mMap, getActivity(), mCraneMarkers, mUnitMarkers);

        Button clickButton = mView.findViewById(R.id.go_to_address);
        clickButton.setOnClickListener(this);

        mMap.setOnMarkerDragListener(mEventHandler);
        mMap.setOnMapLongClickListener(mEventHandler);
        mMap.setOnMarkerClickListener(mEventHandler); // Displays a Toast

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DISNEYLAND, 7));
    }

    @Override
    public void onClick(View view) {
        try {
            hideSoftKeyboard(view);

            String address = ((EditText) mView.findViewById(R.id.edit_address)).getText().toString();
            LatLng newLocation = mLocationHelper.getLocation(address, getActivity());

            clearCurrentMarkers();
            mUnitMarkers.add(createUnit(mMap, newLocation));
            mLocationHelper.goToLocation(mMap, newLocation, 18);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearCurrentMarkers() {
        mUnitMarkers.clear();
        mCraneMarkers.clear();
        mMap.clear();
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
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
