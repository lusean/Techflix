package com.techflix.group36.techflix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by osharifali on 2/15/16.
 */
public class MainActivity extends Activity {
    Button search;
    SearchView searchBar;
    ListView movieList;
    private RequestQueue queue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        search = (Button) findViewById(R.id.search);
        searchBar = (SearchView) findViewById(R.id.searchBar);
        movieList = (ListView) findViewById(R.id.movieList);
        //movieList.setAdapter();
        queue = Volley.newRequestQueue(this);
    }

    public void searchForMovie(View view) {
        String searchQuery = searchBar.getQuery().toString();
        String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
                +searchQuery+"&page_limit=10&page=1&apikey=6ct7v5gbrq4mysgczcxvr8qw";

    }
}