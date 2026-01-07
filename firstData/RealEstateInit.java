package firstData;

import classes.City;
import classes.District;
import classes.Property;
import classes.PropertyType;
import classes.User;

public class RealEstateInit {
    public static Property[] initProperties(User[] users, City[] cities) {
        // O(1)
        if (users == null || users.length == 0 || cities == null || cities.length == 0) {
            return null;
        }

        Property[] properties = new Property[] {
                new Property(cities[0],
                        cities[0].getStreets()[0],
                        3,
                        100,
                        PropertyType.APARTAMENT,
                        true,
                        5,
                        2, users[0]),
                new Property(cities[0],
                        cities[0].getStreets()[0],
                        4,
                        100,
                        PropertyType.PENTHOUSE,
                        true,
                        5,
                        4, users[1]),
                new Property(cities[0],
                        cities[0].getStreets()[0],
                        4,
                        100,
                        PropertyType.HOME,
                        false,
                        5,
                        0, users[1])
        };

        return properties;
    }

    public static User[] initUsers() {
        // O(1)
        User[] users = new User[] {
                new User("123", "Aa123456%", "0500000123", true),
                new User("234", "Aa123456%", "0500000234", false)
        };

        return users;
    }

    public static City[] initCities() {
        // O(n * k)
        final int CITIES_LEN = 10;

        City city;
        City[] cities;

        String[] cityNames = new String[] {
                "Haifa", // north
                "Natania", // north
                "Metola", // north
                "Afula", // north
                "Jerusalem", // center
                "Tel aviv", // center
                "Ashdod", // center
                "Raanana", // sharon
                "Ashkelon", // south
                "Beer sheva", // negev
        };

        District[] districts = new District[] {
                new District(District.NORTH),
                new District(District.NORTH),
                new District(District.NORTH),
                new District(District.NORTH),
                new District(District.CENTER),
                new District(District.CENTER),
                new District(District.CENTER),
                new District(District.SHARON),
                new District(District.SOUTH),
                new District(District.NEGEV)
        };

        String[] streets = new String[] {
                "Eli cohen",
                "Rubin",
                "Aviha",
                "Shay"
        };

        cities = new City[CITIES_LEN];

        for (int i = 0; i < cityNames.length; i++) {
            city = new City(cityNames[i], districts[i]);

            for (int j = 0; j < streets.length; j++) {
                city.addNewStreet(streets[j]);
            }

            cities[i] = city;
        }

        return cities;
    }
}
