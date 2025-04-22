package com.loxx3450.hw_05_04_25.service;

import com.loxx3450.hw_05_04_25.model.Movie;
import com.loxx3450.hw_05_04_25.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResponse> getMovies(@Query("page") int page);
}
