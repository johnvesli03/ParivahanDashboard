package com.example.Parivahan.Dashboard.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for performing calculations.
 */
public class CalculationUtil {

    /**
     * Calculates the percentage change between two values.
     * Formula: ((current - previous) / previous) * 100
     *
     * @param current  The current period's value.
     * @param previous The previous period's value.
     * @return The percentage change, rounded to two decimal places. Returns 0.0 if the previous value is 0.
     */
    public static double calculatePercentageChange(long current, long previous) {
        if (previous == 0) {
            // If the previous value was 0, we can't divide.
            // A non-zero current value represents infinite growth, but for display purposes,
            // we can return 0.0 depending on requirements.
            return 0.0;
        }
        double change = ((double) (current - previous) / previous) * 100.0;

        // Round to 2 decimal places for a cleaner display
        BigDecimal bd = new BigDecimal(Double.toString(change));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
