package com.techflix.group36.techflix.Rating;

import android.util.Log;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.User.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private static final AtomicInteger nextId = new AtomicInteger();

    /**
     * A HashMap containing all ratings that have been created in the application. Each rating is assigned a unique id as a key to find it in the HashMap.
     */
    private static HashMap<Integer, Rating> ratings = new HashMap<>();

    /**
     * A unique integer assigned to each Rating using nextId.
     */
    private final int id;

    /**
     * A float value representing the number of stars a user has rated the movie this rating is associated with.
     */
    private final float stars;

    /**
     * The text of a user's opinion on the movie.
     */
    private final String comment;

    /**
     * The user that created this rating
     */
    private final User author;

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
        HashMap<String, Object> represent = new HashMap<>();
        represent.put("id", this.id);
        represent.put("movie", this.movie.getTitle());
        represent.put("author", this.author.getName());
        represent.put("comment", this.comment);
        represent.put("stars", this.stars);
        return represent;
    }

    /** Writes object to file
     * @param out stream to write to
     * @throws IOException if file can not be written to/found
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.movie);
    }

    /** Reads object from file
     * @param in stream to read from
     * @throws IOException if file can not be read/found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.movie = (Movie)in.readObject();
    }


// --Commented out by Inspection START (4/1/16, 4:02 PM):
//    /** Sets the movie of this rating
//     * @param movie the Movie object this rating should be associated with
//     */
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }
// --Commented out by Inspection STOP (4/1/16, 4:02 PM)

// --Commented out by Inspection START (4/1/16, 4:02 PM):
//    /** Sets the unique id of this rating
//     * @param id the id this rating should be associated with
//     */
//    public void setId(int id) {
//        this.id = id;
//    }
// --Commented out by Inspection STOP (4/1/16, 4:02 PM)

// --Commented out by Inspection START (4/1/16, 4:02 PM):
//    /** Sets the comment of this rating
//     * @param comment a string representing an user opinion on the movie
//     */
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
// --Commented out by Inspection STOP (4/1/16, 4:02 PM)

    /** Gets the comment of this rating
     * @return the String of the comment of this rating
     */
    public String getComment() {
        return comment;
    }

// --Commented out by Inspection START (4/1/16, 4:02 PM):
//    /** Gets the id of this rating
//     * @return the id of this rating
//     */
//    public int getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (4/1/16, 4:02 PM)

    /** Gets the movie of this rating
     * @return the movie associated this rating
     */
    private Movie getMovie() {
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


    /** Saves the rating to the hashmap
     */
    public void save() {
        Log.d("RATING", "save: id - " + this.id + " rating - " + this.getDictionaryRepresentation());
        ratings.put(this.id, this);
        Log.d("RATING", "post-save: " + ratings);
    }

// --Commented out by Inspection START (4/1/16, 4:02 PM):
//    /** Filters all Ratings and finds the ones which have an author with the given major
//     * @param major the major to filter the ratings by
//     */
//    public static ArrayList<Rating> filterRatingsByMajor(String major) {
//        ArrayList<Rating> results = new ArrayList<>();
//        for (HashMap.Entry<Integer, Rating> curItem: ratings.entrySet()) {
//            if (curItem.getValue().getAuthor().getMajor().equals(major)) {
//                results.add(curItem.getValue());
//            }
//        }
//        return results;
//    }
// --Commented out by Inspection STOP (4/1/16, 4:02 PM)

    /** Filters all Ratings and finds the ones which are associated with the given movie
     * @param movieIn the movie to filter the ratings by
     */
    public static ArrayList<Rating> filterRatingsByMovie(Movie movieIn) {
        ArrayList<Rating> results = new ArrayList<>();
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

}
