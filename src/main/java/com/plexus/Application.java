package com.plexus;

import com.plexus.exceptions.InvalidInputException;
import com.plexus.water.Glass;
import com.plexus.water.WaterGlass;
import lombok.extern.java.Log;

import java.util.Scanner;
import java.util.logging.Level;

@Log
public class Application {

    public static final int GLASS_CAPACITY = 250;


    public static void main(String[] args) {

        log.info("staring the application");

        Scanner input = new Scanner(System.in);

        int i = fetchInput(input, "Enter row of glasses: ");
        int j = fetchInput(input, "Enter column of glasses: ");
        int capacity = fetchInput(input, "Enter the amount of water: ");

        log.log(Level.FINEST, " input received rows {0} columns {1} capacity {2}", new Object[]{i, j, capacity});

        Glass glass = null;
        WaterGlass waterGlass = new WaterGlass(GLASS_CAPACITY);

        try {
             glass = waterGlass.findWater(i, j, capacity);
            log.log(Level.FINEST, " water capacity calculated {0}", glass);

        } catch (InvalidInputException e) {
            log.log(Level.SEVERE, "input error {0}", e.getMessage());
            System.out.println("Invalid Input: " + e.getMessage());
        }

        System.out.printf("Amount of water in %d row & %d column is: %.02f %s", i, j, glass.getCurrentCapacity(), glass.getUnit().getUnit());
        System.out.println();
    }

    private static int fetchInput(Scanner input, String s) {
        System.out.print(s);
        return input.nextInt();
    }
}
