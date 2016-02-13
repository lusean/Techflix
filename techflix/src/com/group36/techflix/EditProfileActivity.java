package com.group36.techflix;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by osharifali on 2/9/16.
 */
public class EditProfileActivity extends Activity {
    EditText name;
    EditText movie;
    EditText major;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        name = (EditText)findViewById(R.id.name);
        movie = (EditText)findViewById(R.id.movie);
        major = (EditText)findViewById(R.id.major);

        name.setText(User.currentUser.getName());
        movie.setText(User.currentUser.getFavoriteMovie());
        major.setText(User.currentUser.getMajor());
    }
}