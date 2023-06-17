package org.fstbm.elivraison.listeners;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.fstbm.elivraison.R;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

public class LastLocation implements LocationListener {
    private Context ctx;
    private MapView mapView;
    private Polyline polyline;
    private List<GeoPoint> routePoints;
    private GeoPoint currentPosition;
    private Marker marker1,marker2;
    public LastLocation(Context ctx, MapView mapView ,GeoPoint dest){
        this.ctx = ctx;
        this.mapView = mapView;
        routePoints = new ArrayList<>();
        currentPosition = new GeoPoint(0d,0d);
        routePoints.add(dest);
        routePoints.add(currentPosition);
        marker1 = new Marker(mapView);
        marker2 = new Marker(mapView);
        marker1.setPosition(dest);
        marker1.setIcon(ContextCompat.getDrawable(ctx, R.drawable.destination));
        marker2.setIcon(ContextCompat.getDrawable(ctx,R.drawable.shipping));


        polyline = new Polyline(mapView);
        polyline.setPoints(routePoints);
        polyline.getOutlinePaint().setColor(Color.BLUE);
        polyline.getOutlinePaint().setStrokeJoin(Paint.Join.ROUND);
        polyline.getOutlinePaint().setStrokeWidth(3.5f);
    }

    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        currentPosition.setLatitude(latitude);
        currentPosition.setLongitude(longitude);
        marker2.setPosition(currentPosition);
        mapView.getOverlays().add(marker2);
        mapView.getOverlays().add(marker1);
        polyline.setPoints(routePoints);
        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getController().setCenter(currentPosition);
        mapView.getOverlayManager().add(polyline);
        mapView.invalidate();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
