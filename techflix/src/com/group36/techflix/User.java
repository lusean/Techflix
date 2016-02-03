package com.group36.techflix;

/**
 * Created by Hrisheek on 2/1/16.
 */
public class User {
    private String username;
    private String password;
    private String name;
    private String favoriteMovie;

    public User(String username, String password, String name, String favoriteMovie) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.favoriteMovie = favoriteMovie;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getFavoriteMovie() {
        return favoriteMovie;
    }

    public boolean checkPassword(String password) { return this.password.equals(password); }
}
