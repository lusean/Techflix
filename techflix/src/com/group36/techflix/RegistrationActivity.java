package com.group36.techflix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by osharifali on 1/25/16.
 */
public class RegistrationActivity extends Activity {
    EditText name;
    EditText username;
    EditText password;
    EditText faveMovie;
    private Button registerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        faveMovie = (EditText)findViewById(R.id.faveMovie);
        registerButton = (Button)findViewById(R.id.appRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == registerButton) {
                    UserManagement user = new UserManager();
                    user.addUser(username.getText().toString(), password.getText().toString(),
                            name.getText().toString(), faveMovie.getText().toString());
                    CharSequence toastText;
                    toastText = "Registration Successful";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast t = Toast.makeText(context, toastText, duration);
                    t.show();
                    Intent login = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(login);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}