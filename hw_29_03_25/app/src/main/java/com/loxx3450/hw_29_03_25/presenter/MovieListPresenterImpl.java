package com.loxx3450.hw_29_03_25.presenter;

import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.model.MovieList;
import com.loxx3450.hw_29_03_25.view.MovieListView;

import java.util.List;

public class MovieListPresenterImpl implements MovieListPresenter {
    private List<Movie> movies;
    private MovieListView view;

    public MovieListPresenterImpl(MovieListView view) {
        this.view = view;
    }

    @Override
    public void loadMovies() {
        movies = MovieList.getMovies();

        view.showMovies(movies);
    }

    @Override
    public void detachView() {
        view = null;
    }
}
