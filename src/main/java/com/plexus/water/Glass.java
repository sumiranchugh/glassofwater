package com.plexus.water;

import lombok.Data;

@Data
public class Glass {

    private final float netCapacity;

    private float currentCapacity;

    private Unit unit;

    public Glass(float netCapacity) {
        this.netCapacity = netCapacity;
    }
}
