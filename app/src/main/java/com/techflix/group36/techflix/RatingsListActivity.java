package com.techflix.group36.techflix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RatingsListActivity extends AppCompatActivity {

    ListView ratingsList;
    TextView movieTitleText;
    RatingAdapter ratingAdapter;
    ArrayList<Rating> ratingsListResponse;
    Movie selectedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_list);
        ratingsList = (ListView)findViewById(R.id.ratingsList);
        movieTitleText = (TextView)findViewById(R.id.movieTitleText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedMovie = (Movie)extras.getSerializable("selectedMovie");
            if (selectedMovie.getTitle() != null) {
                String movieTitle = selectedMovie.getTitle();
                movieTitleText.setText(movieTitle);
            }
            populateList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        populateList();
    }

    private void populateList() {
        if (ratingAdapter != null) {
            ratingAdapter.clear();
            ratingAdapter.addAll(ratingsListResponse);
            ratingAdapter.notifyDataSetChanged();
        } else {
            ratingsListResponse = selectedMovie.getRatings();
            ratingAdapter = new RatingAdapter(this, R.layout.item_rating, ratingsListResponse);
            ratingsList.setAdapter(ratingAdapter);
            ratingAdapter.notifyDataSetChanged();
        }
    }
}
