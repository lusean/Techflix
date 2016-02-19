package com.techflix.group36.techflix;

/**
 * Created by developer on 2/3/16.
 */
public interface UserManagement {
    /** Creates a new User object and stores it in the hashmap of users
     * @param username username of user to create
     * @param password password of user to create
     * @param name name of user to create
     * @param favoriteMovie favorite movie of user to create
     */
    void addUser(String username, String password, String name, String favoriteMovie, String major);

    /** Finds a User object in the hashmap by username
     * @param username username to query for
     * @return User object for user specified by username
     */
    User findUserByUsername(String username);
}
