package classes;

import utils.MyString;

public class User {
    // Static Actions
    public static String isPasswordString(String password) {
        // O(k)
        /*
         * this function ask for password and check if it strong password
         * if not will return error msg
         * if password is ok then will return null;
         */
        final int MIN_PASSWORD_CHARS = 5;

        if (password.length() < MIN_PASSWORD_CHARS) {
            return "password must be atleast " + MIN_PASSWORD_CHARS + " characters";
        }

        if (!MyString.containsNumber(password)) {
            return "password must contains atleast 1 number";
        }

        if (!MyString.containsAtleats1SpChar(password)) {
            return "password must contains atleats one of the following characters $%_";
        }

        return null;
    }

    public static boolean isIsrPhoneNumbers(String phone) {
        // O(k)
        final int ISR_PHONE_NUMBER_LEN = 10;
        final String ISR_PHONE_NUMBER_PREFIX = "05";

        if (phone.length() != ISR_PHONE_NUMBER_LEN) {
            return false;
        }

        if (!phone.startsWith(ISR_PHONE_NUMBER_PREFIX)) {
            return false;
        }

        if (!MyString.isNumber(phone)) {
            return false;
        }

        return true;
    }

    // Propertis / State
    private String username;
    private String password;
    private String phone;
    private boolean isRegularUser;

    // Constractors
    public User() {
        this.username = null;
        this.password = null;
        this.phone = null;
        this.isRegularUser = false;
    }

    public User(String username, String password, String phone, boolean isRegularUser) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.isRegularUser = isRegularUser;
    }

    // Getters
    public String getUsername() {
        // O(1)
        return this.username;
    }

    public String getPassword() {
        // O(1)
        return this.password;
    }

    public String getPhone() {
        // O(1)
        return this.phone;
    }

    public boolean getIsRegularUser() {
        // O(1)
        return this.isRegularUser;
    }

    // Setters
    public void setUsername(String username) {
        // O(1)
        this.username = username;
    }

    public void setPassword(String password) {
        // O(k)
        if (User.isPasswordString(password) == null) {
            this.password = password;
        }
    }

    public void setPhone(String phone) {
        // O(k)
        if (User.isIsrPhoneNumbers(phone)) {
            this.phone = phone;
        }
    }

    public void setIsRegularUser(boolean isRegularUser) {
        // O(1)
        this.isRegularUser = isRegularUser;
    }

    // Actions
    public boolean isMyCreds(String username, String password) {
        // O(k)
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String toString() {
        // O(k * k)
        return "User: " + this.getUsername() + " - " + this.getPhone() + " - "
                + (this.getIsRegularUser() ? "regular user" : "sellins man");
    }
}
