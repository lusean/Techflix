package main.java.com.techflix.group36.techflix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Scott on 3/10/2016.
 */
public class AdminActivity extends Activity {

    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpowers);
        TextView username = (TextView) findViewById(R.id.userID);
        ToggleButton bannedToggle = (ToggleButton) findViewById(R.id.bannedToggle);
        ToggleButton lockedToggle = (ToggleButton) findViewById(R.id.lockedToggle);
        ToggleButton adminToggle = (ToggleButton) findViewById(R.id.adminToggle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedUser = (User) extras.getSerializable("selectedUser");
            username.setText(selectedUser.getUsername());
            bannedToggle.setChecked(selectedUser.getBannedStatus());
            lockedToggle.setChecked(selectedUser.getLockedStatus());
            adminToggle.setChecked(selectedUser.getAdminStatus());
        }
        lockedToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedUser.setLockStatus(true);
                } else {
                    selectedUser.setLockStatus(false);
                }
            }
        });
        bannedToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedUser.setBanStatus(true);
                } else {
                    selectedUser.setBanStatus(false);
                }
            }
        });
        adminToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedUser.setAdminStatus(true);
                } else {
                    selectedUser.setAdminStatus(false);
                }
            }
        });
    }


}
