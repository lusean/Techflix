package com.group36.techflix;

/**
 * Created by developer on 2/3/16.
 */
public interface Authentication {
    /**
     * Logs in user specified by given user name and password
     * @param username username of the user to login
     * @param password password of the user to login
     * @param user predicted user logging in
     * @return boolean that is true if the password is valid for the given username, false if not
     */
    boolean executeLogin(String username, String password, User user);
}
