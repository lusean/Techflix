package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.UserManager;

/**
 * Created by osharifali on 1/25/16.
 */
public class RegistrationActivity extends Activity {
    /**
     * Field for the name of the new user.
     */
    EditText name;
    /**
     * Field for the username of the new user.
     */
    EditText username;
    /**
     * Field for the password of the new user.
     */
    EditText password;
    /**
     * Field for the favorite movie of the new user.
     */
    EditText faveMovie;
    /**
     * Field for the major of the new user.
     */
    EditText major;


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