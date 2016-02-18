package com.group36.techflix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by akeaswaran on 2/16/16.
 */
public class UserManagerTest {
    private UserManager manager;
    private String[] userInfo = {"akeaswaran", "password", "Akshay Easwaran", "Skyfall", "Computer Science"};

    @Before
    public void setUp() throws Exception {
        manager = new UserManager();
        manager.addUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
    }

    @Test
    public void testAddUser() throws Exception {
        User addedUser = UserManager.users.get(userInfo[0]);
        assertEquals(addedUser.getUsername(), userInfo[0]);
        assertEquals(addedUser.getPassword(), userInfo[1]);
        assertEquals(addedUser.getName(), userInfo[2]);
        assertEquals(addedUser.getFavoriteMovie(), userInfo[3]);
        assertEquals(addedUser.getMajor(), userInfo[4]);
    }

    @Test
    public void testExecuteLogin() throws Exception {
        assertTrue(manager.executeLogin("akeaswaran", "password"));
        assertFalse(manager.executeLogin("akeaswaran", "notpassword"));
    }

    @Test
    public void testFindUserByUsername() throws Exception {
        assertEquals(manager.findUserByUsername("akeaswaran"),  UserManager.users.get(userInfo[0]));
        assertNull(manager.findUserByUsername("hrisheek"));
    }
}