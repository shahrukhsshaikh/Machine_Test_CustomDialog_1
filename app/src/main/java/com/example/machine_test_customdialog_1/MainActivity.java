package com.example.machine_test_customdialog_1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText ;
    Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextAmount);
        Button buttonConvert = findViewById(R.id.btnConvert);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversionDialog();
            }
        });
    }

    private void showConversionDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.currency_exchange, null);
        builder.setView(dialogView);

        TextView textViewConvertedAmount = dialogView.findViewById(R.id.textViewConvertedAmount);
        RadioGroup radioGroupCurrencies = dialogView.findViewById(R.id.radioGroupCurrencies);
        RadioButton radioButton1= findViewById(R.id.radioButtonCAN);
        RadioButton radioButton2 = findViewById(R.id.radioButtonUSA);
        RadioButton radioButton3 = findViewById(R.id.radioButtonUK);
        RadioButton radioButton4 = findViewById(R.id.radioButtonCHI);


        radioGroupCurrencies.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String amountStr = editText.getText().toString();
                if (amountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);
                double convertedAmount = 0;

                if (radioButton1 == radioButton1) {
                    convertedAmount = amount * 1.25;
                } else if (radioButton2 == radioButton2) {
                    convertedAmount = amount * 1.1;
                } else if (radioButton3 == radioButton3) {
                    convertedAmount = amount * 0.75;
                } else if (radioButton4 == radioButton4) {
                    convertedAmount = amount * 6.5;
                }
                textViewConvertedAmount.setText("Converted Amount: " + convertedAmount);
                Toast.makeText(MainActivity.this, "Conversion completed!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setTitle("Select Currency")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}