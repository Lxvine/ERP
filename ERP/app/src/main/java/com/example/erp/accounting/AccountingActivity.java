package com.example.erp.accounting;

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

public class AccountingActivity extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounting);

        // Remove the appbar:

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Toast button:
        Button b = findViewById(R.id.accountingButtonModify);

        b.setOnClickListener(view -> Toast.makeText(this, "Use the accounting menu in the upper right corner to use the different options.", Toast.LENGTH_LONG).show());

        // initializing our all variables.
        ArrayList<Accounting> accountingArrayList;
        DBHandler dbh = new DBHandler(AccountingActivity.this);
        sp = findViewById(R.id.spinnerAccounting);

        // getting our accounting array
        // list from db handler class.
        accountingArrayList = dbh.readAccounting();

        // on below line passing our array list to our adapter class.
        AccountingRVAdapter accountingRVAdapter = new AccountingRVAdapter(accountingArrayList, AccountingActivity.this);
        RecyclerView accountingRV = findViewById(R.id.idRVAccounting);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AccountingActivity.this, RecyclerView.VERTICAL, false);
        accountingRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        accountingRV.setAdapter(accountingRVAdapter);

        sp = findViewById(R.id.spinnerAccounting);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AccountingActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Options));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (sp.getSelectedItem().toString().toUpperCase()) {
                    case "ADD...":
                        Intent intentAddAppointment = new Intent(AccountingActivity.this, AddAccountingActivity.class);
                        intentAddAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentAddAppointment);
                        break;
                    case "DELETE...":
                        Intent intentDeleteAppointment = new Intent(AccountingActivity.this, AccountingActivity.class);
                        Toast.makeText(AccountingActivity.this, "Click on a accounting to modify it or to remove it.", Toast.LENGTH_SHORT).show();
                        intentDeleteAppointment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDeleteAppointment);
                        break;
                    case "BACK...":
                        Intent intentExitAppointment = new Intent(AccountingActivity.this, LoggedActivity.class);
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