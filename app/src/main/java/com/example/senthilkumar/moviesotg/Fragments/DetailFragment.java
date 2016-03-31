package com.example.senthilkumar.moviesotg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Classes.Movie;
import com.example.senthilkumar.moviesotg.Classes.TVShow;
import com.example.senthilkumar.moviesotg.R;
import com.example.senthilkumar.moviesotg.Volley.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView name, year, genres, release_date, language, rating, voteCount, description;
    ImageView imageView_poster;
    VolleySingleton volleySingleton = VolleySingleton.getInstance();
    ImageLoader imageLoader = volleySingleton.getImageLoader();

    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        name = (TextView) view.findViewById(R.id.title_detail);
        year = (TextView) view.findViewById(R.id.year_detail);
        genres = (TextView) view.findViewById(R.id.genre_detail);
        release_date = (TextView) view.findViewById(R.id.release_detail);
        language = (TextView) view.findViewById(R.id.lang_detail);
        rating = (TextView) view.findViewById(R.id.rating_detail);
        voteCount = (TextView) view.findViewById(R.id.voteCount_detail);
        description = (TextView) view.findViewById(R.id.description_details);
        imageView_poster = (ImageView) view.findViewById(R.id.imageView_details);

        if("Movie".equals(getArguments().getString(MainActivity.DETAIL_FRAGMENT_TYPE_KEY))) {
            PopulateMovieData((Movie) getArguments().getSerializable(MainActivity.DETAIL_FRAGMENT_KEY));
        } else {
            PopulateTVShowData((TVShow) getArguments().getSerializable(MainActivity.DETAIL_FRAGMENT_KEY));
        }

        return view;
    }

    public void PopulateMovieData(Movie movie) {
        if (movie != null) {
            name.setText(movie.getName());
            String date = String.valueOf(movie.getRelease_date());
            if(date.length() > 4)
                year.setText(date.substring(0, 4));
            StringBuilder str = new StringBuilder();
            int[] genres_ids = movie.getGenres();
            for (int genre : genres_ids) {
                str.append(Movie.GENRES.get(genre));
                str.append(" ");
            }
            genres.setText(str);
            release_date.setText(String.format("%s%s", getString(R.string.release_detail), movie.getRelease_date()));
            language.setText(R.string.language_detail);
            rating.setText(String.format("%s%s", String.valueOf(movie.getRating()), getString(R.string.ten_detail)));
            voteCount.setText(String.valueOf(movie.getVoteCount()));
            description.setText(movie.getDescription());

            String urlThumbnail = MainActivity.URL_THUMBNAIL_DETAILS + movie.getURL_thumbnail();
            if(movie.getURL_thumbnail() != null) {
                imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        imageView_poster.setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageView_poster.setImageResource(R.mipmap.ic_launcher);
                    }
                });
            }
        }
    }

    public void PopulateTVShowData(TVShow tvShow) {
        if (tvShow != null) {
            name.setText(tvShow.getName());
            String date = String.valueOf(tvShow.getAir_date());
            if(date.length() > 4)
                year.setText(date.substring(0, 4));
            StringBuilder str = new StringBuilder();
            int[] genres_ids = tvShow.getGenres();
            for (int genre : genres_ids) {
                str.append(Movie.GENRES.get(genre));
                str.append(" ");
            }
            genres.setText(str);
            release_date.setText(String.format("%s%s", getString(R.string.release_detail), tvShow.getAir_date()));
            language.setText(R.string.language_detail);
            rating.setText(String.format("%s%s", String.valueOf(tvShow.getRating()), getString(R.string.ten_detail)));
            voteCount.setText(String.valueOf(tvShow.getVoteCount()));
            description.setText(tvShow.getDescription());

            String urlThumbnail = MainActivity.URL_THUMBNAIL_DETAILS + tvShow.getURL_thumbnail();
            if(tvShow.getURL_thumbnail() != null) {
                imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        imageView_poster.setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imageView_poster.setImageResource(R.mipmap.ic_launcher);
                    }
                });
            }
        }
    }
}
