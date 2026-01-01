package classes;

import global.Globals;
import utils.MyString;
import utils.Prints;
import utils.Utils;

public class PropertyType {
    public static final int APARTAMENT = 1;
    public static final int PENTHOUSE = 2;
    public static final int HOME = 3;

    public static final int DEFAULT_PROPERTY_TYPE = PropertyType.APARTAMENT;
    public static final int INVALID_PROPERTY_TYPE = -1;

    public static boolean isPropertyType(int propertyType) {
        // O(1)
        switch (propertyType) {
            case APARTAMENT:
            case PENTHOUSE:
            case HOME:
                return true;
            default:
                return false;
        }
    }

    public static void printPropertyTypes() {
        // O(n * k)
        Prints.printfln("_§purple_%d) _§blue_Apartament", APARTAMENT);
        Prints.printfln("_§purple_%d) _§blue_Penthouse", PENTHOUSE);
        Prints.printfln("_§purple_%d) _§blue_Home", HOME);
    }

    public static boolean isApartament(int propertyType) {
        // O(1)
        switch (propertyType) {
            case APARTAMENT:
            case PENTHOUSE:
                return true;
            default:
                return false;
        }
    }

    public static String convertPropertyTypeToString(int propertyType) {
        // O(1)
        switch (propertyType) {
            case PENTHOUSE:
                return "Penthouse";
            case HOME:
                return "Home";
            default:
            case APARTAMENT:
                return "Apartment";
        }
    }

    public static int getPropertyTypeFromUser() {
        // O(n * k)
        return PropertyType.getPropertyTypeFromUserOrSkip(PropertyType.INVALID_PROPERTY_TYPE);
    }

    public static int getPropertyTypeFromUserLoopOnError(int findAll) {
        int propertyType;
        do {
            PropertyType.printPropertyTypes();
            Prints.printfln("_§purple_%d) _§blue_Find all", findAll);
            propertyType = getPropertyTypeFromUserOrSkip(findAll);
        } while (propertyType == PropertyType.INVALID_PROPERTY_TYPE);

        return propertyType;
    }

    public static int getPropertyTypeFromUserOrSkip(int skipValue) {
        // O(n * k)
        String userInput;
        int userInputNumber;

        Prints.printf("Please enter the number of property type: ");
        userInput = Globals.SCANNER.nextLine();

        if (MyString.isStringEmpty(userInput)) {
            return PropertyType.INVALID_PROPERTY_TYPE;
        }

        if (!MyString.isNumber(userInput)) {
            return PropertyType.INVALID_PROPERTY_TYPE;
        }

        userInputNumber = Utils.parseInt(userInput);

        if (!PropertyType.isPropertyType(userInputNumber)) {

            if (userInputNumber == skipValue) {
                return skipValue;
            }

            return PropertyType.INVALID_PROPERTY_TYPE;
        }

        return userInputNumber;
    }
}
