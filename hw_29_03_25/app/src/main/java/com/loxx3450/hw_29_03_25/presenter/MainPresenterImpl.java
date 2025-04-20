package com.loxx3450.hw_29_03_25.presenter;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.view.MainView;

public class MainPresenterImpl implements MainPresenter {

    private final MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void updateUI(Movie movie, int orientation) {
        if (orientation == ORIENTATION_PORTRAIT) {
            view.openDetailActivity(movie);
        } else {
            view.updateDetailFragment(movie);
        }
    }
}
