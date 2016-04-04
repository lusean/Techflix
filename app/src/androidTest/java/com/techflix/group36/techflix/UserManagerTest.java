package com.techflix.group36.techflix;

import android.app.Application;
import android.test.ApplicationTestCase;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Hrisheek on 4/3/16.
 */
public class UserManagerTest extends ApplicationTestCase<Application> {
    private UserManager um;
    private User user;

    public UserManagerTest() {
        super(Application.class);
    }

    public void setUp() {
        um = new UserManager();
        user = new User("Hrisheek", "test", "Hrisheek", "Fight Club", "Computer Science");
    }

    public void testAddUserNullUsername() {
        try {
            um.addUser(null, "test", "Hrisheek", "Fight Club", "Computer Science");
            fail("User was added despite having a null username.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Cannot add a User with null data."));
        }
    }

    public void testAddUserNullPassword() {
        try {
            um.addUser("Hrisheek", null, "Hrisheek", "Fight Club", "Computer Science");
            fail("User was added despite having a null password.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Cannot add a User with null data."));
        }
    }

    public void testAddUserNullName() {
        try {
            um.addUser("Hrisheek", "test", null, "Fight Club", "Computer Science");
            fail("User was added despite having a null name.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Cannot add a User with null data."));
        }
    }

    public void testAddUserNullMovie() {
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", null, "Computer Science");
            fail("User was added despite having a null favorite movie.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Cannot add a User with null data."));
        }
    }

    public void testAddUserNullMajor() {
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", "Fight Club", null);
            fail("User was added despite having a null major.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Cannot add a User with null data."));
        }
    }

    public void testAddUserExistsAlready() {
        um.getUsers().put("Hrisheek", user);
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", "Fight Club", "Computer Science");
            fail("User was added despite the username already existing in the list of users.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("User with this username already exists."));
        }
    }

    public void testAddUserSuccess() {
        um.addUser("Akshay", "test", "Akshay", "Star Trek", "Computer Science");
        assertTrue(um.getUsers().containsKey("Akshay"));
    }
}
