package com.slewsoft.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Asset {
    private AssetType type;
    private String notes;
    private int weight;
    private UnitWeightType unitWeightType = UnitWeightType.Pound;

    public enum UnitWeightType {
        Pound, Ton;
    }

    public enum AssetType {
        Crane(1, "Crane"),
        Hvac(2, "Hvac"),
        Truck(3, "Truck"),
        Forklift(4, "Fork lift"),
        Misc(101, "Miscellaneous");

        private int id;
        private String desc;

        AssetType(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public UnitWeightType getUnitWeightType() {
        return unitWeightType;
    }

    public void setUnitWeightType(UnitWeightType unitWeightType) {
        this.unitWeightType = unitWeightType;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
