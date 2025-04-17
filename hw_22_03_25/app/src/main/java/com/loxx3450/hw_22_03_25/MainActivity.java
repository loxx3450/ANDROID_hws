package com.loxx3450.hw_22_03_25;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Task 5
        setContentView(R.layout.activity_main);
        var button = findViewById(R.id.calculateButton);
        button.setOnClickListener(this::calculateFullPrice);

        // Task 3
//        setContentView(R.layout.activity_second);
//        var button = findViewById(R.id.calculateInterest);
//        button.setOnClickListener(this::calculateInterest);
    }

    private void calculateFullPrice(View view) {
        EditText distanceInput = findViewById(R.id.distanceInput);
        EditText consumptionInput = findViewById(R.id.consumptionInput);
        EditText priceInput = findViewById(R.id.priceInput);

        float distance = Float.parseFloat(distanceInput.getText().toString());
        float consumption = Float.parseFloat(consumptionInput.getText().toString());
        float pricePerL = Float.parseFloat(priceInput.getText().toString());

        float liters = distance / 100 * consumption;
        float fullPrice = liters * pricePerL;

        TextView litersCountTextView = findViewById(R.id.litersCountTextView);
        litersCountTextView.setText(String.format("%.2f", liters));

        TextView fullPriceTextView = findViewById(R.id.finalPriceTextView);
        fullPriceTextView.setText(String.format("%.2f", fullPrice));
    }

    private void calculateInterest(View view) {
        EditText depositInput = findViewById(R.id.depositInput);
        EditText rateInput = findViewById(R.id.rateInput);
        EditText yearsInput = findViewById(R.id.termInput);

        float deposit = Float.parseFloat(depositInput.getText().toString());
        float rate = Float.parseFloat(rateInput.getText().toString());
        int years = Integer.parseInt(yearsInput.getText().toString());

        float newDeposit = deposit;

        for (int i = 0; i < years; ++i) {
            newDeposit *= (1 + rate / 100);
        }

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(String.format("%.2f", newDeposit));
    }
}