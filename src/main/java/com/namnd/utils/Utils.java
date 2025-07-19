package com.namnd.utils;

import java.math.BigDecimal;

/**
 * @author nam.nd6 on 12/7/2023
 */
public class Utils {

    public static BigDecimal stringToBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String bigDecimalToString(BigDecimal value) {
        try {
            return value.stripTrailingZeros().toPlainString();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
