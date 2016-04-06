package com.techflix.group36.techflix;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.techflix.group36.techflix.Activity.MainActivity;
import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Movie.MovieAdapter;


/**
 * Created by osharifali on 4/4/16.
 */
@SuppressWarnings({"DefaultFileTemplate"})
public class PopulateListTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2}.
     *
     */
    public PopulateListTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testListViewNotNull() {
        MainActivity activity = getActivity();
        final ListView list = (ListView) activity.findViewById(R.id.movieList);
        assertNotNull(list);
    }

    public void testPopulateList() {
        MainActivity activity = getActivity();

        final Movie movieOne = new Movie("I Am Legend" , "2008", "PG-13");
        final Movie movieTwo = new Movie("Rocky" , "1978", "R");
        final Movie movieThree = new Movie("Deadpool" , "2016", "R");
        final ListView list = (ListView) activity.findViewById(R.id.movieList);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                list.requestFocus();
                final MovieAdapter adapter = (MovieAdapter) list.getAdapter();
                adapter.add(movieOne);
                adapter.add(movieTwo);
                adapter.add(movieThree);
                adapter.notifyDataSetChanged();
            }
        });



        assertTrue(list.getAdapter().getItem(0) == movieOne);
        assertTrue(list.getAdapter().getItem(1) == movieTwo);
        assertTrue(list.getAdapter().getItem(2) == movieThree);
    }




}
