package com.techflix.group36.techflix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by akeaswaran on 2/26/16.
 */
public class Rating {
    private static AtomicInteger nextId = new AtomicInteger();
    private static HashMap<Integer, Rating> ratings;

    private int id;
    private int stars;
    private String comment;
    private User author;
    private Movie movie;

    public Rating(int starsIn, String commentIn, User authorIn, Movie movieIn) {
        this.stars = starsIn;
        this.comment = commentIn;
        this.author = authorIn;
        this.movie = movieIn;
        this.id = nextId.incrementAndGet();
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

    public int getStars() {
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
        ratings.put(this.id, this);
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
        for (HashMap.Entry<Integer, Rating> curItem: ratings.entrySet()) {
            if (curItem.getValue().getMovie().equals(movieIn)) {
                results.add(curItem.getValue());
            }
        }
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
