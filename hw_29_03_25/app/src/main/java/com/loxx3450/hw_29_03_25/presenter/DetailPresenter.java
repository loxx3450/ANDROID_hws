package com.loxx3450.hw_29_03_25.presenter;

import android.content.Intent;

public interface DetailPresenter {
    void init(int orientation);

    void onReturnClicked();

    void showMovie(Intent intent);
}
