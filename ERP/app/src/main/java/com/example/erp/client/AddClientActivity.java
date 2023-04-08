package com.example.erp.client;

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

public class AddClientActivity extends AppCompatActivity {

    private ArrayList<Client> clientsArrayList;
    private EditText editTextEditClientName, editTextEditClientAddress, editTextEditClientEmail, editTextEditClientSector;
    private DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        Objects.requireNonNull(getSupportActionBar()).hide();

        clientsArrayList = new ArrayList<>();
        dbh = new DBHandler(AddClientActivity.this);
        clientsArrayList = dbh.readClient();

        editTextEditClientName = findViewById(R.id.idEdtClientName);
        editTextEditClientAddress = findViewById(R.id.idEdtClientAddress);
        editTextEditClientEmail = findViewById(R.id.idEdtClientEmail);
        editTextEditClientSector = findViewById(R.id.idEdtClientSector);

        Button add = findViewById(R.id.addButton);
        Button back = findViewById(R.id.backButton);

        // Below button adds a new client:
        add.setOnClickListener(view -> {

            String clientName = editTextEditClientName.getText().toString();
            String clientAddress = editTextEditClientAddress.getText().toString();
            String clientEmail = editTextEditClientEmail.getText().toString();
            String clientSector = editTextEditClientSector.getText().toString();

            boolean repe = false;


            for (int i = 0; i < clientsArrayList.size(); i++) {
                if (clientName.equalsIgnoreCase(clientsArrayList.get(i).getName())) {
                    repe = true;
                }

            }

            if (repe) {
                Toast.makeText(AddClientActivity.this, "The client already exists in the database.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                if (clientName.isEmpty() || clientAddress.isEmpty() || clientEmail.isEmpty() || clientSector.isEmpty()) {
                    Toast.makeText(AddClientActivity.this, "You can't add the client if there is any empty field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Client c = new Client(clientName, clientAddress, clientEmail, clientSector);
                dbh.addNewClient(c);
                Toast.makeText(AddClientActivity.this, "The client has been added.", Toast.LENGTH_SHORT).show();
                editTextEditClientName.setText("");
                editTextEditClientAddress.setText("");
                editTextEditClientEmail.setText("");
                editTextEditClientSector.setText("");
            }

        });

        // Below button goes back:
        back.setOnClickListener(view -> {
            Intent intent = new Intent(AddClientActivity.this, ClientActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}
