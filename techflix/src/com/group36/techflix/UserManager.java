package com.group36.techflix;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by developer on 2/1/16.
 */
public class UserManager implements Authentication, UserManagement {
    private static Map<String, User> users = new HashMap<>();

    public boolean executeLogin(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.checkPassword(password);
    }

    public void addUser(String username, String password, String name, String favoriteMovie, String major) {
        User user = new User(username, password, name, favoriteMovie, major);
        users.put(username, user);
    }

    public User findUserByUsername(String username) {
        return users.get(username);
    }
}
