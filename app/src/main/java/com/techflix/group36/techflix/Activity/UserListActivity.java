package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserListAdapter;
import com.techflix.group36.techflix.User.UserManager;

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
        userListSearcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                UserListActivity.this.userListAdapter.getFilter().filter(s);
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

    /**
     * Connects userList array list to adapter so it updates as it changes
     */
    private void populateList() {
        if (userListAdapter != null) {
            userListAdapter.clear();
            userListAdapter.addAll(createSortedList());
            userListAdapter.notifyDataSetChanged();
        } else {
            listOfUsers = createSortedList();
            userListAdapter = new UserListAdapter(this, R.layout.user_info, listOfUsers);
            userList.setAdapter(userListAdapter);
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
