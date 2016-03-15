package com.techflix.group36.techflix.User;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.techflix.group36.techflix.Activity.AdminActivity;
import com.techflix.group36.techflix.R;

import java.util.ArrayList;

/**
 * Created by Scott on 3/3/2016.
 */
public class UserListAdapter extends ArrayAdapter<User> implements Filterable{

    private static class ViewHolder {
        TextView username;
        TextView status;
        TextView admin;
    }
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param users  The objects to represent in the ListView.
     */
    public UserListAdapter(Context context, int resource, ArrayList<User> users) {
        super(context, R.layout.user_info, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final User user = getItem(position);
        final Context curContext = parent.getContext();
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_info, parent, false);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.admin = (TextView) convertView.findViewById(R.id.admin);
            convertView.setTag(viewHolder);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(curContext, AdminActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("selectedUser", user);
                    intent.putExtras(b);
                    curContext.startActivity(intent);
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder.username == null) {
            throw new IllegalArgumentException("username");
        }
        // Populate the data into the template view using the data object
        viewHolder.username.setText(user.getUsername());
        String status;
        if (user.getBannedStatus()) {
            status = "Banned";
        } else if (user.getLockStatus()) {
            status = "Locked";
        } else {
            status = "Active";
        }
        viewHolder.status.setText(status);
        if (user.getAdminStatus()) {
            viewHolder.admin.setText("Admin");
        } else {
            viewHolder.admin.setText("Not Admin");
        }

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((ArrayList<User>) results.values);
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<User> filteredUsers = new ArrayList<User>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                ArrayList<User> dataNames = new ArrayList<>(UserManager.getUserMap().values());
                for (int i = 0; i < dataNames.size(); i++) {
                    User oneUser = dataNames.get(i);
                    if (oneUser.getUsername().toLowerCase().startsWith(constraint.toString()))  {
                        filteredUsers.add(oneUser);
                    }
                }

                results.count = filteredUsers.size();
                results.values = filteredUsers;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }


}
