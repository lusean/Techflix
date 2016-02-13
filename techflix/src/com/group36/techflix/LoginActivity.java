package com.group36.techflix;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.DialogInterface;
import android.widget.Toast;
import android.content.Intent;

/**
 * Created by osharifali on 1/27/16.
 */
public class LoginActivity extends Activity {
    EditText username;
    EditText password;
    final String CORRECT_USERNAME = "admin";
    final String CORRECT_PASSWORD = "password";
    boolean correctInput;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.appLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == loginButton) {
                    checkCredentials(loginButton);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    //HRISHEEK FILL IN THIS CODE
    public void checkCredentials(View view) {
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        Authentication loginHandler = new UserManager();

        //hard-coded user
        UserManagement userManager = new UserManager();
        userManager.addUser("user", "pass", "Hrisheek", "Fight Club", "Computer Science");

        CharSequence toastText;
        if (loginHandler.executeLogin(usernameInput, passwordInput)) {
            toastText = "Login Success";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, toastText, duration);
            t.show();
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("The Username or Password was incorrect.");
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();

        }
    }
}