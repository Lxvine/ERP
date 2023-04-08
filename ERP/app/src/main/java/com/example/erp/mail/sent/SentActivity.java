package com.example.erp.mail.sent;

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

public class SentActivity extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sent);

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Toast button:
        Button b = findViewById(R.id.clientsButtonModify);

        b.setOnClickListener(view -> Toast.makeText(this, "Use the sended mails menu in the upper right corner to use the different options.", Toast.LENGTH_LONG).show());

        // initializing our all variables.
        ArrayList<Sent> sendedArrayList;
        DBHandler dbh = new DBHandler(SentActivity.this);
        sp = findViewById(R.id.spinnerSent);

        // getting our client array
        // list from db handler class.
        sendedArrayList = dbh.readSent();

        // on below line passing our array list to our adapter class.
        SentRVAdapter sendedRVAdapter = new SentRVAdapter(sendedArrayList, SentActivity.this);
        RecyclerView sendedRV = findViewById(R.id.idRVSent);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SentActivity.this, RecyclerView.VERTICAL, false);
        sendedRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        sendedRV.setAdapter(sendedRVAdapter);

        sp = findViewById(R.id.spinnerSent);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SentActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Sended));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sp.getSelectedItem().toString().toUpperCase()) {
                    case "SEND...":
                        Intent intentAddSended = new Intent(SentActivity.this, AddSentActivity.class);
                        intentAddSended.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentAddSended);
                        break;
                    case "DELETE...":
                        Intent intentDeleteSended = new Intent(SentActivity.this, SentActivity.class);
                        Toast.makeText(SentActivity.this, "Click on a sended mail to remove it.", Toast.LENGTH_SHORT).show();
                        intentDeleteSended.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDeleteSended);
                        break;
                    case "BACK...":
                        Intent intentExitSended = new Intent(SentActivity.this, LoggedActivity.class);
                        intentExitSended.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentExitSended);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

}