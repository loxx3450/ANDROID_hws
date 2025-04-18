package com.loxx3450.hw_28_03_25;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> imagesIds = new ArrayList<>(List.of(
        R.drawable.foto1,
        R.drawable.foto2,
        R.drawable.foto3,
        R.drawable.foto4
    ));

    private ArrayList<Integer> descriptionsIds = new ArrayList<>(List.of(
        R.string.description1,
        R.string.description2,
        R.string.description3,
        R.string.description4
    ));

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

        Button button = findViewById(R.id.randomButton);
        button.setOnClickListener(this::loadRandomData);
    }

    private void loadRandomData(View view) {
        Random random = new Random();

        int randomPhotoIndex = random.nextInt(imagesIds.size());
        int photoId = imagesIds.get(randomPhotoIndex);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(photoId);

        int randomDescriptionIndex = random.nextInt(descriptionsIds.size());
        int descriptionId = descriptionsIds.get(randomDescriptionIndex);

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(descriptionId);
    }
}