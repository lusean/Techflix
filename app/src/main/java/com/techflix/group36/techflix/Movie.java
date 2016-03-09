package com.techflix.group36.techflix;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by osharifali on 2/24/16.
 *
 * This class represents a movie object with a title, year, and MPAA rating
 */
public class Movie implements Serializable {
    private String title;
    private String mpaaRating;
    private String year;


    public Movie(String title, String year, String mpaaRating) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.year = year;
    }


    public String getTitle() {
        return title;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getYear() { return year; }

    public ArrayList<Rating> getRatings() {
        return Rating.filterRatingsByMovie(this);
    }

    public void rateMovie(float stars, String comment) {
        Rating newRating = new Rating(stars, comment, User.currentUser, this);
        newRating.save();
    }

    public Rating getRatingFromCurrentUser() {
        ArrayList<Rating> results = Rating.filterRatingsByMovie(this);
        Rating userRating = null;
        for (Rating curRating : results) {
            if (curRating.getAuthor().equals(User.currentUser)) {
                userRating = curRating;
            }
        }
        return userRating;
    }

    public boolean hasRatingFromCurrentUser() {
        if (getRatingFromCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }
}
