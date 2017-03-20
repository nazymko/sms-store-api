package org.nazymko.moneygraph.analytics.utils;

import java.math.BigDecimal;

/**
 * Created by a.nazimko on 17.03.2017.
 */
public class NumberUtil {


    public static boolean isDecimal(String word) {

        return word.matches("-?\\d*\\.?\\d+");
    }

    public static BigDecimal parse(String word) {
        if (isDecimal(word)) {
            return new BigDecimal(word);
        } else {
            return null;
        }
    }
}
