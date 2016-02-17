package com.group36.techflix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by akeaswaran on 2/16/16.
 */
public class UserTest {
    private String[] userInfo = {"akeaswaran", "password", "Akshay Easwaran", "Skyfall", "Computer Science"};
    private User user;
    @Before
    public void setUp() throws Exception {
        user = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
    }

    @Test
    public void testGetUsername() throws Exception {
        assertEquals(user.getUsername(), "akeaswaran");
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals(user.getPassword(), userInfo[1]);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(user.getName(), userInfo[2]);
    }

    @Test
    public void testGetFavoriteMovie() throws Exception {
        assertEquals(user.getFavoriteMovie(), userInfo[3]);
    }

    @Test
    public void testGetMajor() throws Exception {
        assertEquals(user.getMajor(), userInfo[4]);
    }

    @Test
    public void testSetUsername() throws Exception {
        user.setUsername("akeaswaran15");
        assertEquals(user.getUsername(), "akeaswaran15");
    }

    @Test
    public void testSetPassword() throws Exception {
        user.setPassword("newPass");
        assertEquals(user.getPassword(), "newPass");
    }

    @Test
    public void testSetFavoriteMovie() throws Exception {
        user.setFavoriteMovie("Casino Royale");
        assertEquals(user.getFavoriteMovie(), "Casino Royale");
    }

    @Test
    public void testSetMajor() throws Exception {
        user.setMajor("Computer Engineering");
        assertEquals(user.getMajor(), "Computer Engineering");
    }

    @Test
    public void testSetName() throws Exception {
        user.setName("New User");
        assertEquals(user.getName(), "New User");
    }

    @Test
    public void testCheckPassword() throws Exception {
        user.setPassword("newPass");
        assertTrue(user.checkPassword("newPass"));
        assertFalse(user.checkPassword("password"));
    }
}