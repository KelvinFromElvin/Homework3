package classes;

import firstData.RealEstateInit;
import global.Globals;
import myArrays.PropertyArray;
import myArrays.UserArray;
import user_parsing.UserBoolean;
import user_parsing.UserCreate;
import user_parsing.UserInteger;
import utils.MyString;
import utils.Prints;
import utils.Utils;

public class RealEstate {
    private static final int POST_LIMIT_REGULAR_USER = 2;
    private static final int POST_LIMIT_SAILS_MAN_USER = 5;
    private static final int FIND_ALL = -999;

    private static final int PROPERTY_NOT_FOUND = -1;
    private static final City INVALID_CITY = null;

    private User[] users;
    private Property[] properties;
    private City[] cities;

    public RealEstate() {
        this.initUsers();
        this.initCities();
        this.initProperties();
    }

    private void initProperties() {
        // O(1)
        this.properties = RealEstateInit.initProperties(this.users, this.cities);
    }

    private void initUsers() {
        // O(1)
        this.users = RealEstateInit.initUsers();
    }

    private void initCities() {
        // O(n * k)
        this.cities = RealEstateInit.initCities();
    }

    // Create new user
    public void createUser() {
        // O(n * k * k)
        User user = new User();

        user.setUsername(this.getFreeUsernameFromUser());
        user.setPassword(UserCreate.getStrongPasswordFromUser());
        user.setPhone(UserCreate.getPhoneFromUser());
        user.setIsRegularUser(UserCreate.getIsRegularUserFromUser());

        this.users = UserArray.appentToUsersArray(this.users, user);

        Prints.printSuccessMsg(user.toString() + " created successfully!");
    }

    private boolean checkIfUsernameExists(String username) {
        // O(n * k)
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].isUsernameEquals(username)) {
                return true;
            }
        }

        return false;
    }

    private String getFreeUsernameFromUser() {
        // O(n * k * k)
        String username;

        boolean isUsernameExists;
        boolean isInvalidInput;

        do {
            Prints.printf("Please enter username: ");
            username = Globals.SCANNER.nextLine();

            isInvalidInput = false;
            isUsernameExists = true;

            if (MyString.isStringEmpty(username)) {
                Prints.printErrorMsg("Please input something");
                isInvalidInput = true;
                continue;
            }

            isUsernameExists = this.checkIfUsernameExists(username);

            if (isUsernameExists) {
                Prints.printErrorMsg("%s already exists, please choose another username", username);
            }
        } while (isUsernameExists || isInvalidInput);

        return username;
    }

    // Login
    public User login() {
        // O(n * k)
        String username, password;
        User user;

        Prints.printf("Please enter your username: ");
        username = Globals.SCANNER.nextLine();

        Prints.printf("Please enter your password: ");
        password = Globals.SCANNER.nextLine();

        user = this.findUser(username, password);

        if (user == null) {
            Prints.printErrorMsg("Invalid username/password");
        }

        return user;
    }

    private User findUser(String username, String password) {
        // O(n * k)
        final User INVALID_ARGS = null;

        if (MyString.isStringEmpty(username) || MyString.isStringEmpty(password)) {
            return INVALID_ARGS;
        }

        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].isMyCreds(username, password)) {
                return this.users[i];
            }
        }

        return INVALID_ARGS;
    }

    // Post new propery
    public boolean postNewProperty(User user) {
        // O(n * k * t)
        City selectedCity;
        String selectedStreet;
        int selectedType;
        Property property = new Property();
        UserInteger userInteger = new UserInteger();

        if (!this.isUserAllowedToPostNewProperty(user)) {
            Prints.printErrorMsg("You riched the limit of properits to post.");
            return false;
        }

        this.printCities();
        selectedCity = getCityFromUser();
        if (selectedCity == INVALID_CITY) {
            Prints.printErrorMsg("You did not select city from list.");
            return false;
        }

        selectedCity.printStreets();
        selectedStreet = this.getStreetFromUser(selectedCity);
        if (selectedStreet == null) {
            Prints.printErrorMsg("You did not select street from list.");
            return false;
        }

        PropertyType.printPropertyTypes();
        selectedType = PropertyType.getPropertyTypeFromUser();
        if (selectedType == PropertyType.INVALID_PROPERTY_TYPE) {
            Prints.printErrorMsg("You did not select property type from list.");
            return false;
        }

        property.setCity(selectedCity);
        property.setStreet(selectedStreet);
        property.setType(selectedType);

        if (PropertyType.isApartament(selectedType)) {
            property.setFloor(userInteger.getNumberFromUser("Please enter the floor: "));
        }

        property.setNumberOfRooms(userInteger.getNumberFromUser("Please enter the number of rooms: "));
        property.setHouseNumber(userInteger.getNumberFromUser("Please enter house number: "));
        property.setAvalibleForRenting(UserBoolean.getUserBoolean("Renting?"));

        do {
            userInteger.getNumberFromUser("Please enter property price (price > 0): ");
        } while (userInteger.getNumber() <= 0);
        property.setPrice(userInteger.getNumber());

        property.setUser(user);

        this.properties = PropertyArray.appentToPropertiesArray(this.properties, property);

        return true;
    }

    private int countPublishedProperiesOfUser(User user) {
        // O(n * k)
        int count = 0;

        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].isUserPostThisProperty(user)) {
                count++;
            }
        }

        return count;
    }

    private boolean isUserAllowedToPostNewProperty(User user) {
        // O(n * k)
        int postedPropertis = this.countPublishedProperiesOfUser(user);

        if (user.getIsRegularUser() && postedPropertis >= RealEstate.POST_LIMIT_REGULAR_USER) {
            return false;
        }

        if (!user.getIsRegularUser() && postedPropertis >= RealEstate.POST_LIMIT_SAILS_MAN_USER) {
            return false;
        }

        return true;
    }

    private void printCities() {
        // O(n * k * t)
        for (int i = 0; i < this.cities.length; i++) {
            Prints.printfln("_§purple_%d)_§ %s - _§blue_%s", (i + 1), this.cities[i].getDistrict().getDistrictName(),
                    this.cities[i].getName());
        }
    }

    private City getCityFromUser() {
        // O(n * k)
        String cityInput;
        City selectedCity;

        Prints.printf("Please enter the number / name of city: ");
        cityInput = Globals.SCANNER.nextLine();

        if (MyString.isStringEmpty(cityInput)) {
            return INVALID_CITY;
        }

        if (MyString.isNumber(cityInput)) {
            selectedCity = this.getCityByCityNumber(cityInput);
        } else {
            selectedCity = this.getCityByCityStr(cityInput);
        }

        return selectedCity;
    }

    private City getCityByCityNumber(String cityNumber) {
        // O(k)
        int cityIdx = -1;

        if (!MyString.isNumber(cityNumber)) {
            return INVALID_CITY;
        }

        cityIdx = Utils.parseInt(cityNumber) - 1;

        if (cityIdx >= 0 && cityIdx < this.cities.length) {
            return this.cities[cityIdx];
        }

        return INVALID_CITY;
    }

    private City getCityByCityStr(String city) {
        // O(n * k)
        for (int i = 0; i < this.cities.length; i++) {
            if (this.cities[i].isSameName(city)) {
                return this.cities[i];
            }
        }

        return INVALID_CITY;
    }

    private String getStreetFromUser(City city) {
        // O(n * k)
        final String INVALID_ARG = null;
        String streetInput;

        Prints.printf("Please enter the number / name of city: ");
        streetInput = Globals.SCANNER.nextLine();

        if (MyString.isStringEmpty(streetInput)) {
            return INVALID_ARG;
        }

        if (MyString.isNumber(streetInput)) {
            return city.getStreetByIdx(streetInput);
        } else if (city.isStreetExists(streetInput)) {
            return streetInput;
        }

        return INVALID_ARG;
    }

    // Remove users property
    public void removePropertyMenu(User user) {
        // O(n1 * n2 * n3 * n4)
        Property[] userProperties;
        int propertyIdxToRemove;
        UserInteger userInteger = new UserInteger();

        if (user == null) {
            Prints.printErrorMsg("Please login");
            return;
        }

        if (!this.didUserPostProperty(user)) {
            Prints.printErrorMsg("You did not post any properties.");
            return;
        }

        userProperties = this.getUserPostedProperties(user);

        PropertyArray.printPropertiesFromArray(userProperties);

        propertyIdxToRemove = userInteger.getNumberFromUser("Please choose the number of property to remove: ") - 1;

        if (propertyIdxToRemove < 0 || propertyIdxToRemove >= userProperties.length) {
            Prints.printErrorMsg("Cannot delete this property");
        } else {
            Prints.printSuccessMsg("Property deleted");
            this.removePropertyByUUID(userProperties[propertyIdxToRemove].getUUID());
        }
    }

    private boolean didUserPostProperty(User user) {
        // O(n * k)
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getUser().isUsernameEquals(user.getUsername())) {
                return true;
            }
        }

        return false;
    }

    private Property[] getUserPostedProperties(User user) {
        // O(n * n * k)
        Property[] userProperty = new Property[0];

        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].isUserPostThisProperty(user)) {
                userProperty = PropertyArray.appentToPropertiesArray(userProperty, this.properties[i]);
            }
        }

        return userProperty;
    }

    private int findPropertyByUUID(String uuid) {
        // O(n * k)
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].isUUIDEquals(uuid)) {
                return i;
            }
        }

        return PROPERTY_NOT_FOUND;
    }

    private void removePropertyByUUID(String uuid) {
        // O(n * k)
        int idxToRemove = this.findPropertyByUUID(uuid);

        if (idxToRemove == PROPERTY_NOT_FOUND) {
            return;
        }

        this.properties = PropertyArray.removePropertyAt(this.properties, idxToRemove);
    }

    // Print all priperties
    public void printAllProperties() {
        // O(n1 * n2 * n3 * n4)
        PropertyArray.printPropertiesFromArray(this.properties);
    }

    // print user priperties
    public void printUserProperties(User user) {
        // O(n1 * n2 * n3 * n4)
        PropertyArray.printPropertiesFromArray(this.getUserPostedProperties(user));
    }

    // Search property menu
    public Property[] search() {
        // O(n * n * n)
        int rentingUserInput = FIND_ALL;
        boolean renting = false;
        int propertyType = FIND_ALL;
        int roomNumber = FIND_ALL;
        int minUserPrice = FIND_ALL, maxUserPrice = FIND_ALL;
        UserInteger userInteger = new UserInteger();

        Prints.printfln("What property you looking for?");

        rentingUserInput = UserBoolean.getUserBooleanOrSkip("do you want to rent?", FIND_ALL + "");
        renting = rentingUserInput == UserBoolean.USER_INPUT_TRUE;

        propertyType = PropertyType.getPropertyTypeFromUserLoopOnError(FIND_ALL);

        roomNumber = userInteger
                .getNumberFromUser("Please enter the number of rooms_§blue_(" + FIND_ALL + " to skip)_§: ");

        minUserPrice = userInteger
                .getNumberFromUser("Please enter the minimum price_§blue_(" + FIND_ALL + " to skip)_§: ");
        maxUserPrice = userInteger
                .getNumberFromUser("Please enter the maximum price_§blue_(" + FIND_ALL + " to skip)_§: ");

        return this.searchProperiesByParameters(
                renting,
                rentingUserInput == UserBoolean.USER_INPUT_SKIP,
                propertyType,
                roomNumber,
                minUserPrice, maxUserPrice);
    }

    private Property[] searchProperiesByParameters(
            boolean renting,
            boolean findAllRentings,
            int propertyType,
            int roomNumber,
            int minUserPrice,
            int maxUserPrice) {
        // O(n * n * n)
        int minPrice = FIND_ALL, maxPrice = FIND_ALL;
        Property[] matchingProperties = new Property[0];

        if (minUserPrice == FIND_ALL) {
            minPrice = minUserPrice;
        }
        if (maxUserPrice == FIND_ALL) {
            maxPrice = maxUserPrice;
        }
        if (minUserPrice != FIND_ALL && maxUserPrice != FIND_ALL) {
            minPrice = Math.min(minUserPrice, maxUserPrice);
            maxPrice = Math.max(minUserPrice, maxUserPrice);
        }

        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getAvalibleForRenting() != renting && !findAllRentings) {
                continue;
            }

            if (this.properties[i].getType() != propertyType && propertyType != FIND_ALL) {
                continue;
            }

            if (this.properties[i].getNumberOfRooms() != roomNumber && roomNumber != FIND_ALL) {
                continue;
            }

            if (minPrice != FIND_ALL && minPrice > this.properties[i].getPrice()) {
                continue;
            }

            if (maxPrice != FIND_ALL && maxPrice < this.properties[i].getPrice()) {
                continue;
            }

            matchingProperties = PropertyArray.appentToPropertiesArray(matchingProperties, this.properties[i]);
        }

        return matchingProperties;
    }
}
