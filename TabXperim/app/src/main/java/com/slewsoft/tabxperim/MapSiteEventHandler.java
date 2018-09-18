package com.slewsoft.tabxperim;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

public class MapSiteEventHandler
        implements
        OnMapLongClickListener,
        OnMarkerClickListener,
        OnMarkerDragListener {

    private static double DEFAULT_CRANE_RADIUS_FT = 150;
    private GoogleMap mMap;
    private Context mContext;
    private LocationHelper mLocationHelper = new LocationHelper();
    public List<DraggableCircle> mCraneMarkers;
    public List<Marker> mUnitMarkers;

    public MapSiteEventHandler(GoogleMap mMap, Context mContext, List<DraggableCircle> mCraneMarkers, List<Marker> mUnitMarkers) {
        this.mMap = mMap;
        this.mContext = mContext;
        this.mCraneMarkers = mCraneMarkers;
        this.mUnitMarkers = mUnitMarkers;
    }

    @Override
    public void onMapLongClick(LatLng point) {
        int craneCount = mCraneMarkers.size() + 1;
        DraggableCircle circle = new DraggableCircle("Crane " + craneCount, mMap, point, DEFAULT_CRANE_RADIUS_FT);
        mCraneMarkers.add(circle);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(mContext, marker.getTitle() + " has been clicked " + clickCount + " times.", Toast.LENGTH_SHORT).show();
        } else {
            if (mUnitMarkers.size() == 1) {
                Marker unit = mUnitMarkers.get(0);
                String dist = mLocationHelper.distanceBetweenInFt(unit.getPosition(), marker.getPosition());
                Toast.makeText(mContext, marker.getTitle() + " is " + dist + " feet from " + unit.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        onMarkerMoved(marker);
    }

    private void onMarkerMoved(Marker marker) {
        for (DraggableCircle draggableCircle : mCraneMarkers) {
            if (draggableCircle.onMarkerMoved(marker)) {
                break;
            }
        }
    }
}
