package com.techflix.group36.techflix;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Rating.Rating;
import com.techflix.group36.techflix.User.User;

import org.junit.Before;
import org.junit.Rule;
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
    private ArrayList<Rating> charlieRatingsList;
    private ArrayList<Rating> badRatingsList;

    @Before
    public void setUp() {
        alphaRatingsList = new ArrayList<>();
        betaRatingsList = new ArrayList<>();
        charlieRatingsList = new ArrayList<>();
        badRatingsList = new ArrayList<>();

        User alphaUser = new User("akeaswaran", "test", "Akshay", "Star Trek", "CS");
        User betaUser = new User("hrisheekr", "test", "Hrisheek", "The Dark Knight", "CS");
        User charlieUser = new User("lusean", "test", "Sean", "A", "AE");
        Movie alphaMovie = new Movie("Star Trek", "2009", "R");
        Movie betaMovie = new Movie("The Dark Knight", "2009", "R");

        alphaRatingsList.add(new Rating(5, "test", alphaUser,alphaMovie));
        alphaRatingsList.add(new Rating(4, "test", betaUser,alphaMovie));

        betaRatingsList.add(new Rating(2, "test", alphaUser,betaMovie));
        betaRatingsList.add(new Rating(3, "test", betaUser,betaMovie));

        charlieRatingsList.add(new Rating(3, "test", alphaUser,alphaMovie));
        charlieRatingsList.add(new Rating(3, "test", betaUser,alphaMovie));
        charlieRatingsList.add(new Rating(5, "test", charlieUser,alphaMovie));

        badRatingsList.add(new Rating(5, "test", alphaUser,alphaMovie));
        badRatingsList.add(new Rating(3, "test", betaUser,betaMovie));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public void testGetRatingAvgNull() {
        try {
            Movie.getRatingAvg(null);
            fail("list passed to getRatingAvg was not null; IllegalArgumentException not thrown");
        } catch (IllegalArgumentException expectedException) {
            assertThat(expectedException.getMessage(), is("list for getRatingAvg is null"));
        }
    }

    public void testGetRatingAvgDiffMovies() {
        try {
            Movie.getRatingAvg(badRatingsList);
            fail("list passed to getRatingAvg contains ratings for the same movie; IllegalArgumentException not thrown");
        } catch (IllegalArgumentException expectedException) {
            assertThat(expectedException.getMessage(), is("list for getRatingAvg contains different movies"));
        }
    }

    public void testGetRatingAvg() {
        assertEquals(4.5, Movie.getRatingAvg(alphaRatingsList), 0);
        assertEquals(2.5, Movie.getRatingAvg(betaRatingsList), 0);
    }


    //Sean's Tests getRatingAvgOfMajor()
    public void testGetRatingAvgOfMajorNoMajor() {
        assertEquals(new Float(-1.0), Movie.getRatingAvgOfMajor(alphaRatingsList, "asdf"));
    }

    public void testGetRatingAvgOfMajor() {
        assertEquals(new Float(4.5), Movie.getRatingAvgOfMajor(alphaRatingsList, "CS"));
        assertEquals(new Float(2.5), Movie.getRatingAvgOfMajor(betaRatingsList, "CS"));
        assertEquals(new Float(3), Movie.getRatingAvgOfMajor(charlieRatingsList, "CS"));
        assertEquals(new Float(5), Movie.getRatingAvgOfMajor(charlieRatingsList, "AE"));
    }

}