package menus;

import classes.User;
import global.Globals;
import user_parsing.UserMenuInput;
import user_parsing.UserMenuInputOption;
import utils.Prints;

public class MainMenu {
    public final static int CREATE_NEW_ACCOUNT = 1;
    public final static int LOGIN_TO_YOUR_ACCOUNT = 2;
    public final static int EXIT_FROM_SOFT = 3;

    public final static int DEFAULT_OPTION = EXIT_FROM_SOFT;

    public static int decodeUserInput(String userInput) {
        // O(n * n)
        UserMenuInputOption[] options = new UserMenuInputOption[] {
                new UserMenuInputOption(CREATE_NEW_ACCOUNT, new char[] {
                        '1', 'c'
                }),
                new UserMenuInputOption(LOGIN_TO_YOUR_ACCOUNT, new char[] {
                        '2', 'l'
                }),
                new UserMenuInputOption(EXIT_FROM_SOFT, new char[] {
                        '3', 'e'
                })
        };

        return UserMenuInput.decodeUserInput(userInput, options, DEFAULT_OPTION);
    }

    public static void mainManu() {
        // O(n1 * n2 * n3 * n4 * n5)
        int decodedUserInput;
        User user;

        Prints.printfln("_§cyan_==========================================_§");
        Prints.printfln("_§cyan_==== _§blue_Welcome to RealEstate board _§red_3000 _§cyan_====_§");
        Prints.printfln("_§cyan_==========================================_§");
        Prints.printfln("_§purple_%d) _§red_C_§blue_reate new account_§", MainMenu.CREATE_NEW_ACCOUNT);
        Prints.printfln("_§purple_%d) _§red_L_§blue_ogin to your account_§", MainMenu.LOGIN_TO_YOUR_ACCOUNT);
        Prints.printfln("_§purple_%d) _§red_E_§blue_xit_§", MainMenu.EXIT_FROM_SOFT);

        Prints.printf("Please enter your option: ");
        decodedUserInput = MainMenu.decodeUserInput(Globals.SCANNER.nextLine());// O(n * n)

        switch (decodedUserInput) {
            case MainMenu.CREATE_NEW_ACCOUNT:
                Globals.realEstate.createUser();
                break;
            case MainMenu.LOGIN_TO_YOUR_ACCOUNT:
                user = Globals.realEstate.login();
                if (user != null) {
                    UserMenu.userMenu(user);
                }
                break;
            case MainMenu.EXIT_FROM_SOFT:
            default:
                Globals.exitFromSoft = true;
                break;
        }
    }
}
