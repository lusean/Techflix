package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    User selectedUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);
        userList = (ListView) findViewById(R.id.userListView);
        userListSearcher = (EditText) findViewById(R.id.userSearchBar);
        populateList();
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("selectedUser", userListAdapter.getItem(position));
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        userListSearcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == "") {
                    populateList();
                } else {
                    UserListActivity.this.userListAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        populateList();
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

    /**
     * Populates the ListView with Ratings data
     */
    private void populateList() {
        if (userListAdapter != null) {
            userListAdapter.notifyDataSetChanged();
        } else {
            listOfUsers = createSortedList();
            Log.d("USERLISTACTIVITY", "populateList: LIST USERS - " + listOfUsers);
            userListAdapter = new UserListAdapter(this, R.layout.item_rating, listOfUsers);
            userList.setAdapter(userListAdapter);
            userListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Creates and returns a sorted array list of all the users
     * @return Array List with users in sorted order
     */
    private ArrayList<User> createSortedList() {
        ArrayList<User> listOfUsers = new ArrayList<>(UserManager.getUserMap().values());
        java.util.Collections.sort(listOfUsers);
        return listOfUsers;
    }

}
