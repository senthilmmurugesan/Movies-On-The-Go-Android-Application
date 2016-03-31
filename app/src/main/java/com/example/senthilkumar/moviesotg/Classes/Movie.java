package com.example.senthilkumar.moviesotg.Classes;

import android.util.SparseArray;

import java.io.Serializable;

/**
 * Created by Senthil Kumar on 3/24/2016.
 */
public class Movie implements Serializable {

    public static final SparseArray<String> GENRES = new SparseArray<String>(){{
        append(28, "Action");append(12, "Adventure");append(16, "Animation");append(35, "Comedy");
        append(80, "Crime");append(99, "Documentary");append(18, "Drama");append(10751, "Family");
        append(14, "Fantasy");append(10769, "Foreign");append(36, "History");
        append(27, "Horror");append(10402, "Music");append(9648, "Mystery");append(10749, "Romance");
        append(878, "Sci-Fi");append(53, "Thriller");append(10752, "War");append(37, "Western");append(10770, "TV Movie");
    }};

    private String name;
    private String language;
    private String id;
    private String release_date;
    private String description;
    private int[] genres;
    private double popularity;
    private double rating;
    private int voteCount;
    private String URL_thumbnail;

    public Movie() {

    }

    public Movie(String name, String language, String id, String release_date, String description, int[] genres,
                 double popularity, double rating, int voteCount) {
        this.name = name;
        this.language = language;
        this.id = id;
        this.release_date = release_date;
        this.description = description;
        this.genres = genres;
        this.popularity = popularity;
        this.rating = rating;
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getURL_thumbnail() {
        return URL_thumbnail;
    }

    public void setURL_thumbnail(String URL_thumbnail) {
        this.URL_thumbnail = URL_thumbnail;
    }

    @Override
    public String toString() {
        return this.getName() + " : " + this.getRelease_date() + " : " + this.getRating();
    }
}
