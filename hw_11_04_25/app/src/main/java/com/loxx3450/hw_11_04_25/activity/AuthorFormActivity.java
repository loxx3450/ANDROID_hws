package com.loxx3450.hw_11_04_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.loxx3450.hw_11_04_25.App;
import com.loxx3450.hw_11_04_25.R;
import com.loxx3450.hw_11_04_25.db.AppDatabase;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.viewModel.AuthorFormViewModel;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthorFormActivity extends AppCompatActivity {

    // UI elements
    private EditText nameEditText;
    private EditText surnameEditText;

    // VM
    private AuthorFormViewModel viewModel;

    // Disposable
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_author_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.authorForm), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(AuthorFormViewModel.class);

        // Get UI
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        Button submitButton = findViewById(R.id.submitButton);
        Button returnButton = findViewById(R.id.returnButton);

        // Callbacks
        submitButton.setOnClickListener(v -> createNewAuthor());
        returnButton.setOnClickListener(v -> openAuthorsListActivity());
    }

    private void createNewAuthor() {
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();

        if (name.isEmpty() || surname.isEmpty()) {
            Toast.makeText(this, "Values cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Author author = new Author(name, surname);

        disposable = viewModel.insertAuthor(author)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::openAuthorsListActivity,
                        throwable -> showError());
    }

    private void openAuthorsListActivity() {
        Intent intent = new Intent(AuthorFormActivity.this, AuthorsListActivity.class);
        startActivity(intent);
    }

    private void showError() {
        Toast.makeText(this, "Something went wrong! Try again later...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();

        super.onDestroy();
    }
}
