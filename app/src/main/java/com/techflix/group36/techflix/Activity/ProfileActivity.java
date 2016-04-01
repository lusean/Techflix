package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;

/**
 * Created by osharifali on 1/25/16.
 */
@SuppressWarnings({"DefaultFileTemplate", "UnusedParameters", "unused"})
public class ProfileActivity extends Activity {
    /**
     * Field for the name of the current user.
     */
    private TextView name;
    /**
     * Field for the favorite Movie of the current user.
     */
    private TextView faveMovie;
    /**
     * Field for the major of the current user.
     */
    private TextView major;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        name = (TextView)findViewById(R.id.name);
        faveMovie = (TextView)findViewById(R.id.faveMovie);
        major = (TextView) findViewById(R.id.major);
        populateProfile();
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

    /** Logs out the current user.
     * @param view View that this method is being called from
     */
    public void logout(View view) {
        CharSequence text = "Logging out";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        Intent main = new Intent(this, WelcomeActivity.class);
        startActivity(main);
    }

    /** Shows the edit profile screen for the current user
     * @param view View that this method is being called from
     */
    public void editProfile(View view) {
        Intent edit = new Intent(this, EditProfileActivity.class);
        startActivity(edit);
    }

    /** Fills out the current user's profile information
     */
    private void populateProfile() {
        name.setText(User.currentUser.getName());
        faveMovie.setText(User.currentUser.getFavoriteMovie());
        major.setText(User.currentUser.getMajor());
    }
}