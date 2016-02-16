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
    /**
     * Field for the name of the current user.
     */
    TextView name;
    /**
     * Field for the favorite Movie of the current user.
     */
    TextView faveMovie;
    /**
     * Field for the major of the current user.
     */
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

    /** Logs out the current user.
     * @param v View that this method is being called from
     */
    public void logout(View v) {
        CharSequence text = "Logging out";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        Intent main = new Intent(this, WelcomeActivity.class);
        startActivity(main);
    }

    /** Shows the edit profile screen for the current user
     * @param v View that this method is being called from
     */
    public void editProfile(View v) {
        Intent edit = new Intent(this, EditProfileActivity.class);
        startActivity(edit);
    }

    /** Fills out the current user's profile information
     */
    public void populateProfile() {
        name.setText(User.currentUser.getName());
        faveMovie.setText(User.currentUser.getFavoriteMovie());
        major.setText(User.currentUser.getMajor());
    }
}