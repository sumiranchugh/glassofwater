package com.plexus.water;

import com.plexus.exceptions.InvalidInputException;
import com.plexus.util.WaterCalcUtil;
import lombok.extern.java.Log;

import java.lang.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Log
public class WaterGlass {

    final float glassCapacity;
    ArrayList<Glass> glasses;

    public WaterGlass(float capacity) {
        this.glassCapacity = capacity;
    }

    /**
     * @param i        row
     * @param j        column
     * @param totalCap total capacity of water
     * @return amount of water in ith row and jth glass of that row
     * @throws InvalidInputException
     */
    public Glass findWater(int i, int j,
                           int totalCap) throws InvalidInputException {

        validateInput(i, j, totalCap);

        int totalNumberOfGlasses = WaterCalcUtil.calcTotalNumberOfGlasses(i);
        glasses = new ArrayList<>(totalNumberOfGlasses);
        initGlasses(totalNumberOfGlasses, totalCap);

        int index = 0;
        Glass glass;

        for (int row = 1; row <= i; ++row) {
            for (int col = 1;
                 col <= row; ++col, ++index) {
                glass = glasses.get(index);

                float total = glass.getCurrentCapacity();

                glass.setCurrentCapacity(Math.min(total, glassCapacity));

                total = (total >= glassCapacity) ? (total - glassCapacity) : total;

                Glass downOne = glasses.get(index + row);
                downOne.setCurrentCapacity(downOne.getCurrentCapacity() + (total / 2));

                Glass downTwo = glasses.get(index + row + 1);
                downTwo.setCurrentCapacity(downTwo.getCurrentCapacity() + (total / 2));

            }
        }

        return glasses.get(i * (i - 1) /
                2 + j - 1);
    }

    private void initGlasses(int totalNumberOfGlasses, float total) {
        IntStream.range(0, totalNumberOfGlasses).forEach((index) -> glasses.add(new Glass(glassCapacity)));
        glasses.get(0).setCurrentCapacity(total);
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
        if (row > Math.sqrt(Integer.MAX_VALUE - 1)) {
            throw new InvalidInputException("please enter row less than " + Math.round(Math.sqrt(Integer.MAX_VALUE - 1)));
        }
    }


}
