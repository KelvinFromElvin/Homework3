package myArrays;

import classes.User;

public class UserArray {
    public static User[] appentToUsersArray(User[] users, User user) {
        // O(n)
        User[] newUsers = new User[users.length + 1];

        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }

        newUsers[newUsers.length - 1] = user;

        return newUsers;
    }
}
