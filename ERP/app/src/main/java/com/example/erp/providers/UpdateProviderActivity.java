package com.example.erp.providers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.app.DBHandler;

import java.util.Objects;

public class UpdateProviderActivity extends AppCompatActivity {


    String providerName, providerAddress, providerEmail, providerSector;
    private EditText editTextEditProviderName, editTextEditProviderAddress, editTextEditProviderEmail, editTextEditProviderSector;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_provider);

        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextEditProviderName = findViewById(R.id.idEdtProviderName);
        editTextEditProviderAddress = findViewById(R.id.idEdtProviderAddress);
        editTextEditProviderEmail = findViewById(R.id.idEdtProviderEmail);
        editTextEditProviderSector = findViewById(R.id.idEdtProviderSector);

        Button updateButton = findViewById(R.id.updateButton);
        Button backButton = findViewById(R.id.backButtonUpdate);
        Button deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DBHandler(UpdateProviderActivity.this);

        providerName = getIntent().getStringExtra("provider_name");
        providerAddress = getIntent().getStringExtra("provider_address");
        providerEmail = getIntent().getStringExtra("provider_email");
        providerSector = getIntent().getStringExtra("provider_sector");

        editTextEditProviderName.setText(providerName);
        editTextEditProviderAddress.setText(providerAddress);
        editTextEditProviderEmail.setText(providerEmail);
        editTextEditProviderSector.setText(providerSector);


        // Below button updates the pokemon:
        updateButton.setOnClickListener(v -> {

            dbHandler.updateProvider(providerName, editTextEditProviderName.getText().toString(), editTextEditProviderAddress.getText().toString(), editTextEditProviderEmail.getText().toString(), editTextEditProviderSector.getText().toString());

            Toast.makeText(UpdateProviderActivity.this, "Provider updated...", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(UpdateProviderActivity.this, ProviderActivity.class);
            startActivity(i);
        });

        // Below button deletes the pokemon:
        deleteButton.setOnClickListener(view -> {

            dbHandler.deleteProvider(providerName);

            Toast.makeText(UpdateProviderActivity.this, "Provider Deleted...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateProviderActivity.this, ProviderActivity.class);
            startActivity(i);
        });

        // Below button goes back:
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(UpdateProviderActivity.this, ProviderActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}