package com.loxx3450.hw_11_04_25.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_11_04_25.R;
import com.loxx3450.hw_11_04_25.adapter.AuthorAdapter;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.viewModel.AuthorsListViewModel;

import java.util.List;

public class AuthorsListActivity extends AppCompatActivity implements AuthorAdapter.OnAuthorClickListener {

    // UI elements
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    // Adapter
    private AuthorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authors_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.authorsList), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get UI
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        // Setup of adapter
        adapter = new AuthorAdapter(this);
        recyclerView.setAdapter(adapter);

        // Fetch authors
        AuthorsListViewModel viewModel = new ViewModelProvider(this).get(AuthorsListViewModel.class);
        viewModel.getAuthors().observe(this, this::fetchAuthors);

        // Callbacks
        Button createNewButton = findViewById(R.id.createNewButton);
        createNewButton.setOnClickListener(v -> openAuthorFormActivity());
    }

    private void fetchAuthors(List<Author> authors) {
        if (authors.isEmpty()) {
            Toast.makeText(this, "There are no authors so far", Toast.LENGTH_SHORT).show();
        }

        adapter.setAuthors(authors);
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
    }

    private void openAuthorFormActivity() {
        Intent intent = new Intent(AuthorsListActivity.this, AuthorFormActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAuthorClick(Author author) {
        Intent intent = new Intent(AuthorsListActivity.this, BooksListActivity.class);
        intent.putExtra("author_id", author.getId());

        startActivity(intent);
    }
}
