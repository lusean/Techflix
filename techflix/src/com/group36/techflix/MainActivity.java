package com.group36.techflix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
