package classes;

import utils.MyString;
import utils.Prints;
import utils.Utils;

public class City {
    private String name;
    private District district;
    private String[] streets;

    public City(String name, District district) {
        this.name = name;
        this.district = district;
        this.streets = new String[0];
    }

    // Getters
    public String getName() {
        // O(1)
        return this.name;
    }

    public District getDistrict() {
        // O(1)
        return this.district;
    }

    public String[] getStreets() {
        // O(1)
        return this.streets;
    }

    public String getStreetByIdx(String streetIndex) {
        // O(n)
        final String INVALID_ARGUMENT = null;
        int streetIdx = -1;

        if (!MyString.isNumber(streetIndex)) {
            return INVALID_ARGUMENT;
        }

        streetIdx = Utils.parseInt(streetIndex) - 1;

        if (streetIdx >= 0 && streetIdx < this.streets.length) {
            return this.getStreets()[streetIdx];
        }

        return INVALID_ARGUMENT;
    }

    // public String getStreetByIdx(String street) {
    // // O(n) - because of street
    // final String INVALID_ARGUMENT = null;

    // int streetIdx = -1;

    // if (!this.isStreetExistsByIdx(street)) {
    // return INVALID_ARGUMENT;
    // }

    // streetIdx = Utils.parseInt(street) - 1;

    // return this.getStreets()[streetIdx];
    // }

    // Setters
    public void setName(String name) {
        // O(1)
        this.name = name;
    }

    public void setDistrict(District district) {
        // O(1)
        this.district = district;
    }

    // Actions
    public void addNewStreet(String street) {
        if (this.isStreetExists(street)) {
            return;
        }

        String[] newStreets = new String[this.streets.length + 1];

        for (int i = 0; i < this.streets.length; i++) {
            newStreets[i] = this.streets[i];
        }

        newStreets[newStreets.length - 1] = street;

        this.streets = newStreets;
    }

    public boolean isStreetExists(String street) {
        // O(n * k)
        street = street.toLowerCase();

        for (int i = 0; i < this.streets.length; i++) {
            if (this.streets[i].toLowerCase().equals(street)) {
                return true;
            }
        }

        return false;
    }

    public void printStreets() {
        // O(n * k)
        for (int i = 0; i < this.streets.length; i++) {
            Prints.printfln("_§purple_%d) _§blue_%s", (i + 1), this.streets[i]);
        }
    }
}
