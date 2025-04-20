package com.loxx3450.hw_29_03_25.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_29_03_25.R;
import com.loxx3450.hw_29_03_25.adapter.MovieAdapter;
import com.loxx3450.hw_29_03_25.model.Movie;
import com.loxx3450.hw_29_03_25.presenter.MovieListPresenterImpl;
import com.loxx3450.hw_29_03_25.view.MovieListView;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment implements MovieListView {
    public interface OnMovieSelectedListener {
        void onMovieSelected(Movie movie);
    }

    private OnMovieSelectedListener listener;
    private RecyclerView recyclerView;
    private MovieListPresenterImpl presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMovieSelectedListener) {
            listener = (OnMovieSelectedListener) context;
        } else {
            throw new RuntimeException("OnMovieSelectedListener is not implemented");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        presenter = new MovieListPresenterImpl(this);
        presenter.loadMovies();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        MovieAdapter adapter = new MovieAdapter(movies, listener);
        recyclerView.setAdapter(adapter);
    }
}
