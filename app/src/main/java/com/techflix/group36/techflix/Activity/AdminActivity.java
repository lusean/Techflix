package com.techflix.group36.techflix.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.techflix.group36.techflix.R;
import com.techflix.group36.techflix.User.User;
import com.techflix.group36.techflix.User.UserManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scott on 3/10/2016.
 */
public class AdminActivity extends Activity {
    private CheckBox bannedBox;
    private CheckBox lockedBox;
    private CheckBox adminBox;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpowers);
        TextView username = (TextView) findViewById(R.id.userID);
        bannedBox = (CheckBox) findViewById(R.id.bannedBox);
        lockedBox = (CheckBox) findViewById(R.id.lockedBox);
        adminBox = (CheckBox) findViewById(R.id.adminBox);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedUser = (User) extras.getSerializable("selectedUser");
            username.setText(selectedUser.getUsername());
            bannedBox.setChecked(selectedUser.getBannedStatus());
            lockedBox.setChecked(selectedUser.getLockStatus());
            adminBox.setChecked(selectedUser.getAdminStatus());
        }
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

    /**
     * Alters the User admin status based on the toggle button's checked status
     */
    public void updateAdmin(View view) {
        selectedUser.setAdminStatus(adminBox.isChecked());
        Log.d("IS ADMIN", selectedUser.getAdminStatus() + "");
        Log.d("IS CHECKED", adminBox.isChecked() + "");
    }

    /**
     * Alters the User admin status based on the toggle button's checked status
     */
    public void updateBan(View view) {
        //selectedUser.setBanStatus(bannedToggle.isChecked());
        selectedUser.setBanStatus(bannedBox.isChecked());
        Log.d("BAN", "" + selectedUser.getBannedStatus());
    }

    /**
     * Alters the User admin status based on the toggle button's checked status
     */
    public void updateLock(View view) {
        selectedUser.setLockStatus(lockedBox.isChecked());
        Log.d("IS LOCKED", selectedUser.getLockStatus() + "");
        Log.d("IS (LOCK) CHECKED", lockedBox.isChecked() + "");
    }


    /**
     * Saves the status changes to the UserManager
     * @param view The current button being pressed to activate the method
     */
    public void saveChanges(View view) {
        UserManager manager = new UserManager();
        manager.editUserStatus(selectedUser.getUsername(), selectedUser.getLockStatus(), selectedUser.getAdminStatus(),
                selectedUser.getBannedStatus());
        Toast saved = Toast.makeText(this, "User saved", Toast.LENGTH_SHORT);
        saved.show();
        onBackPressed();
    }
}
