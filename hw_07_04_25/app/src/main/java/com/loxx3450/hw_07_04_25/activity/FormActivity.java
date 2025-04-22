package com.loxx3450.hw_07_04_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loxx3450.hw_07_04_25.R;
import com.loxx3450.hw_07_04_25.model.Contact;
import com.loxx3450.hw_07_04_25.repository.ContactRepository;

public class FormActivity extends AppCompatActivity {
    private ContactRepository repository;

    private int id = -1;

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.form), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        repository = new ContactRepository(getApplicationContext());

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);

        loadSentDataIfExists(getIntent());

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> handleSubmit());

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> returnToMainActivity());
    }

    private void loadSentDataIfExists(Intent intent) {
        if (intent.hasExtra("contact_data")) {
            Contact contact = intent.getParcelableExtra("contact_data");

            id = contact.getId();
            nameEditText.setText(contact.getName());
            phoneEditText.setText(contact.getPhone());
            emailEditText.setText(contact.getEmail());
        }
    }

    private void handleSubmit() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String email = emailEditText.getText().toString();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Values cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        Contact contact = new Contact(id, name, phone, email);

        if (id == -1) {
            repository.add(contact);
        } else {
            repository.update(contact);
        }

        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void returnToMainActivity() {
        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
