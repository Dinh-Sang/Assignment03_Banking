package vn.funix.fx21670.java.asm03.models;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatBalance(double value) {
        return String.format(DECIMAL_FORMAT.format(value));
    }

    public static String formatTime(LocalDateTime time) {
        return DATE_TIME_FORMATTER.format(time);
    }
}
