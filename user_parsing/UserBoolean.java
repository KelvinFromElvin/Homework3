package user_parsing;

import global.Globals;
import utils.Prints;

public class UserBoolean {
    public static boolean getUserBoolean(String title) {
        // O(k)
        Prints.printf("%s(_§red_Y_§es | _§red_N_§o): ", title);
        String result = Globals.SCANNER.nextLine();
        return result.toLowerCase().charAt(0) == 'y';
    }

    public static final int USER_INPUT_TRUE = 1;
    public static final int USER_INPUT_FALSE = 0;
    public static final int USER_INPUT_SKIP = 2;

    public static int getUserBooleanOrSkip(String title, String skipValue) {
        // O(n * k)
        Prints.printf("%s(_§red_Y_§es | _§red_N_§o | _§red_%s_§): ", title, skipValue);
        String result = Globals.SCANNER.nextLine();

        if (result.equals(skipValue)) {
            return USER_INPUT_SKIP;
        } else if (result.toLowerCase().charAt(0) == 'y') {
            return USER_INPUT_TRUE;
        } else {
            return USER_INPUT_TRUE;
        }
    }
}
