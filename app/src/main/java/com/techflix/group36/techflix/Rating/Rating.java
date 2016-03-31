package com.techflix.group36.techflix.Rating;

import android.util.Log;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.User.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Rating class provides a way for users to rate movies, as well as query existing ratings.
 * Created by akeaswaran on 2/26/16.
 */
public class Rating implements Serializable {
    /**
     * An integer that represents the next unique id to be assigned
     */
    private static AtomicInteger nextId = new AtomicInteger();

    /**
     * A HashMap containing all ratings that have been created in the application. Each rating is assigned a unique id as a key to find it in the HashMap.
     */
    private static HashMap<Integer, Rating> ratings = new HashMap<Integer, Rating>();

    /**
     * A unique integer assigned to each Rating using nextId.
     */
    private int id;

    /**
     * A float value representing the number of stars a user has rated the movie this rating is associated with.
     */
    private float stars;

    /**
     * The text of a user's opinion on the movie.
     */
    private String comment;

    /**
     * The user that created this rating
     */
    private User author;

    /**
     * The movie this rating is associated with
     */
    private Movie movie;

    /** Creates a new Rating object
     * @param starsIn the number of stars the user has given the movie
     * @param commentIn the user's opinion on the movie
     * @param authorIn the user who has created the rating
     * @param movieIn the movie being rated
     */
    public Rating(float starsIn, String commentIn, User authorIn, Movie movieIn) {
        this.stars = starsIn;
        this.comment = commentIn;
        this.author = authorIn;
        this.movie = movieIn;
        this.id = nextId.incrementAndGet();
    }

    public static HashMap<Integer, Rating> getRatings() {
        return ratings;
    }

    /** Loads a previously-saved set of ratings
     */
    public static void setRatings(HashMap<Integer, Rating> ratingsIn) {
        ratings = ratingsIn;
    }

    /** Creates a cleaner representation of the object for testing
     * @return a hash-map/dictionary representation of the Rating object
     */
    public HashMap<String, Object> getDictionaryRepresentation() {
        HashMap<String, Object> represent = new HashMap<String, Object>();
        represent.put("id", this.id);
        represent.put("movie", this.movie.getTitle());
        represent.put("author", this.author.getName());
        represent.put("comment", this.comment);
        represent.put("stars", this.stars);
        return represent;
    }

    /** Sets the number of stars of this rating
     * @param stars the rating in stars
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /** Sets the author of this rating
     * @param author the User object of the author of this rating
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /** Sets the movie of this rating
     * @param movie the Movie obejct this rating should be associated with
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /** Sets the unique id of this rating
     * @param id the id this rating should be associated with
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Sets the comment of this rating
     * @param comment a string representing an user opinion on the movie
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /** Gets the comment of this rating
     * @return the String of the comment of this rating
     */
    public String getComment() {
        return comment;
    }

    /** Gets the id of this rating
     * @return the id of this rating
     */
    public int getId() {
        return id;
    }

    /** Gets the movie of this rating
     * @return the movie associated this rating
     */
    public Movie getMovie() {
        return movie;
    }

    /** Gets the stars of this rating
     * @return the stars of this rating
     */
    public float getStars() {
        return stars;
    }

    /** Gets the author of this rating
     * @return the User object of the author of this rating
     */
    public User getAuthor() {
        return author;
    }

    /** Gets a Rating object based on its id
     * @return a Rating object or null depending on if a rating is found for the provided key/id
     */
    public static Rating getRatingWithId(int idIn) {
        return ratings.get(idIn);
    }

    /** Removes the rating from the hashmap
     */
    public void delete()  {
        ratings.remove(this.id);
    }

    /** Saves the rating to the hashmap
     */
    public void save() {
        Log.d("RATING", "save: id - " + this.id + " rating - " + this.getDictionaryRepresentation());
        ratings.put(this.id, this);
        Log.d("RATING", "post-save: " + ratings);
    }

    /** Filters all Ratings and finds the ones which have an author with the given major
     * @param major the major to filter the ratings by
     */
    public static ArrayList<Rating> filterRatingsByMajor(String major) {
        ArrayList<Rating> results = new ArrayList<Rating>();
        for (HashMap.Entry<Integer, Rating> curItem: ratings.entrySet()) {
            if (curItem.getValue().getAuthor().getMajor().equals(major)) {
                results.add(curItem.getValue());
            }
        }
        return results;
    }

    /** Filters all Ratings and finds the ones which are associated with the given movie
     * @param movieIn the movie to filter the ratings by
     */
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

    /** Filters all Ratings and finds the ones which are associated with the given user
     * @param userIn the user to filter the ratings by
     */
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
