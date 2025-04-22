package com.loxx3450.hw_05_04_25.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Movie {
    private String title;
    private double popularity;

    @SerializedName("release_date")
    private Date releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
