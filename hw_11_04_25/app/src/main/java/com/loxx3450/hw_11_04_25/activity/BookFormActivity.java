package com.loxx3450.hw_11_04_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.loxx3450.hw_11_04_25.R;
import com.loxx3450.hw_11_04_25.dto.BookFullDto;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;
import com.loxx3450.hw_11_04_25.viewModel.AuthorFormViewModel;
import com.loxx3450.hw_11_04_25.viewModel.BookFormViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class BookFormActivity extends AppCompatActivity {

    // UI Elements
    private EditText titleEditText;
    private EditText genreEditText;
    private EditText releaseYearEditText;
    private Spinner authorSpinner;

    // Adapter
    private ArrayAdapter<Author> adapter;

    // Optional fields
    private Optional<Integer> id = Optional.empty();
    private Optional<Author> selectedAuthor = Optional.empty();

    // VM
    private BookFormViewModel viewModel;

    // Disposable
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bookForm), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get UI elements
        titleEditText = findViewById(R.id.titleEditText);
        genreEditText = findViewById(R.id.genreEditText);
        releaseYearEditText = findViewById(R.id.releaseYearEditText);
        authorSpinner = findViewById(R.id.authorSpinner);

        checkIntentOnData(getIntent());

        // Setup adapter
        setupEmptyAdapter();
        authorSpinner.setAdapter(adapter);

        // Fetch authors
        viewModel = new ViewModelProvider(this).get(BookFormViewModel.class);
        viewModel.getAuthors().observe(this, this::fetchAuthors);

        // Callbacks
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> handleSubmit());

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> openBooksListActivity());
    }

    private void checkIntentOnData(Intent intent) {
        if (intent.hasExtra("book_full_data")) {
            BookFullDto book = intent.getParcelableExtra("book_full_data");

            id = Optional.of(book.getId());

            titleEditText.setText(book.getTitle());
            genreEditText.setText(book.getGenre());
            releaseYearEditText.setText(String.valueOf(book.getReleaseYear()));

            selectedAuthor = Optional.of(book.getAuthor());
        }
    }

    private void setupEmptyAdapter() {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new ArrayList<Author>()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void fetchAuthors(List<Author> authors) {
        if (authors.isEmpty()) {
            Toast.makeText(this, "There are no authors so far", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter.clear();
        adapter.addAll(authors);

        // Select already selected author by Update
        if (selectedAuthor.isPresent()) {
            int position = authors.indexOf(selectedAuthor.get());
            authorSpinner.setSelection(position);

            selectedAuthor = Optional.empty();
        }
    }

    private void handleSubmit() {
        Author selectedAuthor = (Author) authorSpinner.getSelectedItem();

        String title = titleEditText.getText().toString();
        String genre = genreEditText.getText().toString();
        int releaseYear = Integer.parseInt(releaseYearEditText.getText().toString());

        if (title.isEmpty() || genre.isEmpty() || selectedAuthor == null) {
            Toast.makeText(this, "Values cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book = new Book(title, genre, releaseYear);

        Completable action;

        if (id.isPresent()) {
            action = viewModel.updateBook(id.get(), book, selectedAuthor);
        } else {
            action = viewModel.insertBook(book, selectedAuthor);
        }

        disposable = action.observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::openBooksListActivity,
                        throwable -> showError());
    }

    private void openBooksListActivity() {
        Intent intent = new Intent(BookFormActivity.this, BooksListActivity.class);
        startActivity(intent);
    }

    private void showError() {
        Toast.makeText(this, "Something went wrong! Try again later...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}
