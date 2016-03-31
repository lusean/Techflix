package com.techflix.group36.techflix.User;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Rating.Rating;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by developer on 3/30/16.
 */
public class SaveObject implements Serializable {
    private Map<String, User> userList;
    private transient HashMap<Integer, Rating> ratingList;
    private transient ArrayList<Movie> ratedMovieList;

    public SaveObject(Map<String, User> userLst, HashMap<Integer, Rating> ratingLst,
                      ArrayList<Movie> ratedMovieLst) {
        this.ratingList = ratingLst;
        this.userList = userLst;
        this.ratedMovieList = ratedMovieLst;
    }

    public Map<String, User> getUserList() {
        return userList;
    }

    public HashMap<Integer, Rating> getRatingList() {
        return ratingList;
    }

    public ArrayList<Movie> getRatedMovieList() {
        return ratedMovieList;
    }
}
