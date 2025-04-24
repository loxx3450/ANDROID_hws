package com.loxx3450.hw_11_04_25.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loxx3450.hw_11_04_25.R;

public class MainActivity extends AppCompatActivity {

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

        // Get UI elements
        AppCompatButton authorsButton = findViewById(R.id.authorsButton);
        AppCompatButton booksButton = findViewById(R.id.booksButton);

        // Callbacks
        authorsButton.setOnClickListener(v -> openAuthorsListActivity());
        booksButton.setOnClickListener(v -> openBooksListActivity());
    }

    private void openAuthorsListActivity() {
        Intent intent = new Intent(MainActivity.this, AuthorsListActivity.class);
        startActivity(intent);
    }

    private void openBooksListActivity() {
        Intent intent = new Intent(MainActivity.this, BooksListActivity.class);
        startActivity(intent);
    }
}