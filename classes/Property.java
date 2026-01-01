package classes;

import utils.Utils;

public class Property {
    private String uuid;
    private City city;
    private String street;
    private int numberOfRooms;
    private int price;
    private int type; // PropertyType
    private boolean avalibleForRenting;
    private int houseNumber;
    private int floor;
    private User user;

    public Property() {
        this.uuid = Utils.generateUUID();
        this.city = null;
        this.street = "";
        this.numberOfRooms = 0;
        this.price = 0;
        this.type = PropertyType.DEFAULT_PROPERTY_TYPE;
        this.avalibleForRenting = false;
        this.houseNumber = 0;
        this.floor = 0;
        this.user = null;
    }

    public Property(City city, String street, int numberOfRooms, int price, int type, boolean avalibleForRenting,
            int houseNumber, int floor, User user) {
        this.uuid = Utils.generateUUID();
        this.city = city;
        this.street = street;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.type = type;
        this.avalibleForRenting = avalibleForRenting;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.user = user;
    }

    // Getters
    public String getUUID() {
        // O(1)
        return this.uuid;
    }

    public City getCity() {
        // O(1)
        return this.city;
    }

    public String getStreet() {
        // O(1)
        return this.street;
    }

    public int getNumberOfRooms() {
        // O(1)
        return this.numberOfRooms;
    }

    public int getPrice() {
        // O(1)
        return this.price;
    }

    public int getType() {
        // O(1)
        return this.type;
    }

    public boolean getAvalibleForRenting() {
        // O(1)
        return this.avalibleForRenting;
    }

    public int getHouseNumber() {
        // O(1)
        return this.houseNumber;
    }

    public int getFloor() {
        // O(1)
        return this.floor;
    }

    public User getUser() {
        // O(1)
        return this.user;
    }

    public String getPopertyTypeString() {
        // O(1)
        return PropertyType.convertPropertyTypeToString(this.type);
    }

    // Setters
    public void setCity(City city) {
        // O(1)
        this.city = city;
    }

    public void setStreet(String street) {
        // O(n * k)
        if (this.city == null) {
            return;
        }

        if (!this.city.isStreetExists(street)) {
            return;
        }

        this.street = street;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        // O(1)
        this.numberOfRooms = numberOfRooms;
    }

    public void setPrice(int price) {
        // O(1)
        if (price > 0) {
            this.price = price;
        }
    }

    public void setType(int type) {
        // O(1)
        if (!PropertyType.isPropertyType(type)) {
            return;
        }

        this.type = type;
    }

    public void setAvalibleForRenting(boolean avalibleForRenting) {
        // O(1)
        this.avalibleForRenting = avalibleForRenting;
    }

    public void setHouseNumber(int houseNumber) {
        // O(1)
        this.houseNumber = houseNumber;
    }

    public void setFloor(int floor) {
        // O(1)
        this.floor = floor;
    }

    public void setUser(User user) {
        // O(1)
        this.user = user;
    }

    // Actions
    public boolean isUserPostThisProperty(User user) {
        // O(k)
        return this.user.getUsername().equals(user.getUsername());
    }
}
