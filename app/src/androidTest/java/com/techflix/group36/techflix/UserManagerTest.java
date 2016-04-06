package com.techflix.group36.techflix;

import android.app.Application;
import android.test.ApplicationTestCase;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;


import org.junit.Before;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Hrisheek on 4/3/16.
 * Altered by Scott on 4/3/16
 */
@SuppressWarnings({"DefaultFileTemplate"})
public class UserManagerTest extends ApplicationTestCase<Application> {
    private UserManager um;
    private User user;
    private User user2;
    private User user3;
    private User user4;

    public UserManagerTest() {
        super(Application.class);
    }

    @Before
    public void setUp() {
        um = new UserManager();
        user = new User("Hrisheek", "test", "Hrisheek", "Fight Club", "Computer Science");
        user2 = new User("Omar", "PASSWORD", "Omar", "Nothing", "Computer Science");
        user2.setBanStatus(true);
        user3 = new User("Sean", "PasWoRD", "Sean", "~~", "Computer Science");
        user3.setLockStatus(true);
        user4 = new User("Scott", "password", "Scott", "Eve no jikan", "Computer Science");
        UserManager.getUserMap().put("Omar", user2);
        UserManager.getUserMap().put("Sean", user3);
        UserManager.getUserMap().put("Scott", user4);

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
        UserManager.getUserMap().put("Hrisheek", user);
        try {
            um.addUser("Hrisheek", "test", "Hrisheek", "Fight Club", "Computer Science");
            fail("User was added despite the username already existing in the list of users.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("User with this username already exists."));
        }
    }

    public void testAddUserSuccess() {
        um.addUser("Akshay", "test", "Akshay", "Star Trek", "Computer Science");
        assertTrue(UserManager.getUserMap().containsKey("Akshay"));
    }



    //Scott's Tests

    public void testNullUser() {
        String username = "Nope";
        assertNull(um.findUserByUsername(username));
        assertFalse(um.executeLogin(username, "password"));
    }

    public void testBannedFailsLogin() {
        String pass = "PASSWORD";
        assertTrue(user2 != null);
        assertTrue(user2.getPassword().equals(pass));
        assertFalse(user2.getLockStatus());

        assertTrue(user2.getBannedStatus());
        assertFalse(um.executeLogin(user2.getUsername(), pass));
    }

    public void testLockedFailsLogin() {
        String pass = "PasWoRD";
        assertTrue(user3 != null);
        assertTrue(user3.getPassword().equals(pass));
        assertFalse(user3.getBannedStatus());

        assertTrue(user3.getLockStatus());
        assertFalse(um.executeLogin(user3.getUsername(), pass));
    }

    public void testWrongPassword() {
        String pass = "passwords";
        assertTrue(user4 != null);
        assertFalse(user4.getBannedStatus());
        assertFalse(user4.getLockStatus());
        assertFalse(user4.getPassword().equals(pass));
        assertFalse(um.executeLogin(user4.getUsername(), pass));
    }

    public void testSuccessfulLogin() {
        assertTrue(um.executeLogin("Scott", "password"));
    }

}