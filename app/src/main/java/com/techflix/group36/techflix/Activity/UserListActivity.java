package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserListAdapter;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Scott on 2/15/16.
 */
public class UserListActivity extends Activity {
    UserListAdapter userListAdapter;
    ListView userList;
    EditText userListSearcher;
    ArrayList<User> listOfUsers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);
        userList = (ListView) findViewById(R.id.userListView);
        userListSearcher = (EditText) findViewById(R.id.userSearchBar);
        userListAdapter = new UserListAdapter(this, R.layout.user_info, new ArrayList<User>(UserManager.getUserMap().values()));
        userList.setAdapter(userListAdapter);

        /*userListSearcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == "") {
                    userListAdapter = new UserListAdapter(UserListActivity.this, R.layout.user_info, new ArrayList<User>(UserManager.getUserMap().values()));
                } else {
                    UserListActivity.this.userListAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/

    }


    @Override
    public void onResume() {
        super.onResume();
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

    /** Logs out the current admin.
     * @param v View that this method is being called from
     */
    public void adminLogout(View v) {
        CharSequence text = "Logging out";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        Intent main = new Intent(this, WelcomeActivity.class);
        startActivity(main);
    }

}
