package com.loxx3450.hw_29_03_25.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_29_03_25.R;
import com.loxx3450.hw_29_03_25.fragment.MovieListFragment;
import com.loxx3450.hw_29_03_25.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final MovieListFragment.OnMovieSelectedListener listener;
    private final List<Movie> movies;

    public MovieAdapter(List<Movie> movies, MovieListFragment.OnMovieSelectedListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.titleTextView.setText(movie.getTitle());
        holder.showDetailsButton.setOnClickListener(v -> listener.onMovieSelected(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final Button showDetailsButton;

        public MovieViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            showDetailsButton = itemView.findViewById(R.id.showDetailsButton);
        }
    }
}
