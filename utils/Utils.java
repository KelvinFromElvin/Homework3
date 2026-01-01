package utils;

import java.util.UUID;

public class Utils {

    public static int parseInt(String str) {
        // O(k)
        int num = 0;
        boolean isNegative;

        if (!MyString.isNumber(str)) {
            return num;
        }

        isNegative = str.charAt(0) == '-';

        for (int i = 0; i < str.length(); i++) {
            if (isNegative && i == 0) {
                continue;
            }

            num *= 10;
            num += str.charAt(i) - '0';
        }

        if (isNegative) {
            num *= -1;
        }

        return num;
    }

    public static String generateUUID() {
        // O(n)
        return UUID.randomUUID().toString();
    }
}
