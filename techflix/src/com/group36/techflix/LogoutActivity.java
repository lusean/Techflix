package com.group36.techflix;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Logging Out");
        alertDialog.setMessage("Returning to login screen");
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }


}
