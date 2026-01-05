package utils;

public class MyString {
    public static final char[] SPECIAL_CHARS = new char[] { '$', '%', '_' };

    public static boolean containsNumber(String str) {
        // O(k)
        int charNum;

        for (int i = 0; i < str.length(); i++) {
            charNum = str.charAt(i) - '0';

            if (charNum >= 0 && charNum <= 9) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNumber(String str) {
        // O(k)
        int charNum;

        if (MyString.isStringEmpty(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(0) == '-') {
                continue;
            }

            charNum = str.charAt(i) - '0';

            if (charNum < 0 || charNum > 9) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAtleats1SpChar(String str) {
        // O(n * k)
        for (int i = 0; i < MyString.SPECIAL_CHARS.length; i++) {
            if (str.contains(MyString.SPECIAL_CHARS[i] + "")) {
                return true;
            }
        }

        return false;
    }

    public static String convertPrintfStrToStr(String str, Object... args) {
        // O(n * k * t)
        String placeHolder;

        str = Colors.removeColorsFromString(str);

        for (int i = 0; i < args.length; i++) {
            placeHolder = MyStringPlaceHolder.findStringPlaceHolder(args[i]);

            if (args[i] instanceof String) {
                args[i] = Colors.removeColorsFromString((String) args[i]);
            }

            str = str.replaceFirst(placeHolder, args[i].toString());
        }

        return str;
    }

    private static String removeAllSpaces(String str) {
        // O(k)
        return str.replace(" ", "");
    }

    public static boolean isStringEmpty(String str) {
        // O(k)
        String tempString = removeAllSpaces(str);

        return tempString == null || tempString.equals("");
    }
}
