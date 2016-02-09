package com.group36.techflix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

/**
 * Created by osharifali on 1/25/16.
 */
public class ProfileActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    public void logout(View v) {
        CharSequence text = "Logging out";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void editProfile(View v) {
        Intent edit = new Intent(this, EditProfileActivity.class);
        startActivity(edit);
    }
}