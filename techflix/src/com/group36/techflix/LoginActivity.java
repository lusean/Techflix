package com.group36.techflix;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by osharifali on 1/27/16.
 */
public class LoginActivity extends Activity {
    EditText username;
    EditText password;
    final String CORRECT_USERNAME = "admin";
    final String CORRECT_PASSWORD = "password";
    boolean correctInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }

    //HRISHEEK FILL IN THIS CODE
    public void checkCredentials(View view) {
        // get info from username text field and check
        String usernameInput = username.getText().toString();
        // get info from password text field and check
        String passwordInput = password.getText().toString();
        // put a dialog also if the password is incorrect
        if (usernameInput.equals(CORRECT_USERNAME) && passwordInput.equals(CORRECT_PASSWORD)) {

        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Incorrect Username or Password.");
        }
    }
}