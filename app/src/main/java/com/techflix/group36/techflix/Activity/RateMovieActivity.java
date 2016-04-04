package com.techflix.group36.techflix.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.Rating.Rating;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;

@SuppressWarnings("unused")
public class RateMovieActivity extends AppCompatActivity {
    private EditText commentTextView;
    private RatingBar starsBar;
    private Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        TextView movieTitleView = (TextView)findViewById(R.id.movieTitleView);
        TextView movieYearView = (TextView)findViewById(R.id.movieYearView);
        TextView movieMpaaRatingView = (TextView)findViewById(R.id.movieMpaaRatingView);

        commentTextView = (EditText)findViewById(R.id.commentTextView);
        starsBar = (RatingBar)findViewById(R.id.starsBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedMovie = (Movie)extras.getSerializable("selectedMovie");
            if (selectedMovie != null) {
                if (selectedMovie.getTitle() != null) {
                    String movieTitle = selectedMovie.getTitle();
                    movieTitleView.setText(movieTitle);
                }
            }

            if (selectedMovie != null) {
                if (selectedMovie.getYear() != null) {
                    String movieYear = selectedMovie.getYear();
                    movieYearView.setText(movieYear);
                }
            }

            if (selectedMovie != null) {
                if (selectedMovie.getMpaaRating() != null) {
                    String mpaaRating = selectedMovie.getMpaaRating();
                    movieMpaaRatingView.setText(mpaaRating);
                }
            }

            @SuppressWarnings("ConstantConditions") Rating userRating = selectedMovie.getRatingFromCurrentUser();
            if (userRating != null) {
                Log.d("RATING", "onCreate: HAS RATING FROM CURRENT USER");
                starsBar.setRating(userRating.getStars());
                commentTextView.setText(userRating.getComment());
                commentTextView.setInputType(InputType.TYPE_NULL);
            } else {
                Log.d("RATING", "onCreate: DOES NOT HAVE RATING FROM CURRENT USER");
            }
        }
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

    /** Shows all ratings for the selected movie
     * @param v view this method is being called from
     */
    public void showAllRatings(View v) {
        Intent intent = new Intent(this, RatingsListActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("selectedMovie", selectedMovie);
        intent.putExtras(b);
        startActivity(intent);
    }

    /** Saves a rating for the selected movie
     * @param v view this method is being called from
     */
    public void saveRating(View v) {
       if (!selectedMovie.hasRatingFromCurrentUser()) {
           if (commentTextView.getText() == null || commentTextView.getText().length() == 0) {
               final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
               alertDialog.setTitle("Error");
               alertDialog.setMessage("You need to enter a comment for this rating.");
               alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       alertDialog.dismiss();
                   }
               });
               alertDialog.show();
           } else {
               selectedMovie.rateMovie(starsBar.getRating(), commentTextView.getText().toString());
               String toastText = "Saved Rating";
               Context context = getApplicationContext();
               int duration = Toast.LENGTH_SHORT;
               Toast t = Toast.makeText(context, toastText, duration);
               t.show();
               onBackPressed();
               File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
               Log.d("RateMovieActivity", "Saving binary data");
               boolean success = UserManager.saveBinary(file);
               if (success) {
                   Log.d("RateMovieActivity", "Successfully Saved binary data");
               } else {
                   Log.d("RateMovieActivity", "UN-Successful - did not save binary data");
               }
           }
       } else {
           final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
           alertDialog.setTitle("Error");
           alertDialog.setMessage("You have already rated this movie.");
           alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   alertDialog.dismiss();
               }
           });
           alertDialog.show();
       }
    }

}
