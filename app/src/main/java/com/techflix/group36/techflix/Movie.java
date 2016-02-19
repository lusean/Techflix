package com.techflix.group36.techflix;

/**
 * Created by developer on 2/19/16.
 */
public class Movie {
    private String title;
    private String mpaaRating;
    private String releaseDate;

    public Movie(String title, String mpaaRating, String releaseDate) {
        this.title = title;
        this.mpaaRating = mpaaRating;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
