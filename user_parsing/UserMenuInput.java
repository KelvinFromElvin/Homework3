package user_parsing;

public class UserMenuInput {
    public static int decodeUserInput(String userInputString,
            UserMenuInputOption[] options,
            int defaultOptions) {
        // O(n * n)
        char userInput = userInputString.toLowerCase().charAt(0);

        int result = defaultOptions;

        for (int i = 0; i < options.length; i++) {
            if (options[i].isMyOption(userInput)) {
                result = options[i].getOption();
                break;
            }
        }

        return result;
    }
}
