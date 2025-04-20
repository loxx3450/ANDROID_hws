package com.loxx3450.hw_29_03_25.presenter;

import static com.loxx3450.hw_29_03_25.activity.DetailActivity.SELECTED_MOVIE;

import android.content.Intent;
import android.content.res.Configuration;

import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.view.DetailView;

public class DetailPresenterImpl implements DetailPresenter {
    private final DetailView view;

    public DetailPresenterImpl(DetailView view) {
        this.view = view;
    }

    @Override
    public void init(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            view.handleLandscape();
        }
    }

    @Override
    public void onReturnClicked() {
        view.openMainActivity();
    }

    @Override
    public void showMovie(Intent intent) {
        Movie movie = intent.getParcelableExtra(SELECTED_MOVIE);
        view.showMovie(movie);
    }
}
