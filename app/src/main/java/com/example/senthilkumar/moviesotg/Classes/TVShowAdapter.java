package com.example.senthilkumar.moviesotg.Classes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.senthilkumar.moviesotg.Activities.MainActivity;
import com.example.senthilkumar.moviesotg.Interfaces.RecyclerViewClickListener;
import com.example.senthilkumar.moviesotg.R;
import com.example.senthilkumar.moviesotg.Volley.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Senthil Kumar on 3/24/2016.
 */
public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RecyclerViewClickListener listener;
    private ArrayList<TVShow> tvShows = new ArrayList<>();

    public TVShowAdapter(Context context, RecyclerViewClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setTVShowsList(ArrayList<TVShow> list) {
        tvShows = list;
        notifyItemRangeChanged(0, list.size());
        notifyDataSetChanged();
    }

    @Override
    public TVShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_layout, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TVShowViewHolder holder, int position) {
        TVShow someShow = tvShows.get(position);
        holder.name.setText(someShow.getName());
        StringBuilder str = new StringBuilder();
        int[] genres = someShow.getGenres();
        for (int genre : genres) {
            str.append(Movie.GENRES.get(genre));
            str.append(" ");
        }
        holder.genre.setText(str);
        holder.rating.setText(String.valueOf(someShow.getRating()));
        String date = String.valueOf(someShow.getAir_date());
        if(date.length() > 4)
            holder.year.setText(date.substring(0, 4));
        holder.description.setText(someShow.getDescription());

        String urlThumbnail = MainActivity.URL_THUMBNAIL + someShow.getURL_thumbnail();
        if(someShow.getURL_thumbnail() != null) {
            imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.image.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    holder.image.setImageResource(R.mipmap.ic_launcher);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public TVShow getTVShow(int position) {
        return tvShows.get(position);
    }

    public void deleteAll() {
        tvShows.clear();
        notifyDataSetChanged();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, genre, rating, year, description;
        private ImageView image;

        public TVShowViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.movieName);
            genre = (TextView) itemView.findViewById(R.id.genres);
            rating = (TextView) itemView.findViewById(R.id.rating);
            year = (TextView) itemView.findViewById(R.id.year);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }
}
