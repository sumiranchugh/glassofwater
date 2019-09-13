package com.plexus.util;

public class WaterCalcUtil {

    public static int calcTotalNumberOfGlasses(int i) {
        return Math.round((i * (i + 1)))+2;
    }
}
