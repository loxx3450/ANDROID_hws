package com.loxx3450.hw_29_03_25.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.loxx3450.hw_29_03_25.R;
import com.loxx3450.hw_29_03_25.fragment.MovieDetailFragment;
import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.presenter.DetailPresenter;
import com.loxx3450.hw_29_03_25.presenter.DetailPresenterImpl;
import com.loxx3450.hw_29_03_25.view.DetailView;

public class DetailActivity extends AppCompatActivity implements DetailView {
    public static final String SELECTED_MOVIE = "MOVIE";
    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new DetailPresenterImpl(this);
        presenter.init(getResources().getConfiguration().orientation);

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> presenter.onReturnClicked());
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.showMovie(getIntent());
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMovie(Movie movie) {
        MovieDetailFragment fragment = (MovieDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragmentContainer);

        assert fragment != null;
        fragment.selectMovie(movie);
    }

    @Override
    public void handleLandscape() {
        finish();
        openMainActivity();
    }
}
