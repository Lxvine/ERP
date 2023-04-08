package com.example.erp.providers;

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

public class ProviderActivity extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_provider);

        // Remove the appbar:

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Toast button:
        Button b = findViewById(R.id.providersButtonModify);

        b.setOnClickListener(view -> Toast.makeText(this, "Use the providers menu in the upper right corner to use the different options.", Toast.LENGTH_LONG).show());

        // initializing our all variables.
        ArrayList<Provider> providerArrayList;
        DBHandler dbh = new DBHandler(ProviderActivity.this);
        sp = findViewById(R.id.spinnerProvider);

        // getting our provider array
        // list from db handler class.
        providerArrayList = dbh.readProvider();

        // on below line passing our array list to our adapter class.
        ProviderRVAdapter providerRVAdapter = new ProviderRVAdapter(providerArrayList, ProviderActivity.this);
        RecyclerView providerRV = findViewById(R.id.idRVProvider);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProviderActivity.this, RecyclerView.VERTICAL, false);
        providerRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        providerRV.setAdapter(providerRVAdapter);

        sp = findViewById(R.id.spinnerProvider);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProviderActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sp.getSelectedItem().toString().toUpperCase()) {
                    case "ADD...":
                        Intent intentAddAppointment = new Intent(ProviderActivity.this, AddProviderActivity.class);
                        intentAddAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentAddAppointment);
                        break;
                    case "DELETE...":
                        Intent intentDeleteAppointment = new Intent(ProviderActivity.this, ProviderActivity.class);
                        Toast.makeText(ProviderActivity.this, "Click on a provider to modify it or to remove it.", Toast.LENGTH_SHORT).show();
                        intentDeleteAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDeleteAppointment);
                        break;
                    case "BACK...":
                        Intent intentExitAppointment = new Intent(ProviderActivity.this, LoggedActivity.class);
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