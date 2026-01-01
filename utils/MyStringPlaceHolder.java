package utils;

public class MyStringPlaceHolder {
    public static String findStringPlaceHolder(boolean value) {
        // O(1)
        return "%b";
    }

    public static String findStringPlaceHolder(char value) {
        // O(1)
        return "%c";
    }

    public static String findStringPlaceHolder(String value) {
        // O(1)
        return "%s";
    }

    public static String findStringPlaceHolder(int value) {
        // O(1)
        return "%d";
    }

    public static String findStringPlaceHolder(long value) {
        // O(1)
        return "%d";
    }

    public static String findStringPlaceHolder(float value) {
        // O(1)
        return "%f";
    }

    public static String findStringPlaceHolder(double value) {
        // O(1)
        return "%f";
    }

    public static String findStringPlaceHolder(Object value) {
        // O(1)
        return "%s";
    }
}
