package com.plexus;

import com.plexus.exceptions.InvalidInputException;
import lombok.extern.java.Log;

import java.lang.*;

@Log
public class WaterGlass {

    final float glassCapacity;

    public WaterGlass(float capacity){
        this.glassCapacity = capacity;
    }
    /**
     *
     * @param i row
     * @param j column
     * @param totalCap total capacity of water
     * @return amount of water in ith row and jth glass of that row
     * @throws InvalidInputException
     */
    public float findWater(int i, int j,
                           int totalCap) throws InvalidInputException {

        validateInput(i, j, totalCap);

        int ll = Math.round((i * (i + 1)));
        float[] glass = new float[ll + 2];
        float capacity = totalCap / glassCapacity;
        int index = 0;
        glass[index] = capacity;

        for (int row = 1; row <= i; ++row) {
            for (int col = 1;
                 col <= row; ++col, ++index) {
                capacity = glass[index];

                glass[index] = (capacity >= 1.0f) ? 1.0f : capacity;

                capacity = (capacity >= 1.0f) ? (capacity - 1) : 0.0f;

                glass[index + row] += capacity / 2;
                glass[index + row + 1] += capacity / 2;
            }
        }

        return glassCapacity *glass[i * (i - 1) /
                2 + j - 1];
    }

    private void validateInput(int row, int col, int cap) throws InvalidInputException {
        if (row < 1 || col < 1) {
            throw new InvalidInputException("Row and Column should be greater than 0");
        }
        if (col > row) {
            throw new InvalidInputException("column cannot be greater than row");
        }
        if (cap < 0) {
            throw new InvalidInputException("capacity must be greater than 0");
        }
        if (row> Math.sqrt(Integer.MAX_VALUE-1)) {
            throw new InvalidInputException("please enter row less than " + Math.round(Math.sqrt(Integer.MAX_VALUE-1)));
        }
    }


}
