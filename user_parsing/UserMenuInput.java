package user_parsing;

import utils.MyString;

public class UserMenuInput {
    public static int decodeUserInput(String userInputString,
            UserMenuInputOption[] options,
            int defaultOptions) {
        // O(n * n)
        int result = defaultOptions;

        if (MyString.isStringEmpty(userInputString)) {
            return result;
        }

        char userInput = userInputString.toLowerCase().charAt(0);

        for (int i = 0; i < options.length; i++) {
            if (options[i].isMyOption(userInput)) {
                result = options[i].getOption();
                break;
            }
        }

        return result;
    }
}
