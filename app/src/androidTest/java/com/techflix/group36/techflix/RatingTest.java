package com.techflix.group36.techflix;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Rating.Rating;
import com.techflix.group36.techflix.User.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class RatingTest extends ApplicationTestCase<Application> {
    public RatingTest() {
        super(Application.class);
    }

    private ArrayList<Rating> alphaRatingsList;
    private ArrayList<Rating> betaRatingsList;
    private ArrayList<Rating> badRatingsList;

    private User alphaUser;
    private User betaUser;

    private Movie alphaMovie;
    private Movie betaMovie;

    @Before
    public void setUp() {
        alphaRatingsList = new ArrayList<Rating>();
        betaRatingsList = new ArrayList<Rating>();
        badRatingsList = new ArrayList<Rating>();

        alphaUser = new User("akeaswaran", "test", "Akshay", "Star Trek", "CS");
        betaUser = new User("hrisheekr", "test", "Hrisheek", "The Dark Knight", "CS");
        alphaMovie = new Movie("Star Trek", "2009", "R");
        betaMovie = new Movie("The Dark Knight", "2009", "R");

        alphaRatingsList.add(new Rating(5, "test", alphaUser,alphaMovie));
        alphaRatingsList.add(new Rating(4, "test", betaUser,alphaMovie));

        betaRatingsList.add(new Rating(2, "test", alphaUser,betaMovie));
        betaRatingsList.add(new Rating(3, "test", betaUser,betaMovie));

        badRatingsList.add(new Rating(5, "test", alphaUser,alphaMovie));
        badRatingsList.add(new Rating(3, "test", betaUser,betaMovie));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetRatingAvgNull() {
        try {
            Movie.getRatingAvg(null);
            fail("list passed to getRatingAvg was not null; IllegalArgumentException not thrown");
        } catch (IllegalArgumentException expectedException) {
            assertThat(expectedException.getMessage(), is("list for getRatingAvg is null"));
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetRatingAvgDiffMovies() {
        try {
            Movie.getRatingAvg(badRatingsList);
            fail("list passed to getRatingAvg contains ratings for the same movie; IllegalArgumentException not thrown");
        } catch (IllegalArgumentException expectedException) {
            assertThat(expectedException.getMessage(), is("list for getRatingAvg contains different movies"));
        }
    }

    @Test
    public void testGetRatingAvg() {
        assertEquals(4.5, Movie.getRatingAvg(alphaRatingsList), 0);
        assertEquals(2.5, Movie.getRatingAvg(betaRatingsList), 0);
    }

}