package com.apba.proas.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vessel {

    VesselType vesselType;
    int LOA;
    int DWT;
    int displacement;
    String orientation;
    int aoiId;

    public VesselType getVesselType() {
        return vesselType;
    }

    public void setVesselType(VesselType type) {
        this.vesselType = type;
    }

    public int getLOA() {
        return LOA;
    }

    public void setLOA(int lOA) {
        LOA = lOA;
    }

    public int getDWT() {
        return DWT;
    }

    public void setDWT(int dWT) {
        DWT = dWT;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getAoiId() {
        return aoiId;
    }

    public void setAoi(int aoiid) {
        this.aoiId = aoiid;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientiation(String ori) {
        orientation = ori;
    }

    @Override
    public String toString() {
        return "Vessel {" +
                "vesselType=" + vesselType.toString() +
                ", LOA='" + LOA + '\'' +
                ", DWT='" + DWT + '\'' +
                ", displacement='" + displacement + '\'' +
                ", orientation='" + orientation + '\'' +
                ", aoiId='" + aoiId + '\'' +
                '}';
    }
}
