package com.techflix.group36.techflix;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Rating class provides a way for users to rate movies, as well as query existing ratings.
 * Created by akeaswaran on 2/26/16.
 */
public class Rating {
    private static AtomicInteger nextId = new AtomicInteger();
    private static HashMap<Integer, Rating> ratings = new HashMap<Integer, Rating>();

    private int id;
    private float stars;
    private String comment;
    private User author;
    private Movie movie;

    public Rating(float starsIn, String commentIn, User authorIn, Movie movieIn) {
        this.stars = starsIn;
        this.comment = commentIn;
        this.author = authorIn;
        this.movie = movieIn;
        this.id = nextId.incrementAndGet();
    }

    public HashMap<String, Object> getDictionaryRepresentation() {
        HashMap<String, Object> represent = new HashMap<String, Object>();
        represent.put("id", this.id);
        represent.put("movie", this.movie.getTitle());
        represent.put("author", this.author.getName());
        represent.put("comment", this.comment);
        represent.put("stars", this.stars);
        return represent;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public float getStars() {
        return stars;
    }

    public User getAuthor() {
        return author;
    }

    public static Rating getRatingWithId(int idIn) {
        return ratings.get(idIn);
    }

    public void delete()  {
        ratings.remove(this.id);
    }

    public void save() {
        Log.d("RATING", "save: id - " + this.id + " rating - " + this.getDictionaryRepresentation());
        ratings.put(this.id, this);
        Log.d("RATING", "post-save: " + ratings);
    }

    public static ArrayList<Rating> filterRatingsByMajor(String major) {
        ArrayList<Rating> results = new ArrayList<Rating>();
        for (HashMap.Entry<Integer, Rating> curItem: ratings.entrySet()) {
            if (curItem.getValue().getAuthor().getMajor().equals(major)) {
                results.add(curItem.getValue());
            }
        }
        return results;
    }

    public static ArrayList<Rating> filterRatingsByMovie(Movie movieIn) {
        ArrayList<Rating> results = new ArrayList<Rating>();
        for (HashMap.Entry<Integer, Rating> curItem : ratings.entrySet()) {
            Rating curRating = curItem.getValue();
            Log.d("RATING", "pre-filter: " + curRating.getDictionaryRepresentation());
            if (curRating.getMovie().getTitle().equals(movieIn.getTitle())) {
                results.add(curRating);
            }
        }
        Log.d("RATING", "filterRatingsByMovie: post-filter -  " + results);
        return results;
    }

    public static ArrayList<Rating> filterRatingsByUser(User userIn) {
        ArrayList<Rating> results = new ArrayList<Rating>();
        for (HashMap.Entry<Integer, Rating> curItem: ratings.entrySet()) {
            if (curItem.getValue().getAuthor().equals(userIn)) {
                results.add(curItem.getValue());
            }
        }
        return results;
    }
}
