package com.techflix.group36.techflix;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;




import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by osharifali on 2/15/16.
 */
public class MainActivity extends Activity {
    Button search;
    SearchView searchBar;
    ListView movieList;
    private RequestQueue queue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        search = (Button) findViewById(R.id.search);
        searchBar = (SearchView) findViewById(R.id.searchBar);
        movieList = (ListView) findViewById(R.id.movieList);
        //movieList.setAdapter();
        queue = Volley.newRequestQueue(this);
    }

    public void searchForMovie(View view) {
        String searchQuery = searchBar.getQuery().toString();
        searchQuery = searchQuery.replaceAll("\\s", "+");
        String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
                + searchQuery + "&page_limit=10&page=1&apikey=6ct7v5gbrq4mysgczcxvr8qw";
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject resp) {
//                        //handle a valid response coming back.  Getting this string mainly for debug
//                        response = resp.toString();
//                        //printing first 500 chars of the response.  Only want to do this for debug
//                        TextView view = (TextView) findViewById(R.id.textView2);
//                        view.setText(response.substring(0, 500));
//
//                        //Now we parse the information.  Looking at the format, everything encapsulated in RestResponse object
//                        JSONObject obj1 = null;
//                        try {
//                            obj1 = resp.getJSONObject("RestResponse");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        assert obj1 != null;
//                        //From that object, we extract the array of actual data labeled result
//                        JSONArray array = obj1.optJSONArray("result");
//                        ArrayList<Thread.State> states = new ArrayList<>();
//                        for(int i=0; i < array.length(); i++) {
//
//                            try {
//                                //for each array element, we have to create an object
//                                JSONObject jsonObject = array.getJSONObject(i);
//                                State s = new State();
//                                assert jsonObject != null;
//                                s.setName(jsonObject.optString("name"));
//                                s.setA2Code(jsonObject.optString("alpha2_code"));
//                                s.setA3Code(jsonObject.optString("alpha3_code"));
//                                //save the object for later
//                                states.add(s);
//
//
//                            } catch (JSONException e) {
//                                Log.d("VolleyApp", "Failed to get JSON object");
//                                e.printStackTrace();
//                            }
//                        }
//                        //once we have all data, then go to list screen
//                        changeView(states);
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        response = "JSon Request Failed!!";
//                        //show error on phone
//                        TextView view = (TextView) findViewById(R.id.textView2);
//                        view.setText(response);
//                    }
//                });
//        //this actually queues up the async response with Volley
//        queue.add(jsObjRequest);
    }
}