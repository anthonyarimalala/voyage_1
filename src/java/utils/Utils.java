
package utils;

import java.text.DecimalFormat;


public class Utils {
    public static String formatDouble(double number) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(number);
    }
}
