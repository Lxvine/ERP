package com.example.erp.hr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;

import java.util.Objects;

public class HRActivity extends AppCompatActivity {

    Button recruitment, training, org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hractivity);

        Objects.requireNonNull(getSupportActionBar()).hide();

        recruitment = findViewById(R.id.recruitmentButton);
        training = findViewById(R.id.trainingButton);
        org = findViewById(R.id.orgButton);

        // Button actions:

        recruitment.setOnClickListener(view -> Toast.makeText(this, "Recruitment section will work in future versions.", Toast.LENGTH_SHORT).show());

        training.setOnClickListener(view -> Toast.makeText(this, "Training section will work in future versions.", Toast.LENGTH_SHORT).show());

        org.setOnClickListener(view -> Toast.makeText(this, "Organization chart section will work in future versions.", Toast.LENGTH_SHORT).show());


    }
}