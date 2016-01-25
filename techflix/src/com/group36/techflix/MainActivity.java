package com.group36.techflix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    public void allowRegistration(Button button) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
