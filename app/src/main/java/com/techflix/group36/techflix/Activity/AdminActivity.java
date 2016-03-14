package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;

/**
 * Created by Scott on 3/10/2016.
 */
public class AdminActivity extends Activity {
    private ToggleButton bannedToggle;
    private ToggleButton lockedToggle;
    private ToggleButton adminToggle;
    private CheckBox bannedBox;
    private CheckBox lockedBox;
    private CheckBox adminBox;
    private Button save;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpowers);
        TextView username = (TextView) findViewById(R.id.userID);
        //bannedToggle = (ToggleButton) findViewById(R.id.bannedToggle);
        //lockedToggle = (ToggleButton) findViewById(R.id.lockedToggle);
        //adminToggle = (ToggleButton) findViewById(R.id.adminToggle);
        bannedBox = (CheckBox) findViewById(R.id.bannedBox);
        lockedBox = (CheckBox) findViewById(R.id.lockedBox);
        adminBox = (CheckBox) findViewById(R.id.adminBox);
        save = (Button) findViewById(R.id.saveButton);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedUser = (User) extras.getSerializable("selectedUser");
            username.setText(selectedUser.getUsername());
            //bannedToggle.setChecked(selectedUser.getBannedStatus());
            //lockedToggle.setChecked(selectedUser.getLockStatus());
            //adminToggle.setChecked(selectedUser.getAdminStatus());
            bannedBox.setChecked(selectedUser.getBannedStatus());
            lockedBox.setChecked(selectedUser.getLockStatus());
            adminBox.setChecked(selectedUser.getAdminStatus());
        }
    }

    /**
     * Alters the User status based on the toggle button's checked status
     */
    public void updateAdmin(View view) {
        //selectedUser.setAdminStatus(adminToggle.isChecked());
        selectedUser.setAdminStatus(adminBox.isChecked());
    }

    public void updateBan(View view) {
        //selectedUser.setBanStatus(bannedToggle.isChecked());
        selectedUser.setBanStatus(bannedBox.isChecked());
        Log.d("BAN", "" + selectedUser.getBannedStatus());
    }

    public void updateLock(View view) {
        //selectedUser.setLockStatus(lockedToggle.isChecked());
        selectedUser.setLockStatus(lockedBox.isChecked());
    }


    public void saveChanges(View view) {
        onBackPressed();
    }
}
