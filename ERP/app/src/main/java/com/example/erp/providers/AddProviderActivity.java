package com.example.erp.providers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.app.DBHandler;

import java.util.ArrayList;
import java.util.Objects;

public class AddProviderActivity extends AppCompatActivity {

    private ArrayList<Provider> providerArrayList;
    private EditText editTextEditProviderName, editTextEditProviderAddress, editTextEditProviderEmail, editTextEditProviderSector;
    private DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        Objects.requireNonNull(getSupportActionBar()).hide();

        providerArrayList = new ArrayList<>();
        dbh = new DBHandler(AddProviderActivity.this);
        providerArrayList = dbh.readProvider();

        editTextEditProviderName = findViewById(R.id.idEdtProviderName);
        editTextEditProviderAddress = findViewById(R.id.idEdtProviderAddress);
        editTextEditProviderEmail = findViewById(R.id.idEdtProviderEmail);
        editTextEditProviderSector = findViewById(R.id.idEdtProviderSector);

        Button add = findViewById(R.id.addButton);
        Button back = findViewById(R.id.backButton);

        // Below button adds a new provider:
        add.setOnClickListener(view -> {

            String providerName = editTextEditProviderName.getText().toString();
            String providerAddress = editTextEditProviderAddress.getText().toString();
            String providerEmail = editTextEditProviderEmail.getText().toString();
            String providerSector = editTextEditProviderSector.getText().toString();

            boolean repe = false;


            for (int i = 0; i < providerArrayList.size(); i++) {
                if (providerName.equalsIgnoreCase(providerArrayList.get(i).getName())) {
                    repe = true;
                }

            }

            if (repe) {
                Toast.makeText(AddProviderActivity.this, "The provider already exists in the database.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                if (providerName.isEmpty() || providerAddress.isEmpty() || providerEmail.isEmpty() || providerSector.isEmpty()) {
                    Toast.makeText(AddProviderActivity.this, "You can't add the provider if there is any empty field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Provider p = new Provider(providerName, providerAddress, providerEmail, providerSector);
                dbh.addNewProvider(p);
                Toast.makeText(AddProviderActivity.this, "The provider has been added.", Toast.LENGTH_SHORT).show();
                editTextEditProviderName.setText("");
                editTextEditProviderAddress.setText("");
                editTextEditProviderEmail.setText("");
                editTextEditProviderSector.setText("");
            }

        });

        // Below button goes back:
        back.setOnClickListener(view -> {
            Intent intent = new Intent(AddProviderActivity.this, ProviderActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}