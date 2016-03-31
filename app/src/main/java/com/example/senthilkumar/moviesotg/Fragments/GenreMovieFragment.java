package com.example.senthilkumar.moviesotg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Classes.Movie;
import com.example.senthilkumar.moviesotg.Classes.MovieAdapter;
import com.example.senthilkumar.moviesotg.Classes.ReadJSONMovie;
import com.example.senthilkumar.moviesotg.Interfaces.RecyclerViewClickListener;
import com.example.senthilkumar.moviesotg.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenreMovieFragment extends Fragment implements AdapterView.OnItemSelectedListener, RecyclerViewClickListener {
    Spinner spinner_genres;
    ReadJSONMovie readJSONMovie = new ReadJSONMovie();
    RecyclerView recyclerView;
    ArrayList<String> genres = new ArrayList<>();

    public GenreMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genre_movie, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_genres_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        spinner_genres = (Spinner) view.findViewById(R.id.spinner_genres_movie);
        for(int i = 0; i < Movie.GENRES.size(); i++) {
            genres.add(Movie.GENRES.valueAt(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, genres);
        spinner_genres.setAdapter(adapter);
        spinner_genres.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), this);
        recyclerView.setAdapter(movieAdapter);
        String url = MainActivity.URL_GENRE + Movie.GENRES.keyAt(position) + "/movies?" + MainActivity.KEY;
        readJSONMovie.GetData(url, movieAdapter, getActivity());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        MovieAdapter movieAdapter = (MovieAdapter) recyclerView.getAdapter();
        Movie movie = movieAdapter.getMovie(position);
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.DETAIL_FRAGMENT_KEY, movie);
        bundle.putString(MainActivity.DETAIL_FRAGMENT_TYPE_KEY, "Movie");
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).addToBackStack(null).commit();
    }
}
