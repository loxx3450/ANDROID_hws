package com.loxx3450.hw_05_04_25;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_05_04_25.adapter.MovieAdapter;
import com.loxx3450.hw_05_04_25.model.Movie;
import com.loxx3450.hw_05_04_25.model.MovieResponse;
import com.loxx3450.hw_05_04_25.repository.MovieRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MovieRepository repository;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button getNextButton;

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        getNextButton = findViewById(R.id.getNextButton);

        repository = new MovieRepository();

        repository.get(callback);

        getNextButton.setOnClickListener(v -> {
            updateUIOnRequest();
            repository.get(++currentPage, callback);
        });
    }

    private final Callback<MovieResponse> callback = new Callback<MovieResponse>() {
        @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
            handleResponse(response);
        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private void handleResponse(Response<MovieResponse> response) {
        if (response.isSuccessful()) {
            List<Movie> movies = response.body().getResults();

            MovieAdapter adapter = new MovieAdapter(movies);
            recyclerView.setAdapter(adapter);

            updateUIOnResponse();
        } else {
            System.out.println("Something unexpected happened");
        }
    }

    private void updateUIOnRequest() {
        recyclerView.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    private void updateUIOnResponse() {
        recyclerView.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }
}