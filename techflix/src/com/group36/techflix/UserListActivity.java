package com.group36.techflix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Scott on 2/17/16.
 */
public class UserListActivity extends Activity {
    ListView userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);
        userList = (ListView) findViewById(R.id.userList);
        //userList.setAdapter();
    }

}