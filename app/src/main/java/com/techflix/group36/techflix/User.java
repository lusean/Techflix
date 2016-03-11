package com.techflix.group36.techflix;

import java.util.ArrayList;

/**
 * Created by Hrisheek on 2/1/16.
 */
public class User implements Comparable<User> {

    /**
     * The username of the User.
     */
    private String username;

    /**
     * The password of the User.
     */
    private String password;

    /**
     * The full name of the User.
     */
    private String name;

    /**
     * The favorite movie of the User.
     */
    private String favoriteMovie;

    /**
     * The major of the User.
     */
    private String major;

    /**
     * A static reference to the user currently logged in. This is set in the constructor.
     */
    public static User currentUser;

    /**
     * Status representing whether the User is locked out of account
     */
    private boolean locked;

    /**
     * Counter for login attempts
     */
    private int lockCount;

    /**
     * Status representing whether the User is banned
     */
    private boolean banned;

    /**
     * Status representing whether the User is an admin
     */
    private boolean admin;

    /** Creates a new user object and sets it as the currently-logged in user.
     * @param username username for the new user
     * @param password password for the new user
     * @param name name for the new user
     * @param favoriteMovie favorite movie of the new user
     * @param major major of the new user
     */
    public User(String username, String password, String name, String favoriteMovie, String major) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.favoriteMovie = favoriteMovie;
        this.major = major;
        currentUser = this;
    }

    /** Returns the username of this user.
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    /** Returns the password of this user.
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /** Returns the name of the user.
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /** Returns the favorite movie of the user.
     * @return favorite movie of the user
     */
    public String getFavoriteMovie() {
        return favoriteMovie;
    }

    /** Sets a new password for the user
     * @param pass password to set for the user
     */
    public void setPassword(String pass) {
        this.password = pass;
    }

    /** Sets a new favorite movie for the user
     * @param faveMovie favorite movie to set for the user
     */
    public void setFavoriteMovie(String faveMovie) {
        this.favoriteMovie = faveMovie;
    }

    /** Sets a new major for the user
     * @param majorIn major to set for the user
     */
    public void setMajor(String majorIn) {
        this.major = majorIn;
    }

    /** Sets a new name for the user
     * @param nameIn name to set for the user
     */
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    /** Gets the user's major
     * @@return the user's major
     */
    public String getMajor() { return major; }

    /** Checks if the given password is valid for this user.
     * @param password password to check
     * @return true if the password is valid for this user, false if not.
     */

    /**
     * Gets the user's locked status
     * @return the user's locked status
     */
    public boolean getLockStatus() {
        return locked;
    }

    /**
     * Gets the user's banned status
     * @return the user's banned status
     */
    public boolean getBannedStatus() {
        return banned;
    }

    /**
     * Gets the user's admin status
     * @return the user's admin status
     */
    public boolean getAdminStatus() {
        return admin;
    }

    /**
     * Increases the number of login attempts to determine Account lock status.
     * Locks the account at 3 counts or higher.
     */
    public void incrementLock() {
        lockCount++;
        if (lockCount >= 3) {
            setLockStatus(true);
        }
    }

    /**
     * Changes the user's lock status
     * @param input the new lock status
     */
    public void setLockStatus(boolean input) {
        locked = input;
    }

    /**
     * Changes the user's ban status
     * @param input the new banned status
     */
    public void setBanStatus(boolean input) {
        banned = input;
    }

    /**
     * Changes the user's admin status
     * @param input the new admin status
     */
    public void setAdminStatus(boolean input) {
        admin = input;
    }

    //MISSING JAVADOC***************************************************************************
    public ArrayList<Rating> getRatings() {
        return Rating.filterRatingsByUser(this);
    }

    @Override
    public int compareTo(User user) {
        return this.username.compareTo(user.getUsername());
    }
}
