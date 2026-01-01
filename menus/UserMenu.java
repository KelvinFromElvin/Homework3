package menus;

import classes.User;
import global.Globals;
import myArrays.PropertyArray;
import user_parsing.UserMenuInput;
import user_parsing.UserMenuInputOption;
import utils.MyString;
import utils.Prints;

public class UserMenu {
    public static final int POST_NEW_PROPERTY = 1;
    public static final int DELETE_PROPERTY = 2;
    public static final int SHOW_ALL_PROPERTY = 3;
    public static final int SHOW_ALL_MY_PROPERTY = 4;
    public static final int SEARCH_PROPERTY_BY_PARAMETER = 5;
    public static final int LOGOUT = 6;

    public static final int DEFAULT_OPTION = LOGOUT;

    public static boolean exitFromMenu = false;

    public static void userMenu(User user) {
        // O(n1 * n2 * n3 * n4 * n5)
        String title = "_§cyan_==== _§blue_Welcome back _§red_%s_§blue_! _§cyan_====";

        Object[] args = new Object[] {
                user.getUsername()
        };

        String titleMarkup = MyString.convertPrintfStrToStr(title, args);

        printEqualsForTitle(titleMarkup);
        Prints.printfln(title, args);
        printEqualsForTitle(titleMarkup);

        exitFromMenu = false;

        do {
            menuLoop(user);
        } while (!exitFromMenu);
    }

    private static void menuLoop(User user) {
        // O(n1 * n2 * n3 * n4)
        int decodedUserInput;
        printMenu();

        Prints.printf("Please enter your option: ");
        decodedUserInput = UserMenu.decodeUserInput(Globals.SCANNER.nextLine());

        switch (decodedUserInput) {
            case POST_NEW_PROPERTY:
                postNewProperty(user);
                break;
            case DELETE_PROPERTY:
                Globals.realEstate.removePropertyMenu(user);
                break;
            case SHOW_ALL_PROPERTY:
                Globals.realEstate.printAllProperties();
                break;
            case SHOW_ALL_MY_PROPERTY:
                Globals.realEstate.printUserProperties(user);
                break;
            case SEARCH_PROPERTY_BY_PARAMETER:
                PropertyArray.printPropertiesFromArray(Globals.realEstate.search());
                break;
            case LOGOUT:
            default:
                System.out.println("!!!!!!!!!!!!!!!");
                exitFromMenu = true;
                break;
        }
    }

    private static void printEqualsForTitle(String title) {
        // O(n * k)
        String str = "_§cyan_";

        for (int i = 0; i < title.length(); i++) {
            str += "=";
        }

        Prints.printfln(str);
    }

    private static void printMenu() {
        // O(n * k)
        Prints.printfln("_§purple_1) _§red_P_§blue_ost new property"); // 1 | p
        Prints.printfln("_§purple_2) _§red_D_§blue_elete existing property"); // 2 | d
        Prints.printfln("_§purple_3) _§red_S_§blue_how all properties"); // 3 | s
        Prints.printfln("_§purple_4) _§blue_Show _§red_a_§blue_ll my properties"); // 4 | a
        Prints.printfln("_§purple_5) _§blue_S_§red_e_§blue_arch property by parameters"); // 5 | e
        Prints.printfln("_§purple_6) _§red_L_§blue_ogout"); // 6 | l
    }

    public static int decodeUserInput(String userInput) {
        // O(n * n)
        UserMenuInputOption[] options = new UserMenuInputOption[] {
                new UserMenuInputOption(POST_NEW_PROPERTY, new char[] {
                        '1', 'p'
                }),
                new UserMenuInputOption(DELETE_PROPERTY, new char[] {
                        '2', 'd'
                }),
                new UserMenuInputOption(SHOW_ALL_PROPERTY, new char[] {
                        '3', 's'
                }),
                new UserMenuInputOption(SHOW_ALL_MY_PROPERTY, new char[] {
                        '4', 'a'
                }),
                new UserMenuInputOption(SEARCH_PROPERTY_BY_PARAMETER, new char[] {
                        '5', 'e'
                }),
                new UserMenuInputOption(LOGOUT, new char[] {
                        '6', 'l'
                }),
        };

        return UserMenuInput.decodeUserInput(userInput, options, DEFAULT_OPTION);
    }

    public static void postNewProperty(User user) {
        // O(n * k * t)
        boolean isPropertyPosted = Globals.realEstate.postNewProperty(user);

        if (isPropertyPosted) {
            Prints.printfln("_§blue_Property added successfuly");
        } else {
            Prints.printErrorMsg("Fail to add property");
        }
    }
}
