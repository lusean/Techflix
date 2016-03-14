package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.techflix.group36.techflix.R;

public class WelcomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

    }

    /**
     * Moves the app to the Login screen.
     * @param view view this method was called from.
     */
    public void appLogin(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        Log.d("LOGIN", "Intent launched.");
        startActivity(login);
    }

    /**
     * Moves the app to the Registration screen.
     * @param view view this method was called from.
     */
    public void allowRegistration(View view) {
        Intent register = new Intent(this, RegistrationActivity.class);
        Log.d("REGISTER", "Intent launched.");
        startActivity(register);
    }

}
