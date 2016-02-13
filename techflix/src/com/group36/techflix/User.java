package com.group36.techflix;

/**
 * Created by Hrisheek on 2/1/16.
 */
public class User {
    private String username;
    private String password;
    private String name;
    private String favoriteMovie;
    private String major;
    public static User currentUser;

    public User(String username, String password, String name, String favoriteMovie, String major) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.favoriteMovie = favoriteMovie;
        this.major = major;
        currentUser = this;
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

    public String getMajor() { return major; }

    public boolean checkPassword(String password) { return this.password.equals(password); }
}
