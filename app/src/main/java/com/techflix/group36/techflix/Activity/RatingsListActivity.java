package com.techflix.group36.techflix.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.Rating.RatingAdapter;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;

public class RatingsListActivity extends AppCompatActivity {

    private ListView ratingsList;
    private RatingAdapter ratingAdapter;
    private Movie selectedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_list);
        ratingsList = (ListView)findViewById(R.id.ratingsList);
        TextView movieTitleText = (TextView) findViewById(R.id.movieTitleText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedMovie = (Movie)extras.getSerializable("selectedMovie");
            if (selectedMovie != null) {
                if (selectedMovie.getTitle() != null) {
                    String movieTitle = selectedMovie.getTitle();
                    movieTitleText.setText(movieTitle);
                }
            }
            populateList();
        }
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
     * Populates the ListView with Ratings data
     */
    private void populateList() {
        if (ratingAdapter != null) {
            ratingAdapter.clear();
            ratingAdapter.addAll(selectedMovie.getRatings());
            ratingAdapter.notifyDataSetChanged();
        } else {
            Log.d("RATINGS_LIST_ACTIVITY", "populateList: GET_RATINGS - " + selectedMovie.getRatings());
            ratingAdapter = new RatingAdapter(this, R.layout.item_rating, selectedMovie.getRatings());
            ratingAdapter.addAll(selectedMovie.getRatings());
            ratingsList.setAdapter(ratingAdapter);
            ratingAdapter.notifyDataSetChanged();
        }
    }
}
