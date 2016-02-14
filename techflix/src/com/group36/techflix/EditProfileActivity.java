package com.group36.techflix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by osharifali on 2/9/16.
 */
public class EditProfileActivity extends Activity {
    EditText name;
    EditText movie;
    EditText major;
    EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        name = (EditText)findViewById(R.id.name);
        movie = (EditText)findViewById(R.id.movie);
        major = (EditText)findViewById(R.id.major);
        password = (EditText)findViewById(R.id.password);

        name.setText(User.currentUser.getName());
        movie.setText(User.currentUser.getFavoriteMovie());
        major.setText(User.currentUser.getMajor());
        password.setText(User.currentUser.getPassword());
    }

    public void saveProfile(View v) {
        User.currentUser.setName(name.getText().toString());
        User.currentUser.setMajor(major.getText().toString());
        User.currentUser.setFavoriteMovie(movie.getText().toString());
        User.currentUser.setPassword(password.getText().toString());

        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        Toast t = Toast.makeText(context, "Profile Saved", duration);
        t.show();

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}