package com.techflix.group36.techflix;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RateMovieActivity extends AppCompatActivity {
    private EditText commentTextView;
    private RatingBar starsBar;
    private Movie selectedMovie;
    private Rating curRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        TextView movieTitleView = (TextView)findViewById(R.id.movieTitleView);
        TextView movieYearView = (TextView)findViewById(R.id.movieYearView);
        TextView movieMpaaRatingView = (TextView)findViewById(R.id.movieMpaaRatingView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedMovie = (Movie)extras.getSerializable("selectedMovie");
            if (selectedMovie.getTitle() != null) {
                String movieTitle = selectedMovie.getTitle();
                movieTitleView.setText(movieTitle);
            }

            if (selectedMovie.getYear() != null) {
                String movieYear = selectedMovie.getYear();
                movieYearView.setText(movieYear);
            }

            if (selectedMovie.getMpaaRating() != null) {
                String mpaaRating = selectedMovie.getMpaaRating();
                movieMpaaRatingView.setText(mpaaRating);
            }
        }

        commentTextView = (EditText)findViewById(R.id.commentTextView);
        starsBar = (RatingBar)findViewById(R.id.starsBar);
    }

    public void showRating(View v) {
        if (curRating != null) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(selectedMovie.getTitle());
            String commentRating = curRating.getStars() + " Stars - " + curRating.getComment();
            alertDialog.setMessage(commentRating);
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    public void saveRating(View v) {
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
            curRating = new Rating(starsBar.getRating(), commentTextView.getText().toString(), User.currentUser, selectedMovie);

            String toastText = "Saved Rating";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, toastText, duration);
            t.show();
            onBackPressed();
        }
    }

}
