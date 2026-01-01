package user_parsing;

public class UserMenuInputOption {
    private int option;
    private char[] optionLabels;

    // Constractors
    public UserMenuInputOption(int option, char[] optionLabels) {
        this.option = option;
        this.optionLabels = optionLabels;
    }

    // Getters
    public int getOption() {
        // O(1)
        return this.option;
    }

    public char[] getOptionLabels() {
        // O(1)
        return this.optionLabels;
    }

    // Actions
    public boolean isMyOption(char userInput) {
        // O(n)
        for (int i = 0; i < this.optionLabels.length; i++) {
            if (this.optionLabels[i] == userInput) {
                return true;
            }
        }

        return false;
    }
}
