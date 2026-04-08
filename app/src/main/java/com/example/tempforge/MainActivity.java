package com.example.tempforge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etTemperature;
    private RadioGroup radioGroup;
    private Button btnConvert;
    private TextView tvResult;

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

        etTemperature = findViewById(R.id.etTemperature);
        radioGroup = findViewById(R.id.radioGroup);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempString = etTemperature.getText().toString();

                if (tempString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Введіть значення температури!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double temperature = Double.parseDouble(tempString);
                double result = 0.0;
                String resultText = "";

                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == R.id.rbCelsiusToFahrenheit) {
                    result = (temperature * 9 / 5) + 32;
                    resultText = result + " F";
                } else if (selectedId == R.id.rbFahrenheitToCelsius) {
                    result = (temperature - 32) * 5 / 9;
                    resultText = result + " C";
                }

                tvResult.setText(resultText);
            }
        });
    }
}