package com.techflix.group36.techflix.User;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.techflix.group36.techflix.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Scott on 3/3/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
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
     * @param resource Resource id for the item
     * @param users  The objects to represent in the ListView.
     */
    public UserListAdapter(Context context, @SuppressWarnings("SameParameterValue") int resource, ArrayList<User> users) {
        super(context, resource, users);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_info, parent, false);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.admin = (TextView) convertView.findViewById(R.id.admin);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder.username == null) {
            throw new IllegalArgumentException("username");
        }
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

        return convertView;
    }

    @Override
    public Filter getFilter() {

        @SuppressWarnings("UnnecessaryLocalVariable") Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                Collections.sort((ArrayList<User>) results.values);
                addAll((ArrayList<User>) results.values);
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<User> filteredUsers = new ArrayList<>();


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

                return results;
            }
        };

        return filter;
    }

}
