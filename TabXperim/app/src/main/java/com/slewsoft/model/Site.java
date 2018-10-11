package com.slewsoft.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Site {
//    private Bitmap pix;
    private List<Asset> assets = new ArrayList<>();
    private int bldHeightFt;
    private int unitOffsetFt;
    private String latLng;

    public Site() {
    }

    public Site(String latLng, int bldHeightFt, int unitOffsetFt) {
        this.latLng = latLng;
        this.bldHeightFt = bldHeightFt;
        this.unitOffsetFt = unitOffsetFt;
    }


    //    public Bitmap getPix() { return pix; }
//    public void setPix(Bitmap pix) { this.pix = pix; }

    public List<Asset> getAssets() { return assets; }
    public void setAssets(List<Asset> assets) { this.assets = assets; }

    public int getBldHeightFt() {
        return bldHeightFt;
    }

    public void setBldHeightFt(int bldHeightFt) {
        this.bldHeightFt = bldHeightFt;
    }

    public int getUnitOffsetFt() {
        return unitOffsetFt;
    }

    public void setUnitOffsetFt(int unitOffsetFt) {
        this.unitOffsetFt = unitOffsetFt;
    }
}
