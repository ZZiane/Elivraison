package org.fstbm.elivraison.data.model;

import java.io.Serializable;

public class Position implements Serializable {
    private double latitude;
    private double longitude;

    public Position(){ }

    public Position(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
