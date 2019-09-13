package com.plexus.water;

public enum Unit {

    ML("ml"), L("l"), GALLONS("gallons");

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
