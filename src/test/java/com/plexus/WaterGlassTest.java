package com.plexus;

import com.plexus.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class WaterGlassTest {

    static WaterGlass waterGlass;
    static DecimalFormat format = new DecimalFormat("#.0");

    @BeforeAll
    public static void setup() {
        waterGlass = new WaterGlass();
    }

    @DisplayName("test correct amount of water")
    @ParameterizedTest
    @CsvSource({"1,1,250,250.0", "2,1,275,12.5", "2,2,275,12.5", "2,2,251,0.5"})
    void findWater(int row, int col, int capacity, float result) throws InvalidInputException {

        float water = waterGlass.findWater(row, col, capacity);
        assertEquals(Float.valueOf(format.format(water)), result);
    }

    @DisplayName("test valid input, row is 0")
    @Test
    void invalidInput_row() {

        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(0, 1, 250));
    }

    @DisplayName("test valid input, row is negative")
    @Test
    void invalidInput_row_negative() {

        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(-1, 1, 250));
    }

    @DisplayName("test valid input, col is 0")
    @Test
    void invalidInput_col() {

        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(1, 0, 250));
    }

    @DisplayName("test valid input, col is negative")
    @Test
    void invalidInput_col_negative() {

        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(1, -1, 250));
    }

    @DisplayName("test boundary max row and cap")
    @Disabled("increase heap , irrelevant")
    @Test
    void boundaryCondition() throws InvalidInputException {
        assertEquals(100, waterGlass.findWater(46340, 46340, Integer.MAX_VALUE));
    }


    @DisplayName("test valid input, row is max")
    @Test
    void invalidInput_row_max() {
        assertThrows(InvalidInputException.class, () -> waterGlass.findWater( Integer.MAX_VALUE, 1, 250));
    }

    @DisplayName("test valid input, col is max")
    @Test
    void invalidInput_col_max() {
        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(1, Integer.MAX_VALUE,  250));
    }

    @DisplayName("test valid input, cap is negative ")
    @Test
    void invalidInput_cap_neg() {
        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(1, 1,  -250));
    }

    @DisplayName("test valid input, capacity is max")
    @Test
    void invalidInput_cap_max() {
        assertThrows(InvalidInputException.class, () -> waterGlass.findWater(1, Integer.MAX_VALUE,  Integer.MAX_VALUE));
    }


}