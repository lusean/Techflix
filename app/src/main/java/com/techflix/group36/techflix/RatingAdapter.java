package com.techflix.group36.techflix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by akeaswaran on 3/7/16.
 */
public class RatingAdapter  extends ArrayAdapter<Rating> {

    private static class ViewHolder {
        TextView username;
        RatingBar starsBar;
        TextView comment;
    }
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param ratings  The objects to represent in the ListView.
     */
    public RatingAdapter(Context context, int resource, ArrayList<Rating> ratings) {
        super(context, R.layout.item_rating, ratings);
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
            viewHolder.starsBar = (RatingBar) convertView.findViewById(R.id.starsBar);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(rating.getAuthor().getUsername());
        viewHolder.starsBar.setRating(rating.getStars());
        viewHolder.comment .setText(rating.getComment());
        return convertView;
    }
}
