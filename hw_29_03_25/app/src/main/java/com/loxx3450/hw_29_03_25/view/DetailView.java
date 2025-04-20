package com.loxx3450.hw_29_03_25.view;

import com.loxx3450.hw_29_03_25.model.Movie;

public interface DetailView {
    void showMovie(Movie movie);
    void handleLandscape();
    void openMainActivity();
}
