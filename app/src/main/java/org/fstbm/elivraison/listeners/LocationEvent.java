package org.fstbm.elivraison.listeners;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.fstbm.elivraison.R;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class LocationEvent implements LocationListener {
    private Context ctx;
    private MapView mapView;
    private Marker marker1,marker2;
    private GeoPoint destinationPosition;
    public  LocationEvent(Context ctx, MapView mapView,GeoPoint destinationPosition){
        this.ctx = ctx;
        this.mapView = mapView;
        marker1 = new Marker(mapView);
        marker2 = new Marker(mapView);
        this.destinationPosition = destinationPosition;
        marker1.setIcon(ContextCompat.getDrawable(ctx,R.drawable.destination));
        marker1.setPosition(destinationPosition);
        marker2.setIcon(ContextCompat.getDrawable(ctx,R.drawable.shipping));
    }

    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        GeoPoint currentPosition = new GeoPoint(latitude,longitude);
        List<GeoPoint> points = new ArrayList<>();
        points.add(destinationPosition);
        points.add(currentPosition);
        mapView.zoomToBoundingBox(BoundingBox.fromGeoPoints(points),true);
        marker2.setPosition(currentPosition);
        mapView.getOverlays().add(marker1);
        mapView.getOverlays().add(marker2);
        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        //mapView.getController().animateTo(currentPosition);
        sleep();
        mapView.invalidate();
    }


    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Handle the status change of the location provider
        // You can add your own implementation here
    }

    private void sleep(){
        try{
            wait(2000);
        }catch (Exception e){}

    }

}
