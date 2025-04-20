package com.loxx3450.hw_29_03_25.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loxx3450.hw_29_03_25.R;
import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.presenter.MovieDetailPresenter;
import com.loxx3450.hw_29_03_25.presenter.MovieDetailPresenterImpl;
import com.loxx3450.hw_29_03_25.view.MovieDetailView;

public class MovieDetailFragment extends Fragment implements MovieDetailView {

    private TextView titleTextView;
    private TextView descriptionTextView;

    private MovieDetailPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        titleTextView = view.findViewById(R.id.titleTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);

        presenter = new MovieDetailPresenterImpl(this);

        return view;
    }

    public void selectMovie(Movie movie) {
        presenter.showMovie(movie);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        titleTextView.setText(movie.getTitle());
        descriptionTextView.setText(movie.getDescription());
    }
}
