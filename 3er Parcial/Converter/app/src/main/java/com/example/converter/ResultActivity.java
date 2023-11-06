package com.example.converter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultTextView = findViewById(R.id.resultTextView);
        Button backButton = findViewById(R.id.backButton);

        double convertedAmount = getIntent().getDoubleExtra("convertedAmount", 0.00);

        // Get the conversion direction from the intent
        String conversionDirection = getIntent().getStringExtra("conversionDirection");

        // Set the text accordingly based on the conversion direction
        if (conversionDirection.equals("USD_TO_MXN") || conversionDirection.equals("MXN_TO_MXN")) {
            resultTextView.setText("Converted Amount: " + convertedAmount + " MXN");
        } else if (conversionDirection.equals("MXN_TO_USD") || conversionDirection.equals("USD_TO_USD")) {
            resultTextView.setText("Converted Amount: " + convertedAmount + " USD");
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and return to the previous one (MainActivity)
            }
        });
    }
}


