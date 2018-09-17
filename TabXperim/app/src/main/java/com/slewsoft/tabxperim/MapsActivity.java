package com.slewsoft.tabxperim;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.*;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback,
        OnMarkerClickListener,
        View.OnClickListener {
    private static final LatLng LINKEDIN = new LatLng(37.4233438, -122.0728817);
    private GoogleMap mMap;
    private LocationHelper helper = new LocationHelper();

    @Override
    public void onClick(View view) {
        try {
            hideSoftKeyboard(view);

            String address = ((EditText) findViewById(R.id.edit_address)).getText().toString();
            LatLng newLocation = helper.getLocation(address, this);

            createMarker(mMap, newLocation);
            helper.goToLocation(mMap, newLocation, 19);

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

        Marker crane = map.addMarker(new MarkerOptions()
                .position(createOffset(location))
                .title("Crane A")
                .draggable(true));
        crane.setTag(0);

        crane.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        crane.showInfoWindow();
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


//        mMap.moveCamera(CameraUpdateFactory.newLatLng(LINKEDIN));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LINKEDIN, 18));
        mMap.setOnMarkerClickListener(this);
    }

    private LatLng createOffset(final LatLng location) {
        double latOffset = .0004;
        double longOffset = .0004;

        return new LatLng(location.latitude + latOffset, location.longitude + longOffset);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
