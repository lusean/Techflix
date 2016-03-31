package com.techflix.group36.techflix.User;

import com.techflix.group36.techflix.Movie.Movie;
import com.techflix.group36.techflix.Rating.Rating;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by developer on 3/30/16.
 */
public class SaveObject implements Serializable {
    private Map<String, User> userList;

    public SaveObject(Map<String, User> userList) {
        this.userList = userList;
    }

    public Map<String, User> getUserList() {
        return userList;
    }

    /*
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.userList);
        out.writeObject(this.ratingList);
        out.writeObject(this.ratedMovieList);
        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.userList = (Map<String, User>)in.readObject();
        this.ratingList = (HashMap<Integer, Rating>)in.readObject();
        this.ratedMovieList = (ArrayList<Movie>)in.readObject();
        in.close();
    }
    */

}
