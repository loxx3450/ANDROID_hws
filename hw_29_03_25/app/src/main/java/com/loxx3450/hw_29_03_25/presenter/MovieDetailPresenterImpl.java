package com.loxx3450.hw_29_03_25.presenter;

import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.view.MovieDetailView;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private final MovieDetailView view;

    public MovieDetailPresenterImpl(MovieDetailView view) {
        this.view = view;
    }

    @Override
    public void showMovie(Movie movie) {
        view.showMovieDetails(movie);
    }
}
