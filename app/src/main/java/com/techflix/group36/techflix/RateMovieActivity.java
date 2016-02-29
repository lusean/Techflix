package com.techflix.group36.techflix;

import android.app.AlertDialog;
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

public class RateMovieActivity extends AppCompatActivity {
    private EditText commentTextView;
    private RatingBar starsBar;
    private Movie selectedMovie;
    private TextView movieTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        movieTitleView = (TextView)findViewById(R.id.movieTitleView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedMovie = (Movie)extras.getSerializable("selectedMovie");
            if (selectedMovie.getTitle() != null) {
                String movieTitle = selectedMovie.getTitle() + " (" + selectedMovie.getYear() + ") ";
                movieTitleView.setText(movieTitle);
            }
        }

        commentTextView = (EditText)findViewById(R.id.commentTextView);
        starsBar = (RatingBar)findViewById(R.id.starsBar);
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
            selectedMovie.rateMovie(starsBar.getNumStars(), commentTextView.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
