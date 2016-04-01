package com.techflix.group36.techflix.Movie;

import android.util.Log;

import com.techflix.group36.techflix.Rating.Rating;
import com.techflix.group36.techflix.User.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by osharifali on 2/24/16.
 *
 * This class represents a movie object with a title, year, and MPAA rating
 */
public class Movie implements Serializable {
    private final String title;
    private final String mpaaRating;
    private final String year;
    private static ArrayList<Movie> ratedMovies = new ArrayList<>();

    /**
     * Creates a movie object
     * @param title the title of the movie
     * @param year the year the movie came out
     * @param mpaaRating the mpaa rating of the movie
     */
    public Movie(String title, String year, String mpaaRating) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.year = year;
    }
    /*
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.title);
        out.writeObject(this.mpaaRating);
        out.writeObject(this.year);
        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.title = (String)in.readObject();
        this.mpaaRating = (String)in.readObject();
        this.year = (String)in.readObject();
        in.close();
    }
    */

    public static ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    /** Loads a previously-saved set of movies
     */
    public static void setRatedMovies(ArrayList<Movie> movies) {
        ratedMovies = movies;
    }

    /**
     * Gets the title of the movie
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the mpaa rating of the movie
     * @return the mpaa rating of the movie
     */
    public String getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Gets the release year of the movie
     * @return the release year of the movie
     */
    public String getYear() { return year; }

    /**
     * Gets all the ratings for one movie
     * @return a list of all the ratings for a movie
     */
    public ArrayList<Rating> getRatings() {
        return Rating.filterRatingsByMovie(this);
    }

    /**
     * Rates a movie out of a 5 star score with comment
     *
     * @param stars The rating score given
     * @param comment The comment attached to a rating
     */
    public void rateMovie(float stars, String comment) {
        Rating newRating = new Rating(stars, comment, User.currentUser, this);
        newRating.save();
        ratedMovies.add(this);
        for(int i=0;i<ratedMovies.size();i++){
            for(int j=i+1;j<ratedMovies.size();j++){
                if(ratedMovies.get(i).getTitle().equals(ratedMovies.get(j).getTitle())) {
                    ratedMovies.remove(j);
                    j--;
                }
            }
        }
    }

    /**
     * Gets the average score rating of a given list parameter
     *
     * @param list The list of ratings to get the avg of
     * @return float of the total average score rating
     */
    private static float getRatingAvg(ArrayList<Rating> list) {
        float total = 0;
        float count = 0;
        for (Rating rating: list) {
            total += rating.getStars();
            count++;
        }
        return total / count;
    }

    /**
     * Gets the average score rating from a list for a given major
     *
     * @param list The list of ratings to get the avg of
     * @param major The major to be counted in the avg
     * @return float of the total avg score rating for a major
     */
    private static float getRatingAvgOfMajor(ArrayList<Rating> list, String major) {
        float total = 0;
        float count = 0;
        for (Rating rating: list) {
            if (rating.getAuthor().getMajor().equals(major)) {
                total += rating.getStars();
                count++;
            }
        }
        if (count == 0) {
            return -1;
        } else {
            return total / count;
        }
    }

    /**
     * Puts movies in order of best rating to worst
     *
     * @return ArrayList of Movie in descending rating
     */
    public static ArrayList<Movie> filterMoviesByRating() {
        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : ratedMovies) {
            if (results.size() == 0) {
                results.add(movie);
            } else {
                boolean added = false;
                for (int i = 0; i < results.size(); i++) {
                    if (getRatingAvg(results.get(i).getRatings())
                            <= getRatingAvg(movie.getRatings())) {
                        results.add(i, movie);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    results.add(movie);
                }
            }
        }
        return results;
    }

    /**
     * Puts movies in order of best rating to worst
     * based on ratings of a specific major
     *
     * @param major The major to filter the recommendations by
     * @return ArrayList of Movie in descending rating
     */
    public static ArrayList<Movie> filterMoviesByMajor(String major) {
        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : ratedMovies) {
            if (getRatingAvgOfMajor(movie.getRatings(), major) != -1) {
                if (results.size() == 0) {
                    results.add(movie);
                } else {
                    boolean added = false;
                    for (int i = 0; i < results.size(); i++) {
                        if (getRatingAvgOfMajor(results.get(i).getRatings(), major)
                                <= getRatingAvgOfMajor(movie.getRatings(), major)) {
                            results.add(i, movie);
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        results.add(movie);
                    }
                }
            }
        }
        return results;
    }

    public Rating getRatingFromCurrentUser() {
        ArrayList<Rating> results = Rating.filterRatingsByMovie(this);
        Log.d("MOVIE", "RATING COUNT: " + results.size());
        Rating userRating = null;
        for (Rating curRating : results) {
            if (curRating.getAuthor().equals(User.currentUser)) {
                userRating = curRating;
                break;
            }
        }
        if (userRating != null) {
            Log.d("MOVIE", "" + userRating.getDictionaryRepresentation());
        } else {
            Log.d("MOVIE", "NO USER RATING");
        }
        return userRating;
    }

    public boolean hasRatingFromCurrentUser() {
        return getRatingFromCurrentUser() != null;
    }
}

