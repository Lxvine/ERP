package com.example.erp.client;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.app.DBHandler;

import java.util.Objects;

public class UpdateClientActivity extends AppCompatActivity {


    String clientName, clientAddress, clientEmail, clientSector;
    private EditText editTextEditClientName, editTextEditClientAddress, editTextEditClientEmail, editTextEditClientSector;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);

        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextEditClientName = findViewById(R.id.idEdtClientName);
        editTextEditClientAddress = findViewById(R.id.idEdtClientAddress);
        editTextEditClientEmail = findViewById(R.id.idEdtClientEmail);
        editTextEditClientSector = findViewById(R.id.idEdtClientSector);


        Button updateButton = findViewById(R.id.updateButton);
        Button backButton = findViewById(R.id.backButtonUpdate);
        Button deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DBHandler(UpdateClientActivity.this);

        clientName = getIntent().getStringExtra("client_name");
        clientAddress = getIntent().getStringExtra("client_address");
        clientEmail = getIntent().getStringExtra("client_email");
        clientSector = getIntent().getStringExtra("client_sector");

        editTextEditClientName.setText(clientName);
        editTextEditClientAddress.setText(clientAddress);
        editTextEditClientEmail.setText(clientEmail);
        editTextEditClientSector.setText(clientSector);


        // Below button updates the pokemon:
        updateButton.setOnClickListener(v -> {

            dbHandler.updateClient(clientName, editTextEditClientName.getText().toString(), editTextEditClientAddress.getText().toString(), editTextEditClientEmail.getText().toString(), editTextEditClientSector.getText().toString());

            Toast.makeText(UpdateClientActivity.this, "Client updated...", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(UpdateClientActivity.this, ClientActivity.class);
            startActivity(i);
        });

        // Below button deletes the pokemon:
        deleteButton.setOnClickListener(view -> {

            dbHandler.deleteClient(clientName);

            Toast.makeText(UpdateClientActivity.this, "Client Deleted...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateClientActivity.this, ClientActivity.class);
            startActivity(i);
        });

        // Below button goes back:
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(UpdateClientActivity.this, ClientActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}
