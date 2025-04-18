package com.loxx3450.hw_31_03_25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

public class SecondActivity extends AppCompatActivity {
    private Optional<Integer> position = Optional.empty();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            TextView nameInput = findViewById(R.id.nameInput);
            TextView phoneInput = findViewById(R.id.phoneInput);

            nameInput.setText(arguments.getString("name"));
            phoneInput.setText(arguments.getString("phone"));

            position = Optional.of(arguments.getInt("position"));
        }

        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        Intent intent = new Intent();

        TextView nameInput = findViewById(R.id.nameInput);
        TextView phoneInput = findViewById(R.id.phoneInput);

        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();

        intent.putExtra("name", name);
        intent.putExtra("phone", phone);

        if (position.isPresent()) {
            intent.putExtra("position", position.get());
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}
