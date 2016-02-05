package com.group36.techflix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Scott Messing.
 */
public class LogoutActivity extends Activity {

    private Button logoutButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logoutButton = (Button)findViewById(R.id.appLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == logoutButton) {
                    logout();
                }
            }
        });
    }

    public void logout() {
        CharSequence text = "Logging out";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }


}
