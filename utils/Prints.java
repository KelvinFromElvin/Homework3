package utils;

public class Prints {
    public static final String STRING_END_LINE = "_§%n";

    public static void printf(String str, Object... args) {
        // O(n * k)
        str = Colors.convertStringToColor(str);

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                args[i] = Colors.convertStringToColor(args[i].toString());
            }
        }

        System.out.printf(str, args);
    }

    public static void printfln(String str, Object... args) {
        // O(n * k)
        Prints.printf(str + STRING_END_LINE, args);
    }

    public static void printfln() {
        // O(n * k)
        Prints.printf(STRING_END_LINE);
    }

    public static void printErrorMsg(String str, Object... args) {
        // O(n * k)
        Prints.printfln("_§red_" + str, args);
    }

    public static void printSuccessMsg(String str, Object... args) {
        // O(n * k)
        Prints.printfln("_§blue_" + str, args);
    }
}
