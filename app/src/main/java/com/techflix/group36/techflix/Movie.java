package com.techflix.group36.techflix;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by osharifali on 2/24/16.
 *
 * This class represents a movie object with a title, year, and MPAA rating
 */
public class Movie implements Serializable {
    private String title;
    private String mpaaRating;
    private String year;
    private static ArrayList<Movie> ratedMovies = new ArrayList<Movie>();


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
        if (!ratedMovies.contains(this)) {
            ratedMovies.add(this);
        }
    }

    public static float getRatingAvg(ArrayList<Rating> list) {
        float total = 0;
        float count = 0;
        for (Rating rating: list) {
            total += rating.getStars();
            count++;
        }
        return total / count;
    }

    //Puts movies in order of best rating
    public static ArrayList<Movie> filterMoviesByRating() {
        ArrayList<Movie> results = new ArrayList<Movie>();

        for (Movie movie : ratedMovies) {
            if (results.size() == 0) {
                results.add(movie);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    if (getRatingAvg(results.get(i).getRatings()) >= getRatingAvg(movie.getRatings())) {

                    }
                }
            }
        }

        HashMap<Float, Movie> avgRatings = new HashMap<Float, Movie>();
        for (Movie movie: ratedMovies) {
            float rating = getRatingAvg(movie.getRatings());
            avgRatings.put(rating, movie);
        }
        /*
        while (!avgRatings.isEmpty()) {
            float max = 0;
            for (float rating: avgRatings.keySet()) {
                if (rating > max) {
                    max = rating;
                }
                results.add(avgRatings.get(max));
                avgRatings.remove(max);
            }
        }
        */
        return results;
    }

    //top 5 movies, must have 3+ recs
    public static ArrayList<Movie> filterTopMoviesByRating() {
        ArrayList<Movie> results = filterMoviesByRating();
        int count = 0;
        while (count < 5) {
            if (results.get(count).getRatings().size() >= 3) {
                count++;
            } else {
                results.remove(count);
            }
        }
        return results;
    }

    //top 5 movies w/ major, must have 3+ recs
    public static ArrayList<Movie> filterMoviesByMajor(String major) {
        ArrayList<Movie> results = new ArrayList<Movie>();
        HashMap<Float, Movie> avgRatings = new HashMap<Float, Movie>();
        for (Movie movie: ratedMovies) {
            int majorCount = 0;
            ArrayList<Rating> list = movie.getRatings();
            for (Rating rating: list) {
                if (rating.getAuthor().getMajor().equals(major)) {
                    majorCount++;
                } else {
                    list.remove(rating);
                }
            }
            if (majorCount >= 3) {
                float rating = getRatingAvg(list);
                avgRatings.put(rating, movie);
            }
        }
        while (!avgRatings.isEmpty()) {
            float max = 0;
            for (float rating: avgRatings.keySet()) {
                if (rating > max) {
                    max = rating;
                }
                results.add(avgRatings.get(max));
                avgRatings.remove(max);
            }
        }
        return results;
    }
}

