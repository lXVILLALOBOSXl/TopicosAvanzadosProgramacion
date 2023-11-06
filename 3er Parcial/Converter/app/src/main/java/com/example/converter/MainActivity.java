package com.example.converter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText amountEditText;
    Spinner fromCurrencySpinner;
    Spinner toCurrencySpinner;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition("MXN");
        toCurrencySpinner.setSelection(defaultPosition);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        double amount = 0.0;

        try {
            amount = Double.parseDouble(amountEditText.getText().toString());
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            e.printStackTrace();
        }

        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        double convertedAmount = performCurrencyConversion(amount, fromCurrency, toCurrency);

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("convertedAmount", convertedAmount);

        // Determine the conversion direction and pass it to ResultActivity
        if (fromCurrency.equals("USD") && toCurrency.equals("MXN")) {
            intent.putExtra("conversionDirection", "USD_TO_MXN");
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("USD")) {
            intent.putExtra("conversionDirection", "MXN_TO_USD");
        }else if (fromCurrency.equals("USD") && toCurrency.equals("USD")) {
            intent.putExtra("conversionDirection", "USD_TO_USD");
        }else{
            intent.putExtra("conversionDirection", "MXN_TO_MXN");
        }

        startActivity(intent);
    }

    private double performCurrencyConversion(double amount, String fromCurrency, String toCurrency) {

        double usdToMxnRate = 17.55;
        double mxnToUsdRate = 0.057;

        if (fromCurrency.equals("USD") && toCurrency.equals("MXN")) {
            return amount * usdToMxnRate;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("USD")) {
            return amount * mxnToUsdRate;
        }else {
            return amount;
        }

    }
}

