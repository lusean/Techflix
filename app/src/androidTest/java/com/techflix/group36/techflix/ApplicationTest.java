package com.techflix.group36.techflix;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Rating.Rating;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;

import org.junit.Test;

import java.util.ArrayList;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    private UserManager um;
    private User user;

    public void setUp() {
        um = new UserManager();
        user = new User("Hrisheek", "test", "Hrisheek", "Fight Club", "Computer Science");
    }

    public void testAddUserNullUsername() {
        try {
            um.addUser(null, "test", "Hrisheek", "Fight Club", "Computer Science");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddUserNullPassword() {
        try {
            um.addUser("Hrisheek", null, "Hrisheek", "Fight Club", "Computer Science");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddUserNullName() {
        try {
            um.addUser("Hrisheek", "test", null, "Fight Club", "Computer Science");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddUserNullMovie() {
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", null, "Computer Science");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddUserNullMajor() {
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", "Fight Club", null);
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddUserExistsAlready() {
        um.getUsers().put("Hrisheek", user);
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", "Fight Club", "Computer Science");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddUserSuccess() {
        um.addUser("Akshay", "test", "Akshay", "Star Trek", "Computer Science");
        assertTrue(um.getUsers().containsKey("Akshay"));
    }
}