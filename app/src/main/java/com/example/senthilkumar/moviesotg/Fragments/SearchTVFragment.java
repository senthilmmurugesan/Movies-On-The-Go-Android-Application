package com.example.senthilkumar.moviesotg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Classes.ReadJSONTVShow;
import com.example.senthilkumar.moviesotg.Classes.TVShow;
import com.example.senthilkumar.moviesotg.Classes.TVShowAdapter;
import com.example.senthilkumar.moviesotg.Interfaces.RecyclerViewClickListener;
import com.example.senthilkumar.moviesotg.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTVFragment extends Fragment implements SearchView.OnQueryTextListener, RecyclerViewClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    TVShowAdapter tvShowAdapter;
    ReadJSONTVShow readJSONTVShow = new ReadJSONTVShow();

    public SearchTVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tv, container, false);
        searchView = (SearchView) view.findViewById(R.id.searchView_tv);
        searchView.setOnQueryTextListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_search_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TVShowAdapter(getActivity(), this);
        recyclerView.setAdapter(tvShowAdapter);

        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!query.trim().equals("")) {
            query = query.replaceAll(" ", "%20");
            readJSONTVShow.GetData(MainActivity.URL_SEARCH_TV + query + "&" + MainActivity.KEY, tvShowAdapter, getActivity());
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if(!query.trim().equals("")) {
            query = query.replaceAll(" ", "%20");
            readJSONTVShow.GetData(MainActivity.URL_SEARCH_TV + query + "&" + MainActivity.KEY, tvShowAdapter, getActivity());
        } else {
            tvShowAdapter.deleteAll();
        }
        return true;
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
