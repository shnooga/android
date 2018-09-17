package com.slewsoft.tabxperim;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Tab1Fragment extends Fragment
    implements OnMapReadyCallback {

    GoogleMap mMap;
    MapView mMapView;
    View mView;

    private static final String TAG = "Tab1Fragment";
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

    private Button btnTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        btnTest = view.findViewById(R.id.btn_Test1);

        btnTest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Testing Btn click 1", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
        */

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
        /*
        MapsInitializer.initialize(getContext());
        mMap = map;
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .draggable(true));

//        CameraPosition position = CameraPosition.builder().target(SYDNEY).zoom(16).build();
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 17));
        */
        map.setContentDescription("blahdy blah");

        mMap = map;
//        mMap.setOnMarkerDragListener(this);
//        mMap.setOnMapLongClickListener(this);
//
//        mMap.setOnMarkerClickListener(this); // Displays a Toast

//        mFillColorArgb = Color.HSVToColor(
//                mFillAlphaBar.getProgress(), new float[]{mFillHueBar.getProgress(), 1, 1});
//        mStrokeColorArgb = Color.HSVToColor(
//                mStrokeAlphaBar.getProgress(), new float[]{mStrokeHueBar.getProgress(), 1, 1});
//
//        mFillHueBar.setOnSeekBarChangeListener(this);
//        mFillAlphaBar.setOnSeekBarChangeListener(this);
//
//        mStrokeWidthBar.setOnSeekBarChangeListener(this);
//        mStrokeHueBar.setOnSeekBarChangeListener(this);
//        mStrokeAlphaBar.setOnSeekBarChangeListener(this);
//
//        mStrokePatternSpinner.setOnItemSelectedListener(this);
//
//        DraggableCircle circle = new DraggableCircle(SYDNEY, DEFAULT_RADIUS_METERS);
//        mCircles.add(circle);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 19));
    }
}
