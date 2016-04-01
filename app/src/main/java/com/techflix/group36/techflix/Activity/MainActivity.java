package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Movie.MovieAdapter;
import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import static com.android.volley.Response.ErrorListener;
import static com.android.volley.Response.Listener;

/**
 * Created by osharifali on 2/15/16.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class MainActivity extends Activity {
    private EditText majorInput;
    private SearchView searchBar;
    private ListView movieList;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieListResponse;
    private RequestQueue queue;
    private final String API_KEY = "yedukp76ffytfuy24zsqk7f5";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        searchBar = (SearchView) findViewById(R.id.searchBar);
        movieList = (ListView) findViewById(R.id.movieList);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), RateMovieActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("selectedMovie", movieAdapter.getItem(position));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        populateList();
    }

    @Override
    public void onPause() {
        super.onPause();
        File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
        Log.d("Techflix", "Saving binary data");
        boolean success = UserManager.saveBinary(file);
        if (success) {
            Log.d("Techflix", "Successfully Saved binary data");
        } else {
            Log.d("Techflix", "UN-Successful - did not save binary data");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
        Log.d("Techflix", "Saving binary data");
        boolean success = UserManager.saveBinary(file);
        if (success) {
            Log.d("Techflix", "Successfully Saved binary data");
        } else {
            Log.d("Techflix", "UN-Successful - did not save binary data");
        }
    }

    /**
     * Connects movieList array list to adapter so it updates as it changes
    */
    private void populateList() {
        if (movieAdapter != null) {
            movieAdapter.notifyDataSetChanged();
        } else {
            queue = Volley.newRequestQueue(this);
            movieListResponse = new ArrayList<>();
            movieAdapter = new MovieAdapter(this, movieListResponse);
            movieList.setAdapter(movieAdapter);
        }
    }

    /**
     * Method that shows a list of recommendations based
     * on user ratings
     *
     * @param view Allows to set the on click
     */
    public void showRecommendations(View view) {
        movieAdapter.clear();
        movieAdapter.notifyDataSetChanged();
        movieAdapter.addAll(Movie.filterMoviesByRating());
        movieAdapter.notifyDataSetChanged();
    }

    /**
     * Method that allows user to type in major and filters the
     * recommendations by that major
     *
     * @param view Allows to set the on click
     */
    public void searchMajor(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Major");

        majorInput = new EditText(this);
        builder.setView(majorInput);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String major = majorInput.getText().toString();
                movieAdapter.clear();
                movieAdapter.notifyDataSetChanged();
                movieAdapter.addAll(Movie.filterMoviesByMajor(major));
                movieAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builder.create();
        Button chooseMajor = (Button) findViewById(R.id.major);
        chooseMajor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ad.show();
            }
        });
    }

    /**
     * Method that processes search bar information into the API
     *
     * @param view Allows to set the on click
     */
    public void searchForMovie(View view) {

        String searchQuery = searchBar.getQuery().toString();
        searchQuery = searchQuery.replaceAll("\\s", "+");
        String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
                + searchQuery + "&page_limit=10&page=1&apikey=" + API_KEY;
        Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response JSON response
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
             * @param error Error thrown by volley
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url, new JSONObject(),
                listener, errorListener);


        queue.add(jsonRequest);

    }

    /**
     * Method that allows you to view your profile from search screen
     * @param view Allows to set the on click
     */
    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Updates the list view with the data from the raw JSON
     *
     * @param movies The raw JSON object returned from API call
     */
    private void processList(JSONObject movies) throws JSONException {
        movieAdapter.clear();
        movieAdapter.notifyDataSetChanged();
        int numberOfMovies = movies.getInt("total");
        if (numberOfMovies > 10) {
            numberOfMovies = 10;
        }
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
    /**
     * Checks new releases
     *
     * @param view Connects button from view to this method
     */
    public void checkNew(View view) {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?page_limit=16&page=1&country=us&apikey="
                + API_KEY;
        Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response JSON response
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
             * @param error Volley error that gets caught
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url, new JSONObject(),
                listener, errorListener);


        queue.add(jsonRequest);

    }
    /**
     * Checks recent DVDs
     *
     * @param view Connects button from view to this method
     */
    public void checkDVD(View view) {

        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=16&page=1&country=us&apikey="
                + API_KEY;
        Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            /**
             * Called when a response is received.
             *
             * @param response JSON response from API
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
             * @param error Volley error that gets caught
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