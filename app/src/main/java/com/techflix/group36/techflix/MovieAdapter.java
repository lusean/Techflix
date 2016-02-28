package com.techflix.group36.techflix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osharifali on 2/17/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {


    private static class ViewHolder {
        TextView title;
        TextView year;
        TextView rating;
    }
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param movies  The objects to represent in the ListView.
     */
    public MovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Movie movie = getItem(position);
        final Context curContext = parent.getContext();
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.movieTitle);
            viewHolder.year = (TextView) convertView.findViewById(R.id.movieYear);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.movieRating);
            convertView.setTag(viewHolder);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(curContext, RateMovieActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("selectedMovie", movie);
                    intent.putExtras(b); //Put your id to your next Intent
                    curContext.startActivity(intent);
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.title.setText(movie.getTitle());
        viewHolder.rating.setText(movie.getMpaaRating());
        viewHolder.year.setText(movie.getYear());
        // Return the completed view to render on screen
        return convertView;
    }


}
