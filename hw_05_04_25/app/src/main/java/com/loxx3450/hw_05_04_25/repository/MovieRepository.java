package com.loxx3450.hw_05_04_25.repository;

import com.loxx3450.hw_05_04_25.config.RetrofitClient;
import com.loxx3450.hw_05_04_25.model.Movie;
import com.loxx3450.hw_05_04_25.model.MovieResponse;
import com.loxx3450.hw_05_04_25.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private final ApiService apiService;

    public MovieRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void get(Callback<MovieResponse> callback) {
        var call = apiService.getMovies(1);
        call.enqueue(callback);
    }

    public void get(int page, Callback<MovieResponse> callback) {
        var call = apiService.getMovies(page);
        call.enqueue(callback);
    }

}
