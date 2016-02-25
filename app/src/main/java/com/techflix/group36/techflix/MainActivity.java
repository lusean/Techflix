package com.techflix.group36.techflix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;




import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Response.*;

/**
 * Created by osharifali on 2/15/16.
 */
public class MainActivity extends Activity {
    Button search;
    SearchView searchBar;
    ListView movieList;
    MovieAdapter movieAdapter;
    ArrayList<Movie> movieListResponse;
    private RequestQueue queue;
    private final String API_KEY = "yedukp76ffytfuy24zsqk7f5";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        search = (Button) findViewById(R.id.search);
        searchBar = (SearchView) findViewById(R.id.searchBar);
        movieList = (ListView) findViewById(R.id.movieList);
        queue = Volley.newRequestQueue(this);
        movieListResponse = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, R.layout.item_movie, movieListResponse);
        movieList.setAdapter(movieAdapter);
    }

    public void searchForMovie(View view) {

        String searchQuery = searchBar.getQuery().toString();
        searchQuery = searchQuery.replaceAll("\\s", "+");
        String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
                + searchQuery + "&page_limit=10&page=1&apikey=" + API_KEY;
        Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONObject response) {
                try {
                    processList(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        ErrorListener errorListener = new ErrorListener() {
            /**
             * Callback method that an error has been occurred with the
             * provided error code and optional user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url, new JSONObject(),
                listener, errorListener);


        queue.add(jsonRequest);

    }
    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void processList(JSONObject movies) throws JSONException {
        movieAdapter.clear();
        int numberOfMovies = movies.getInt("total");
        Log.d("Process", "Number of Movies: " + numberOfMovies);
        for (int i = 0; i < numberOfMovies; i++) {

            Log.d("Process", movies.getJSONArray("movies").getJSONObject(i).toString());
            String currTitle = movies.getJSONArray("movies").getJSONObject(i).getString("title");
            String currYear =  movies.getJSONArray("movies").getJSONObject(i).getString("year");
            String currRating = movies.getJSONArray("movies").getJSONObject(i).getString("mpaa_rating");
            Movie currMovie = new Movie(currTitle, currYear, currRating);
            movieListResponse.add(currMovie);
            movieAdapter.notifyDataSetChanged();

        }

    }

    public void checkNew(View view) {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?page_limit=16&page=1&country=us&apikey="
                + API_KEY;
        Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONObject response) {
                try {
                    processList(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        ErrorListener errorListener = new ErrorListener() {
            /**
             * Callback method that an error has been occurred with the
             * provided error code and optional user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url, new JSONObject(),
                listener, errorListener);


        queue.add(jsonRequest);

    }
    public void checkDVD(View view) {

        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=16&page=1&country=us&apikey="
                + API_KEY;
        Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONObject response) {
                try {
                    processList(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        ErrorListener errorListener = new ErrorListener() {
            /**
             * Callback method that an error has been occurred with the
             * provided error code and optional user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url, new JSONObject(),
                listener, errorListener);


        queue.add(jsonRequest);
    }

}