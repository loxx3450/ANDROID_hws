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
import com.loxx3450.hw_11_04_25.adapter.BookAdapter;
import com.loxx3450.hw_11_04_25.dto.BookFullDto;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;
import com.loxx3450.hw_11_04_25.viewModel.BooksListViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class BooksListActivity extends AppCompatActivity implements BookAdapter.OnBookClickListener {

    // UI Elements
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    // Adapter
    private BookAdapter adapter;

    // VM
    private BooksListViewModel viewModel;

    // Disposable
    private Disposable deleteDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_books_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booksList), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get UI elements
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        // Setting up Recycler View
        adapter = new BookAdapter(this);
        recyclerView.setAdapter(adapter);

        fetchBooks();

        // Callbacks
        Button createNewButton = findViewById(R.id.createNewButton);
        createNewButton.setOnClickListener(v -> openBookFormActivityWithoutExtra());
    }

    private void fetchBooks() {
        viewModel = new ViewModelProvider(this).get(BooksListViewModel.class);

        if (getIntent().hasExtra("author_id")) {
            int authorId = getIntent().getIntExtra("author_id", -1);
            viewModel.getBooksByAuthorId(authorId).observe(this, this::handleBooksList);
        } else {
            viewModel.getBooks().observe(this, this::handleBooksList);
        }
    }

    private void handleBooksList(List<BookFullDto> books) {
        if (books.isEmpty()) {
            Toast.makeText(this, "There are no books so far", Toast.LENGTH_SHORT).show();
        }

        adapter.setBooks(books);
        adapter.notifyDataSetChanged();

        // Update UI
        if (progressBar.getVisibility() == VISIBLE) {
            progressBar.setVisibility(GONE);
            recyclerView.setVisibility(VISIBLE);
        }
    }

    private void openBookFormActivityWithoutExtra() {
        Intent intent = new Intent(BooksListActivity.this, BookFormActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBookClick(BookFullDto book) {
        Intent intent = new Intent(BooksListActivity.this, BookFormActivity.class);
        intent.putExtra("book_full_data", book);

        startActivity(intent);
    }

    @Override
    public void onBookDelete(BookFullDto book) {
        Book bookToDelete = new Book(book);

        deleteDisposable = viewModel.deleteBook(bookToDelete)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Toast.makeText(this, "Book deleted successfully", Toast.LENGTH_SHORT).show(),
                        throwable -> Toast.makeText(this, "Could not delete book", Toast.LENGTH_SHORT).show()
                );
    }

    @Override
    protected void onDestroy() {
        if (deleteDisposable != null) {
            deleteDisposable.dispose();
        }

        super.onDestroy();
    }
}
