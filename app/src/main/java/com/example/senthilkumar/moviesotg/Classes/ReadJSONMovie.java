package com.example.senthilkumar.moviesotg.Classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Senthil Kumar on 3/25/2016.
 */
public class ReadJSONMovie {

    public void GetData(String url, final MovieAdapter movieAdapter, final Context context) {
        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        RequestQueue requestQueue = volleySingleton.getRequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Movie> movieList = parseJSONResponse(response);
                    if(movieList.size() > 0)
                        movieAdapter.setMoviesList(movieList);
                    else
                        movieAdapter.deleteAll();
                } catch (JSONException | ParseException e) {
                    ShowMessage(e.getMessage(), context);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShowMessage(error.getMessage(),context);
            }
        });
        requestQueue.add(request);
    }

    public ArrayList<Movie> parseJSONResponse(JSONObject response) throws JSONException, ParseException {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        JSONArray results = response.getJSONArray("results");

        for(int i = 0; i < results.length(); i++) {
            Movie currentMovie = new Movie();

            JSONObject listObj = results.getJSONObject(i);
            currentMovie.setDescription(listObj.getString("overview"));
            currentMovie.setRelease_date(listObj.getString("release_date"));
            currentMovie.setId(listObj.getString("id"));
            JSONArray genre = listObj.getJSONArray("genre_ids");
            int[] genre_ids = new int[genre.length()];
            for(int j = 0; j < genre.length(); j++) {
                genre_ids[j] = genre.getInt(j);
            }
            currentMovie.setGenres(genre_ids);

            currentMovie.setName(listObj.getString("original_title"));
            currentMovie.setLanguage(listObj.getString("original_language"));
            currentMovie.setPopularity(listObj.getDouble("popularity"));
            currentMovie.setVoteCount(listObj.getInt("vote_count"));
            currentMovie.setRating(listObj.getDouble("vote_average"));
            currentMovie.setURL_thumbnail(listObj.getString("poster_path"));

            movieArrayList.add(currentMovie);
        }
        return movieArrayList;
    }

    public void ShowMessage(String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Movie");
        builder.setMessage(message);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
