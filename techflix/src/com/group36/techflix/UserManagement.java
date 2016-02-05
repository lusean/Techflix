package com.group36.techflix;

/**
 * Created by developer on 2/3/16.
 */
public interface UserManagement {
    void addUser(String username, String password, String name, String favoriteMovie);
    User findUserByUsername(String username);
}
