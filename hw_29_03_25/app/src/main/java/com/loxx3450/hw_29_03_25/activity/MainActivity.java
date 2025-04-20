package com.loxx3450.hw_29_03_25.activity;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.loxx3450.hw_29_03_25.R;
import com.loxx3450.hw_29_03_25.fragment.MovieDetailFragment;
import com.loxx3450.hw_29_03_25.fragment.MovieListFragment;
import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.presenter.MainPresenter;
import com.loxx3450.hw_29_03_25.presenter.MainPresenterImpl;
import com.loxx3450.hw_29_03_25.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView, MovieListFragment.OnMovieSelectedListener {
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        int orientation = getResources().getConfiguration().orientation;
        presenter.updateUI(movie, orientation);
    }

    @Override
    public void openDetailActivity(Movie movie) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.SELECTED_MOVIE, movie);

        startActivity(intent);
    }

    @Override
    public void updateDetailFragment(Movie movie) {
        MovieDetailFragment detailFragment = (MovieDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragmentContainer);

        assert detailFragment != null;
        detailFragment.selectMovie(movie);
    }
}