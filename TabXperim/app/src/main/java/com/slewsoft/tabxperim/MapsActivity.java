package com.slewsoft.tabxperim;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity
        implements  OnMapReadyCallback,
                    OnMapLongClickListener,
                    OnMarkerClickListener,
                    View.OnClickListener {

    private static double DEFAULT_CRANE_RADIUS_FT = 150;
    private GoogleMap mMap;
    private LocationHelper helper = new LocationHelper();
    private List<DraggableCircle> craneMarkers = new ArrayList<>();

    @Override
    public void onMapLongClick(LatLng point) {
        DraggableCircle circle = new DraggableCircle(mMap, point, DEFAULT_CRANE_RADIUS_FT);
        craneMarkers.add(circle);
    }

    @Override
    public void onClick(View view) {
        try {
            hideSoftKeyboard(view);

            String address = ((EditText) findViewById(R.id.edit_address)).getText().toString();
            LatLng newLocation = helper.getLocation(address, this);

            createMarker(mMap, newLocation);
            helper.goToLocation(mMap, newLocation, 18);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMarker(GoogleMap map, LatLng location) {
        Marker unit = map.addMarker(new MarkerOptions()
                .position(location)
                .title("Unit 1")
                .draggable(true));
        unit.setTag(0);

        unit.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        unit.showInfoWindow();
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Button clickButton = findViewById(R.id.go_to_address);
        clickButton.setOnClickListener(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this, marker.getTitle() + " has been clicked " + clickCount + " times.", Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
