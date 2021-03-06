package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;

/**
 * Created by osharifali on 2/9/16.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class EditProfileActivity extends Activity {
    private final static String TAG = "Techflix";
    /**
     * Field for the name of the current user.
     */
    private EditText name;
    /**
     * Field for the favorite movie of the current user.
     */
    private EditText movie;
    /**
     * Field for the major of the current user.
     */
    private EditText major;
    /**
     * Field for the password of the current user.
     */
    private EditText password;

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
    @Override
    public void onPause() {
        super.onPause();
        final File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
        Log.d(TAG, "Saving binary data");
        final boolean success = UserManager.saveBinary(file);
        if (success) {
            Log.d(TAG, "Successfully Saved binary data");
        } else {
            Log.d(TAG, "UN-Successful - did not save binary data");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        final File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
        Log.d(TAG, "Saving binary data");
        final boolean success = UserManager.saveBinary(file);
        if (success) {
            Log.d(TAG, "Successfully Saved binary data");
        } else {
            Log.d(TAG, "UN-Successful - did not save binary data");
        }
    }

    /**
     * Saves all profile information to the user HashMap.
     * @param v view this method is being called from
     */
    public void saveProfile(View v) {
        if(password.getText().toString().equals("") ||
                name.getText().toString().equals("") || movie.getText().toString().equals("") ||
                major.getText().toString().equals("")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please fill in all of the fields.");
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
            User.currentUser.setName(name.getText().toString());
            User.currentUser.setMajor(major.getText().toString());
            User.currentUser.setFavoriteMovie(movie.getText().toString());
            User.currentUser.setPassword(password.getText().toString());

            final int duration = Toast.LENGTH_SHORT;
            final Context context = getApplicationContext();
            final Toast t = Toast.makeText(context, "Profile Saved", duration);
            t.show();

            final Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}