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
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;

/**
 * Created by osharifali on 1/25/16.
 */
@SuppressWarnings({"DefaultFileTemplate", "unused"})
public class RegistrationActivity extends Activity {
    private final static String TAG = "Techflix";
    /**
     * Field for the name of the new user.
     */
    private EditText name;
    /**
     * Field for the username of the new user.
     */
    private EditText username;
    /**
     * Field for the password of the new user.
     */
    private EditText password;
    /**
     * Field for the favorite movie of the new user.
     */
    private EditText faveMovie;
    /**
     * Field for the major of the new user.
     */
    private EditText major;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        faveMovie = (EditText)findViewById(R.id.faveMovie);
        major = (EditText) findViewById(R.id.major);

    }

    @Override
    public void onPause() {
        super.onPause();
        File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
        Log.d(TAG, "Saving binary data");
        boolean success = UserManager.saveBinary(file);
        if (success) {
            Log.d(TAG, "Successfully Saved binary data");
        } else {
            Log.d(TAG, "UN-Successful - did not save binary data");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        File file = new File(this.getFilesDir(), UserManager.DEFAULT_BINARY_FILE_NAME);
        Log.d(TAG, "Saving binary data");
        boolean success = UserManager.saveBinary(file);
        if (success) {
            Log.d(TAG, "Successfully Saved binary data");
        } else {
            Log.d(TAG, "UN-Successful - did not save binary data");
        }
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, WelcomeActivity.class);
        startActivity(main);
    }

    /** Registers a new user and opens the app to the main page.
     * @param view View this method is called from
     */
    public void completeRegistration(View view) {
        UserManager manager = new UserManager();
        CharSequence toastText;
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        if(username.getText().toString().equals("") || password.getText().toString().equals("") ||
                name.getText().toString().equals("") || faveMovie.getText().toString().equals("") ||
                major.getText().toString().equals("")) {
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please fill in all of the fields.");
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
            try {
                manager.addUser(username.getText().toString(), password.getText().toString(),
                        name.getText().toString(), faveMovie.getText().toString(), major.getText().toString());
                toastText = "Registration Successful";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(context, toastText, duration);
                t.show();
                Intent login = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(login);
            } catch (IllegalArgumentException e) {
                alertDialog.setTitle("Error");
                alertDialog.setMessage(e.getMessage());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        }
    }
}