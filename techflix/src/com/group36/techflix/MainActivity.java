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

    public void appLogin(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        Log.d("LOGIN", "Intent launched.");
        startActivity(login);
    }


    public void allowRegistration(View view) {
        Intent register = new Intent(this, RegistrationActivity.class);
        Log.d("REGISTER", "Intent launched.");
        startActivity(register);
    }

    public void appLogout(View view) {
        Intent logout = new Intent(this, LogoutActivity.class);
        Log.d("Logout", "Intent launched.");
        startActivity(logout);
    }

}
