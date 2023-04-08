package com.example.erp.mail.received;

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

public class ReceivedActivity extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_received);

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Toast button:
        Button b = findViewById(R.id.clientsButtonModify);

        b.setOnClickListener(view -> Toast.makeText(this, "Use the received mails menu in the upper right corner to use the different options.", Toast.LENGTH_LONG).show());

        // initializing our all variables.
        ArrayList<Received> receivedArrayList;
        DBHandler dbh = new DBHandler(ReceivedActivity.this);
        sp = findViewById(R.id.spinnerReceived);

        // getting our client array
        // list from db handler class.
        receivedArrayList = dbh.readReceived();

        // on below line passing our array list to our adapter class.
        ReceivedRVAdapter receivedRVAdapter = new ReceivedRVAdapter(receivedArrayList, ReceivedActivity.this);
        RecyclerView receivedRV = findViewById(R.id.idRVReceived);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReceivedActivity.this, RecyclerView.VERTICAL, false);
        receivedRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        receivedRV.setAdapter(receivedRVAdapter);

        sp = findViewById(R.id.spinnerReceived);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ReceivedActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Received));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sp.getSelectedItem().toString().toUpperCase()) {
                    case "DELETE...":
                        Intent intentDeleteReceived = new Intent(ReceivedActivity.this, ReceivedActivity.class);
                        Toast.makeText(ReceivedActivity.this, "Click on a received mail to remove it.", Toast.LENGTH_SHORT).show();
                        intentDeleteReceived.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDeleteReceived);
                        break;
                    case "BACK...":
                        Intent intentExitReceived = new Intent(ReceivedActivity.this, LoggedActivity.class);
                        intentExitReceived.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentExitReceived);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


}
