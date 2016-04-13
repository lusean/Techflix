package com.techflix.group36.techflix.Movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techflix.group36.techflix.R;

import java.util.ArrayList;

/**
 * Created by osharifali on 2/17/16.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MovieAdapter extends ArrayAdapter<Movie> {


    private static class ViewHolder {
        private TextView title;
        private TextView year;
        private TextView rating;
    }
    /**
     * Constructor
     * @param context  The current context.
     * @param resource Resource id for the item
     * @param movies  The objects to represent in the ListView.
     */
    public MovieAdapter(Context context, @SuppressWarnings("SameParameterValue") int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Movie movie = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.movieTitle);
            viewHolder.year = (TextView) convertView.findViewById(R.id.movieYear);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.movieRating);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(movie.getTitle());
        viewHolder.rating.setText(movie.getMpaaRating());
        viewHolder.year.setText(movie.getYear());
        return convertView;
    }


}
