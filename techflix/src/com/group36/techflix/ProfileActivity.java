package com.group36.techflix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

/**
 * Created by osharifali on 1/25/16.
 */
public class ProfileActivity extends Activity {
    TextView name;
    TextView faveMovie;
    TextView major;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        name = (TextView)findViewById(R.id.name);
        faveMovie = (TextView)findViewById(R.id.faveMovie);
        major = (TextView) findViewById(R.id.major);
        populateProfile();
    }

    public void logout(View v) {
        CharSequence text = "Logging out";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void editProfile(View v) {
        Intent edit = new Intent(this, EditProfileActivity.class);
        startActivity(edit);
    }

    public void populateProfile() {
        name.setText(User.currentUser.getName());
        faveMovie.setText(User.currentUser.getFavoriteMovie());
        major.setText(User.currentUser.getMajor());
    }
}