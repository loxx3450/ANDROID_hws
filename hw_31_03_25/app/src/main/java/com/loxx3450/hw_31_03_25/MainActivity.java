package com.loxx3450.hw_31_03_25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_31_03_25.adapter.ContactAdapter;
import com.loxx3450.hw_31_03_25.model.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private ContactAdapter adapter;

    private final ActivityResultLauncher<Intent> createNewLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();

                    String name = data.getStringExtra("name");
                    String phone = data.getStringExtra("phone");

                    contacts.add(new Contact(name, phone));
                    adapter.notifyItemInserted(contacts.size() - 1);
                }
            }
    );

    private final ActivityResultLauncher<Intent> updateLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();

                    int position = data.getIntExtra("position", -1);
                    String name = data.getStringExtra("name");
                    String phone = data.getStringExtra("phone");

                    contacts.set(position, new Contact(name, phone));
                    adapter.notifyItemChanged(position);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>(getInitialData());
        adapter = new ContactAdapter(contacts, updateLauncher);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        Button createButton = findViewById(R.id.createNewButton);
        createButton.setOnClickListener(this::openSecondActivity);
    }

    private void openSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        createNewLauncher.launch(intent);
    }

    private List<Contact> getInitialData() {
        return Arrays.asList(
                new Contact("Alice", "+1234567890"),
                new Contact("Bob", "+1987654321"),
                new Contact("Charlie", "+1122334455"),
                new Contact("Diana", "+1098765432"),
                new Contact("Ethan", "+1222333444")
        );
    }
}