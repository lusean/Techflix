package com.group36.techflix;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by developer on 2/1/16.
 */
public class UserManager implements Authentication, UserManagement {
    public static Map<String, User> users = new HashMap<>();
    private static final String adminCode = "2340";


    /** Logs in user specified by given user name and password
     * @param username username of the user to login
     * @param password password of the user to login
     * @return boolean that is true if the password is valid for the given username, false if not
     */
    public boolean executeLogin(String username, String password, User user) {
        return user != null
                && !user.getBannedStatus()
                && !user.getLockStatus()
                && user.getPassword().equals(password);
    }

    /** Creates a new User object and stores it in the hashmap of users
     * @param username username of user to create
     * @param password password of user to create
     * @param name name of user to create
     * @param favoriteMovie favorite movie of user to create
     */
    public void addUser(String username, String password, String name, String favoriteMovie, String major) {
        User user = new User(username, password, name, favoriteMovie, major);
        users.put(username, user);
    }

    /** Finds a User object in the hashmap by username
     * @param username username to query for
     * @return User object for user specified by username
     */
    public User findUserByUsername(String username) {
        return users.get(username);
    }

    /**
     * Gets the adminCode for this application
     * @return the admin code for the application
     */
    public String getAdminCode() {
        return adminCode;
    }
}
