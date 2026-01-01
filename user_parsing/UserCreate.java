package user_parsing;

import classes.User;
import global.Globals;
import utils.Prints;

public class UserCreate {
    public static String getStrongPasswordFromUser() {
        // O(k * k)
        String password;
        String errorMsg;

        do {
            Prints.printf("Please enter strong password (min 5 chars, atleats $ %% _,atleats 1 number): ");

            password = Globals.SCANNER.nextLine();

            errorMsg = User.isPasswordString(password);

            if (errorMsg != null) {
                Prints.printErrorMsg("%s", errorMsg);
            }
        } while (errorMsg != null);

        return password;
    }

    public static String getPhoneFromUser() {
        // O(n * k)
        String phone;

        boolean isPhoneValid;

        do {
            Prints.printf("Please enter phone number: ");
            phone = Globals.SCANNER.nextLine();

            isPhoneValid = User.isIsrPhoneNumbers(phone);

            if (!isPhoneValid) {
                Prints.printErrorMsg("%s is not a valid israel phone number, please choose valid israel phone number",
                        phone);
            }
        } while (!isPhoneValid);

        return phone;
    }

    public static boolean getIsRegularUserFromUser() {
        // O(k)
        return UserBoolean.getUserBoolean("Are you regular user?");
    }
}
