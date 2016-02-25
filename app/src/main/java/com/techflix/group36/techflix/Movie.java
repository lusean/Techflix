package com.techflix.group36.techflix;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by osharifali on 2/24/16.
 */
public class Movie {
    private String title;
    private String mpaaRating;
    private String year;


    public Movie(String title, String year, String mpaaRating ) {
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
}
