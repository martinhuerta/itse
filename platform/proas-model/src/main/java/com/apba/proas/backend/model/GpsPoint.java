package com.apba.proas.backend.model;

public class GpsPoint {
    static int nextGpsPointId = 1;

    int gpsPointId = nextGpsPointId++;
    float longitude;
    float latitude;
    int aoiId;

    public int getGpsPointId() {
        return gpsPointId;
    }

    public void setGpsPointId(int gpsPointId) {
        this.gpsPointId = gpsPointId;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getAoiId() {
        return aoiId;
    }

    public void setAoiId(int aoiId) {
        this.aoiId = aoiId;
    }

    @Override
    public String toString() {
        return getClass().getName() + " { " +
                "gpsPointId = " + gpsPointId +
                ", longitude = " + longitude +
                ", latitude = " + latitude +
                ", aoiId = " + aoiId +
                " }";
    }
}
