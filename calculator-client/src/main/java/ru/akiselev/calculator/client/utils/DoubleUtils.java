package ru.akiselev.calculator.client.utils;

public class DoubleUtils {
    private DoubleUtils() {

    }

    public static boolean isDouble(final String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
