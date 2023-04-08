package com.example.erp.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    Button darkMode, size, lowSpecs, colorblind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Objects.requireNonNull(getSupportActionBar()).hide();

        darkMode = findViewById(R.id.darkModeButton);
        size = findViewById(R.id.sizeButton);
        lowSpecs = findViewById(R.id.lowSpecsButton);
        colorblind = findViewById(R.id.colorblindButton);

        // Button actions:

        darkMode.setOnClickListener(view -> Toast.makeText(this, "Dark mode menu options will work in future versions.", Toast.LENGTH_SHORT).show());

        size.setOnClickListener(view -> Toast.makeText(this, "Size menu options will work in future versions.", Toast.LENGTH_SHORT).show());

        lowSpecs.setOnClickListener(view -> Toast.makeText(this, "Low specs menu options will work in future versions.", Toast.LENGTH_SHORT).show());

        colorblind.setOnClickListener(view -> Toast.makeText(this, "Colorblind menu options will work in future versions.", Toast.LENGTH_SHORT).show());


    }
}