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
    EditText adminCode;
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
        adminCode = (EditText)findViewById(R.id.admincode);
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
    //HRISHEEK FILL IN THIS CODE
    public void checkCredentials(View view) {
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        String adminInput = adminCode.getText().toString();
        Authentication loginHandler = new UserManager();

        CharSequence toastText;
        User user = ((UserManager)loginHandler).findUserByUsername(usernameInput);
        if (loginHandler.executeLogin(usernameInput, passwordInput, user)) {
            Intent intent;
            if (((UserManager)loginHandler).getAdminCode().equals(adminInput)) {
                toastText = "Admin Login Success";
                user.setAdminStatus(true);
                intent = new Intent(this, UserListActivity.class);
            } else {
                toastText = "Login Success";
                user.setAdminStatus(false);
                intent = new Intent(this, ProfileActivity.class);
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