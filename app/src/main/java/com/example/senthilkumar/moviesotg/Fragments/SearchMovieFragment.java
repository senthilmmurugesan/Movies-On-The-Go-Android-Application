package com.example.senthilkumar.moviesotg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Classes.Movie;
import com.example.senthilkumar.moviesotg.Classes.MovieAdapter;
import com.example.senthilkumar.moviesotg.Interfaces.RecyclerViewClickListener;
import com.example.senthilkumar.moviesotg.R;
import com.example.senthilkumar.moviesotg.Classes.ReadJSONMovie;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMovieFragment extends Fragment implements SearchView.OnQueryTextListener, RecyclerViewClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    MovieAdapter movieAdapter;
    ReadJSONMovie readJSONMovie = new ReadJSONMovie();


    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        searchView = (SearchView) view.findViewById(R.id.searchView_movie);
        searchView.setOnQueryTextListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_search_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter(getActivity(), this);
        recyclerView.setAdapter(movieAdapter);

        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!query.trim().equals("")) {
            query = query.replaceAll(" ", "%20");
            readJSONMovie.GetData(MainActivity.URL_SEARCH_MOVIE + query + "&" + MainActivity.KEY, movieAdapter, getActivity());
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if(!query.trim().equals("")) {
            query = query.replaceAll(" ", "%20");
            readJSONMovie.GetData(MainActivity.URL_SEARCH_MOVIE + query + "&" + MainActivity.KEY, movieAdapter, getActivity());
        } else {
            movieAdapter.deleteAll();
        }
        return true;
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
