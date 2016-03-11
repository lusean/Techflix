package com.techflix.group36.techflix;

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

/**
 * Created by osharifali on 1/27/16.
 */
public class LoginActivity extends Activity {
    EditText username;
    EditText password;
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
        Intent main = new Intent(this, WelcomeActivity.class);
        startActivity(main);
    }

    /**
     * Checks the entered credentials against the User hashmap and logins in a user if found
     * and their password is valid.
     * @param view View this method is being called from.
     */
    public void checkCredentials(View view) {
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        UserManager loginHandler = new UserManager();

        CharSequence toastText;
        User user = loginHandler.findUserByUsername(usernameInput);
        if (loginHandler.executeLogin(usernameInput, passwordInput)) {
            Intent intent;
            if (user.getAdminStatus()) {
                toastText = "Admin Login Success";
                intent = new Intent(this, UserListActivity.class);
            } else {
                toastText = "Login Success";
                intent = new Intent(this, MainActivity.class);
            }
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, toastText, duration);
            t.show();
            startActivity(intent);
        } else {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            if (user == null) {
                alertDialog.setMessage("That username does not exist.");
            } else if (user.getBannedStatus()) {
                alertDialog.setMessage("The account you are trying to access is banned.");
            } else if (user.getLockStatus()) {
                alertDialog.setMessage("This account is locked for failing multiple logins.");
            } else {
                alertDialog.setMessage("The password was incorrect.");
                user.incrementLock();
                if (user.getLockStatus()) {
                    alertDialog.setMessage("Account Locked");
                }
            }
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }
}