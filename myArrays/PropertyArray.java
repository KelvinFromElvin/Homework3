package myArrays;

import classes.Property;
import classes.PropertyType;
import utils.PrintTable;

public class PropertyArray {
    public static Property[] appentToPropertiesArray(Property[] properties, Property property) {
        // O(n)
        Property[] newProperties = new Property[properties.length + 1];

        for (int i = 0; i < properties.length; i++) {
            newProperties[i] = properties[i];
        }

        newProperties[newProperties.length - 1] = property;

        return newProperties;
    }

    public static Property[] removePropertyAt(Property[] properties, int idx) {
        // O(n)
        if (properties == null || properties.length == 0) {
            return properties;
        }

        if (idx < 0 || idx >= properties.length) {
            return properties;
        }

        Property[] newProperties = new Property[properties.length - 1];

        for (int i = 0, j = 0; i < properties.length; i++) {
            if (i == idx) {
                continue;
            }

            newProperties[j] = properties[i];

            j++;
        }

        return newProperties;
    }

    public static void printPropertiesFromArray(Property[] userProperties) {
        // O(n1 * n2 * n3 * n4)
        PrintTable printTable = new PrintTable();

        printTable.addRow();

        printTable.addData("#");
        printTable.addData("District");
        printTable.addData("City");
        printTable.addData("Street");
        printTable.addData("Number of rooms");
        printTable.addData("Price");
        printTable.addData("Type");
        printTable.addData("Renting");
        printTable.addData("House number");
        printTable.addData("Floor");
        printTable.addData("Seller name");
        printTable.addData("Seller phone");

        for (int i = 0; i < userProperties.length; i++) {
            printTable.addRow();
            printTable.addData((i + 1) + "");
            printTable.addData(userProperties[i].getCity().getDistrict().getDistrictName());
            printTable.addData(userProperties[i].getCity().getName());
            printTable.addData(userProperties[i].getStreet());
            printTable.addData(userProperties[i].getNumberOfRooms() + "");
            printTable.addData(userProperties[i].getPrice() + "");
            printTable.addData(userProperties[i].getPopertyTypeString());
            printTable.addData(userProperties[i].getAvalibleForRenting() ? "V" : "X");
            printTable.addData(userProperties[i].getHouseNumber() + "");
            if (PropertyType.isApartament(userProperties[i].getType())) {
                printTable.addData(userProperties[i].getFloor() + "");
            } else {
                printTable.addData("");
            }
            printTable.addData(userProperties[i].getUser().getUsername());
            printTable.addData(userProperties[i].getUser().getPhone());
        }

        printTable.printTable();
    }

}
