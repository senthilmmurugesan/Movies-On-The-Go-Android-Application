package com.example.senthilkumar.moviesotg.Classes;

import java.io.Serializable;

/**
 * Created by Senthil Kumar on 3/26/2016.
 */
public class TVShow implements Serializable {
    private String name;
    private String language;
    private String id;
    private String air_date;
    private String description;
    private String[] origin_countries;
    private int[] genres;
    private double popularity;
    private double rating;
    private int voteCount;
    private String URL_thumbnail;

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

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getOrigin_countries() {
        return origin_countries;
    }

    public void setOrigin_countries(String[] origin_countries) {
        this.origin_countries = origin_countries;
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
}
