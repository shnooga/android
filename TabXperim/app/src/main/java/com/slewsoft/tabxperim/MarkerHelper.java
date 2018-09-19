package com.slewsoft.tabxperim;

import java.util.UUID;

public class MarkerHelper {

    public enum MarkerType {
        Unit, Crane, FlatBed
    }

    public MarkerInfo createCraneInfo(String ... details) {
       return new MarkerInfo(UUID.randomUUID().toString(), MarkerType.Crane, details[0], details[1]);
    }

    public MarkerInfo createUnitInfo(String ... details) {
        return new MarkerInfo(UUID.randomUUID().toString(), MarkerType.Unit, details[0], details[1]);
    }
}
