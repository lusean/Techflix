package com.techflix.group36.techflix.User;

import android.util.Log;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Rating.Rating;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by developer on 2/1/16.
 */
@SuppressWarnings("ALL")
public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    public final static String DEFAULT_BINARY_FILE_NAME = "data12.bin";

    /**
     * Constructor of a UserManager, which creates a HeadAdmin and puts it in the HashMap.
     */
    public UserManager() {
        User adminUser = new User("admin", "2340", "ADMIN", "none", "ADMIN");
        adminUser.setAdminStatus(true);
        users.put("admin", adminUser);
    }

    /**
     * Gets the users map
     * @return the map of users and usernames
     */
    public Map<String, User> getUsers() {
        return users;
    }

    /** Logs in user specified by given user name and password
     * @param username username of the user to login
     * @param password password of the user to login
     * @return boolean that is true if the login is valid for the given username, false if not
     */
    public boolean executeLogin(String username, String password) {
        User user = findUserByUsername(username);
        if (user != null
                && !user.getBannedStatus()
                && !user.getLockStatus()
                && user.getPassword().equals(password)) {
            User.currentUser = user;
            return true;
        } else {
            return false;
        }
    }

    /** Creates a new User object and stores it in the hashmap of users
     * @param username username of user to create
     * @param password password of user to create
     * @param name name of user to create
     * @param favoriteMovie favorite movie of user to create
     */
    public void addUser(String username, String password, String name, String favoriteMovie,
                        String major) {
        if (username == null || password == null || name == null || favoriteMovie == null
                || major == null) {
            throw new IllegalArgumentException("Cannot add a User with null data.");
        }
        if (findUserByUsername(username) == null) {
            User user = new User(username, password, name, favoriteMovie, major);
            users.put(username, user);
        } else {
            throw new IllegalArgumentException("User with this username already exists.");
        }
    }



    /** Finds a User object in the hashmap by username
     * @param username username to query for
     * @return User object for user specified by username
     */
    public User findUserByUsername(String username) {
        return users.get(username);
    }

    /** Allows you to edit a user's status
     * @param username username to query for
     */
    public void editUserStatus(String username, boolean lockStatus, boolean adminStatus, boolean banStatus) {
        User temp = findUserByUsername(username);
        temp.setBanStatus(banStatus);
        temp.setLockStatus(lockStatus);
        temp.setAdminStatus(adminStatus);
    }
    /**
     * Obtains the UserManager HashMap instance variable
     * @return The HaspMap backing the UserManager
     */
    public static Map<String, User> getUserMap() {
        return users;
    }

    public static boolean loadBinary(File file) {
        boolean success = true;
        try {
            /*
              To read, we must use the ObjectInputStream since we want to read our model in with
              a single read.
             */
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // assuming we saved our top level object, we read it back in with one line of code.
            /*
            Map<String, User> userList = (Map<String, User>) in.readObject();
            HashMap<Integer, Rating> ratingList = (HashMap<Integer, Rating>) in.readObject();
            ArrayList<Movie> ratedMovieList = (ArrayList<Movie>) in.readObject();
            */
            SaveObject sObj = (SaveObject) in.readObject();
            users = sObj.getUserList();
            //noinspection unchecked
            Rating.setRatings((HashMap<Integer, Rating>) in.readObject());
            //noinspection unchecked
            Movie.setRatedMovies((ArrayList<Movie>) in.readObject());
            in.close();
        } catch (IOException e) {
            Log.e("UserManager", "Error reading an entry from binary file");
            //Log.e("UserManager", e.printStackTrace());
            e.printStackTrace();
            success = false;
        } catch (ClassNotFoundException e) {
            Log.e("UserManager", "Error casting a class from the binary file");
            Log.e("UserManager", e.getMessage());
            success = false;
        }

        return success;
    }

    public static boolean saveBinary(File file) {
        boolean success = true;
        try {
            /*
               For binary, we use Serialization, so everything we write has to implement
               the Serializable interface.  Fortunately all the collection classes and APi classes
               that we might use are already Serializable.  You just have to make sure your
               classes implement Serializable.

               We have to use an ObjectOutputStream to write objects.

               One thing to be careful of:  You cannot serialize static data.
             */


            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            // We basically can save our entire data model with one write, since this will follow
            // all the links and pointers to save everything.  Just save the top level object.
            Map<String, User> userList = users;
            SaveObject sObj = new SaveObject(userList);
            out.writeObject(sObj);
            HashMap<Integer, Rating> ratingList = Rating.getRatings();
            ArrayList<Movie> ratedMoviesList = Movie.getRatedMovies();
            out.writeObject(ratingList);
            out.writeObject(ratedMoviesList);
            /*
            if (userList != null) {
                out.writeObject(userList);
            }
            if (ratingList != null) {
                out.writeObject(ratingList);
            }
            if (ratedMovieList != null) {
                out.writeObject(ratedMovieList);
            }
            */
            out.close();

        } catch (IOException e) {
            Log.e("UserManager", "Error writing an entry from binary file");
            Log.e("UserManager", e.getMessage());
            success = false;
        }

        return success;
    }
}
