package com.example.erp.accounting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.app.DBHandler;

import java.util.Objects;

public class UpdateAccountingActivity extends AppCompatActivity {

    String accountingCode, accountingDescription, accountingDebit, accountingCredit;
    private EditText editTextEditAccountingCode, editTextEditAccountingDescription, editTextEditAccountingDebit, editTextEditAccountingCredit;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_accounting);

        Objects.requireNonNull(getSupportActionBar()).hide();

        editTextEditAccountingCode = findViewById(R.id.idEdtAccountingCode);
        editTextEditAccountingDescription = findViewById(R.id.idEdtAccountingDescription);
        editTextEditAccountingDebit = findViewById(R.id.idEdtAccountingDebit);
        editTextEditAccountingCredit = findViewById(R.id.idEdtAccountingCredit);


        Button updateButton = findViewById(R.id.updateButton);
        Button backButton = findViewById(R.id.backButtonUpdate);
        Button deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DBHandler(UpdateAccountingActivity.this);

        accountingCode = getIntent().getStringExtra("accounting_code");
        accountingDescription = getIntent().getStringExtra("accounting_description");
        accountingDebit = getIntent().getStringExtra("accounting_debit");
        accountingCredit = getIntent().getStringExtra("accounting_credit");

        editTextEditAccountingCode.setText(accountingCode);
        editTextEditAccountingDescription.setText(accountingDescription);
        editTextEditAccountingDebit.setText(accountingDebit);
        editTextEditAccountingCredit.setText(accountingCredit);


        // Below button updates the pokemon:
        updateButton.setOnClickListener(v -> {

            dbHandler.updateAccounting(accountingCode, editTextEditAccountingCode.getText().toString(), editTextEditAccountingDescription.getText().toString(), editTextEditAccountingDebit.getText().toString(), editTextEditAccountingCredit.getText().toString());

            Toast.makeText(UpdateAccountingActivity.this, "Accounting movement updated...", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(UpdateAccountingActivity.this, AccountingActivity.class);
            startActivity(i);
        });

        // Below button deletes the pokemon:
        deleteButton.setOnClickListener(view -> {

            dbHandler.deleteAccounting(accountingCode);

            Toast.makeText(UpdateAccountingActivity.this, "Accounting movement deleted...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateAccountingActivity.this, AccountingActivity.class);
            startActivity(i);
        });

        // Below button goes back:
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(UpdateAccountingActivity.this, AccountingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}
