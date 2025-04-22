package com.loxx3450.hw_07_04_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loxx3450.hw_07_04_25.R;
import com.loxx3450.hw_07_04_25.adapter.ContactAdapter;
import com.loxx3450.hw_07_04_25.model.Contact;
import com.loxx3450.hw_07_04_25.repository.ContactRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactClickListener {

    private ContactRepository repository;
    private ContactAdapter adapter;
    private List<Contact> contacts;

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

        repository = new ContactRepository(getApplicationContext());

        ListView listView = findViewById(R.id.listView);

        contacts = repository.getAll();
        adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);

        Button createNewButton = findViewById(R.id.createButton);
        createNewButton.setOnClickListener(v -> openFormActivityWithoutExtra());
    }

    private void openFormActivityWithoutExtra() {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        startActivity(intent);
    }

    @Override
    public void onContactClick(Contact contact) {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra("contact_data", contact);

        startActivity(intent);
    }

    @Override
    public void onContactDelete(Contact contact) {
        repository.delete(contact.getId());

        contacts.removeIf(c -> c.getId() == contact.getId());
        adapter.notifyDataSetChanged();
    }
}