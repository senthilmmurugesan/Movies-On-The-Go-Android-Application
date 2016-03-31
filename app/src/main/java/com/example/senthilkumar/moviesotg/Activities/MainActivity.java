package com.example.senthilkumar.moviesotg.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.senthilkumar.moviesotg.Fragments.DiscoverMovieFragment;
import com.example.senthilkumar.moviesotg.Fragments.DiscoverTVShowFragment;
import com.example.senthilkumar.moviesotg.Fragments.GenreMovieFragment;
import com.example.senthilkumar.moviesotg.Fragments.SearchMovieFragment;
import com.example.senthilkumar.moviesotg.Fragments.SearchTVFragment;
import com.example.senthilkumar.moviesotg.R;
import com.example.senthilkumar.moviesotg.Interfaces.RecyclerViewClickListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String URL_DISCOVER_MOVIE = "http://api.themoviedb.org/3/discover/movie?";
    public static final String URL_DISCOVER_TV = "http://api.themoviedb.org/3/discover/tv?";
    public static final String URL_SEARCH_MOVIE = "http://api.themoviedb.org/3/search/movie?query=";
    public static final String URL_SEARCH_TV = "http://api.themoviedb.org/3/search/tv?query=";
    public static final String URL_GENRE = "http://api.themoviedb.org/3/genre/";
    public static final String URL_THUMBNAIL = "http://image.tmdb.org/t/p/w150";
    public static final String URL_THUMBNAIL_DETAILS = "http://image.tmdb.org/t/p/w300";
    public static final String KEY = "api_key=326955897126f579280b70abfc9fbf27";

    public static final String DETAIL_FRAGMENT_KEY = "SelectedObject";
    public static final String DETAIL_FRAGMENT_TYPE_KEY = "ObjectType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_discover_movie));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = new DiscoverMovieFragment();

        switch (id) {
            case R.id.nav_discover_movie:
                fragment = new DiscoverMovieFragment();
                break;
            case R.id.nav_search_movie:
                fragment = new SearchMovieFragment();
                break;
            case R.id.nav_genre_movie:
                fragment = new GenreMovieFragment();
                break;
            case R.id.nav_discover_tv:
                fragment = new DiscoverTVShowFragment();
                break;
            case R.id.nav_search_tv:
                fragment = new SearchTVFragment();
                break;
        }

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
