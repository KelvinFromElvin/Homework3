package user_parsing;

import global.Globals;
import utils.MyString;
import utils.Prints;
import utils.Utils;

public class UserInteger {
    private int number;
    private boolean isValidNumber;

    public UserInteger() {
        this.number = -1;
        this.isValidNumber = false;
    }

    // Getters
    public int getNumber() {
        // O(1)
        return this.number;
    }

    public boolean getIsValidNumber() {
        // O(1)
        return this.isValidNumber;
    }

    // Actions
    public void getAnyNumberFromUser() {
        // O(k)
        String str = Globals.SCANNER.nextLine();

        if (!MyString.isNumber(str)) {
            this.isValidNumber = false;
            return;
        }

        this.isValidNumber = true;

        this.number = Utils.parseInt(str);
    }

    public int getNumberFromUser(String title) {
        // O(n * k)
        this.isValidNumber = false;

        do {
            Prints.printf(title);

            this.getAnyNumberFromUser();
        } while (!this.getIsValidNumber());

        return this.getNumber();
    }
}
