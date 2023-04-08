package com.example.erp.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erp.R;
import com.example.erp.app.DBHandler;
import com.example.erp.app.LoggedActivity;

import java.util.ArrayList;
import java.util.Objects;

public class ClientActivity extends AppCompatActivity {


    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clients);

        // Remove the appbar:

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Toast button:
        Button b = findViewById(R.id.clientsButtonModify);

        b.setOnClickListener(view -> Toast.makeText(this, "Use the client menu in the upper right corner to use the different options.", Toast.LENGTH_LONG).show());

        // initializing our all variables.
        ArrayList<Client> clientArrayList;
        DBHandler dbh = new DBHandler(ClientActivity.this);
        sp = findViewById(R.id.spinnerClients);

        // getting our client array
        // list from db handler class.
        clientArrayList = dbh.readClient();

        // on below line passing our array list to our adapter class.
        ClientRVAdapter clientRVAdapter = new ClientRVAdapter(clientArrayList, ClientActivity.this);
        RecyclerView clientRV = findViewById(R.id.idRVClient);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClientActivity.this, RecyclerView.VERTICAL, false);
        clientRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        clientRV.setAdapter(clientRVAdapter);

        sp = findViewById(R.id.spinnerClients);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ClientActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sp.getSelectedItem().toString().toUpperCase()) {
                    case "ADD...":
                        Intent intentAddAppointment = new Intent(ClientActivity.this, AddClientActivity.class);
                        intentAddAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentAddAppointment);
                        break;
                    case "DELETE...":
                        Intent intentDeleteAppointment = new Intent(ClientActivity.this, ClientActivity.class);
                        Toast.makeText(ClientActivity.this, "Click on a client to modify it or to remove it.", Toast.LENGTH_SHORT).show();
                        intentDeleteAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDeleteAppointment);
                        break;
                    case "BACK...":
                        Intent intentExitAppointment = new Intent(ClientActivity.this, LoggedActivity.class);
                        intentExitAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentExitAppointment);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

}