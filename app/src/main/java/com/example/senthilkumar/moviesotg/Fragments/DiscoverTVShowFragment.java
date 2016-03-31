package com.example.senthilkumar.moviesotg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Classes.Movie;
import com.example.senthilkumar.moviesotg.Classes.MovieAdapter;
import com.example.senthilkumar.moviesotg.Classes.ReadJSONTVShow;
import com.example.senthilkumar.moviesotg.Classes.TVShow;
import com.example.senthilkumar.moviesotg.Classes.TVShowAdapter;
import com.example.senthilkumar.moviesotg.Interfaces.RecyclerViewClickListener;
import com.example.senthilkumar.moviesotg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverTVShowFragment extends Fragment implements RecyclerViewClickListener {
    RecyclerView recyclerView;
    TVShowAdapter tvShowAdapter;
    ReadJSONTVShow readJSONTVShow = new ReadJSONTVShow();

    public DiscoverTVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover_tv, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_discover_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TVShowAdapter(getActivity(), this);
        recyclerView.setAdapter(tvShowAdapter);
        readJSONTVShow.GetData(MainActivity.URL_DISCOVER_TV + MainActivity.KEY, tvShowAdapter, getActivity());

        return view;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        TVShowAdapter tvShowAdapter = (TVShowAdapter) recyclerView.getAdapter();
        TVShow tvShow = tvShowAdapter.getTVShow(position);
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.DETAIL_FRAGMENT_KEY, tvShow);
        bundle.putString(MainActivity.DETAIL_FRAGMENT_TYPE_KEY, "TVShow");
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).addToBackStack(null).commit();
    }
}
