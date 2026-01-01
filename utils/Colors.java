package utils;

public class Colors {
    public static final String FBLACK = "\u001B[30m";
    public static final String BBLACK = "\u001B[40m";

    public static final String FRED = "\u001B[31m";
    public static final String BRED = "\u001B[41m";

    public static final String FGREEN = "\u001B[32m";
    public static final String BGREEN = "\u001B[42m";

    public static final String FYELLOW = "\u001B[33m";
    public static final String BYELLOW = "\u001B[43m";

    public static final String FBLUE = "\u001B[34m";
    public static final String BBLUE = "\u001B[44m";

    public static final String FPURPLE = "\u001B[35m";
    public static final String BPURPLE = "\u001B[45m";

    public static final String FCYAN = "\u001B[36m";
    public static final String BCYAN = "\u001B[46m";

    public static final String FWHITE = "\u001B[37m";
    public static final String BWHITE = "\u001B[47m";

    public static final String RESET = "\u001B[0m";

    public static final String[] keyVals = new String[] {
            "_§black_", Colors.FBLACK,
            "_§bblack_", Colors.BBLACK,
            "_§red_", Colors.FRED,
            "_§bred_", Colors.BRED,
            "_§green_", Colors.FGREEN,
            "_§bgreen_", Colors.BGREEN,
            "_§yellow_", Colors.FYELLOW,
            "_§byellow_", Colors.BYELLOW,
            "_§blue_", Colors.FBLUE,
            "_§bblue_", Colors.BBLUE,
            "_§purple_", Colors.FPURPLE,
            "_§bpurple_", Colors.BPURPLE,
            "_§cyan_", Colors.FCYAN,
            "_§bcyan_", Colors.BCYAN,
            "_§white_", Colors.FWHITE,
            "_§bwhite_", Colors.BWHITE,
            "_§", Colors.RESET
    };

    public static String convertStringToColor(String str) {
        // O(n * k)
        for (int i = 0; i < Colors.keyVals.length; i += 2) {
            str = str.replace(Colors.keyVals[i], Colors.keyVals[i + 1]);
        }
        return str;
    }

    public static String removeColorsFromString(String str) {
        // O(n * k)
        for (int i = 0; i < Colors.keyVals.length; i += 2) {
            str = str.replace(Colors.keyVals[i], "");
        }
        return str;
    }
}
