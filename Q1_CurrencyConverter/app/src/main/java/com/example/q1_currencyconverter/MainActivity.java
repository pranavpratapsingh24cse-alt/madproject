package com.example.q1_currencyconverter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.q1_currencyconverter.R;

/* * Q1: Currency Converter (INR, USD, JPY, EUR)
 * Implements conversion logic and basic layout.
 */
public class MainActivity extends AppCompatActivity {
    // Basic exchange rates (relative to 1 USD)
    double USD = 1.0, INR = 95.0, JPY = 150.0, EUR = 0.95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etAmount = findViewById(R.id.etAmount);
        Spinner spinnerFrom = findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = findViewById(R.id.spinnerTo);
        Button btnConvert = findViewById(R.id.btnConvert);
        TextView tvResult = findViewById(R.id.tvResult);

        // Define the currency array
        String[] currencies = {"USD", "INR", "JPY", "EUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        btnConvert.setOnClickListener(v -> {
            String val = etAmount.getText().toString();
            if (!val.isEmpty()) {
                double amount = Double.parseDouble(val);
                String from = spinnerFrom.getSelectedItem().toString();
                String to = spinnerTo.getSelectedItem().toString();

                // Logic: Convert input to USD first, then to target currency
                double inUSD = amount / getRate(from);
                double result = inUSD * getRate(to);

                tvResult.setText(String.format("%.2f %s", result, to));
            }
        });
    }

    private double getRate(String currency) {
        switch (currency) {
            case "INR": return INR;
            case "JPY": return JPY;
            case "EUR": return EUR;
            default: return USD;
        }
    }
}