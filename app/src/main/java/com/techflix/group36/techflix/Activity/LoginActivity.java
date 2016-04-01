package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.DialogInterface;
import android.widget.Toast;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;

/**
 * Created by osharifali on 1/27/16.
 */
@SuppressWarnings({"DefaultFileTemplate"})
public class LoginActivity extends Activity {
    private EditText username;
    private EditText password;
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

    /**
     * Checks the entered credentials against the User HashMap and logs in a user if found
     * and their password is valid.
     * @param view View this method is being called from.
     */
    @SuppressWarnings({"WeakerAccess", "UnusedParameters"})
    //This method must be public so login.xml can use it.
    public void checkCredentials( View view) {
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
                alertDialog.setMessage("This account is locked for failing multiple login attempts.");
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