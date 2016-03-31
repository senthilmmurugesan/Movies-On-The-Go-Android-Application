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
public class ReadJSONTVShow {

    public void GetData(String url, final TVShowAdapter tvShowAdapter, final Context context) {
        VolleySingleton volleySingleton = VolleySingleton.getInstance();
        RequestQueue requestQueue = volleySingleton.getRequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<TVShow> tvShowsList = parseJSONResponse(response);
                    if(tvShowsList.size() > 0)
                        tvShowAdapter.setTVShowsList(tvShowsList);
                    else
                        tvShowAdapter.deleteAll();
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

    public ArrayList<TVShow> parseJSONResponse(JSONObject response) throws JSONException, ParseException {
        ArrayList<TVShow> tvShowList = new ArrayList<>();

        JSONArray results = response.getJSONArray("results");

        for(int i = 0; i < results.length(); i++) {
            TVShow currentTVShow = new TVShow();

            JSONObject listObj = results.getJSONObject(i);
            currentTVShow.setDescription(listObj.getString("overview"));
            currentTVShow.setAir_date(listObj.getString("first_air_date"));
            currentTVShow.setId(listObj.getString("id"));
            JSONArray genre = listObj.getJSONArray("genre_ids");
            int[] genre_ids = new int[genre.length()];
            for(int j = 0; j < genre.length(); j++) {
                genre_ids[j] = genre.getInt(j);
            }
            currentTVShow.setGenres(genre_ids);

            currentTVShow.setName(listObj.getString("original_name"));
            currentTVShow.setLanguage(listObj.getString("original_language"));
            currentTVShow.setPopularity(listObj.getDouble("popularity"));
            currentTVShow.setVoteCount(listObj.getInt("vote_count"));
            currentTVShow.setRating(listObj.getDouble("vote_average"));
            currentTVShow.setURL_thumbnail(listObj.getString("poster_path"));

            tvShowList.add(currentTVShow);
        }
        return tvShowList;
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
