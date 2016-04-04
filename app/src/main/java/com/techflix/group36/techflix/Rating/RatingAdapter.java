package com.techflix.group36.techflix.Rating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techflix.group36.techflix.R;

import java.util.ArrayList;

/**
 * Created by akeaswaran on 3/7/16.
 */
@SuppressWarnings("DefaultFileTemplate")
public class RatingAdapter  extends ArrayAdapter<Rating> {


    private static class ViewHolder {
        TextView username;
        TextView stars;
        TextView comment;
    }
    /**
     * Constructor
     * @param context  The current context.
     * @param ratings  The objects to represent in the ListView.
     */
    public RatingAdapter(Context context, int resource, ArrayList<Rating> ratings) {
        super(context, resource, ratings);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Rating rating = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_rating, parent, false);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.stars = (TextView) convertView.findViewById(R.id.stars);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(rating.getAuthor().getUsername());
        String starsText = "" + rating.getStars() + "";
        viewHolder.stars.setText(starsText);
        viewHolder.comment .setText(rating.getComment());
        return convertView;
    }
}
