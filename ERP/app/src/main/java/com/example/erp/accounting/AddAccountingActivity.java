package com.example.erp.accounting;

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

public class AddAccountingActivity extends AppCompatActivity {

    private ArrayList<Accounting> accountingArrayList;
    private EditText editTextEditAccountingCode, editTextEditAccountingDescription, editTextEditAccountingDebit, editTextEditAccountingCredit;
    private DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounting);

        Objects.requireNonNull(getSupportActionBar()).hide();

        accountingArrayList = new ArrayList<>();
        dbh = new DBHandler(AddAccountingActivity.this);
        accountingArrayList = dbh.readAccounting();

        editTextEditAccountingCode = findViewById(R.id.idEdtAccountingCode);
        editTextEditAccountingDescription = findViewById(R.id.idEdtAccountingDescription);
        editTextEditAccountingDebit = findViewById(R.id.idEdtAccountingDebit);
        editTextEditAccountingCredit = findViewById(R.id.idEdtAccountingCredit);

        Button add = findViewById(R.id.addButton);
        Button back = findViewById(R.id.backButton);

        // Below button adds a new accounting move:
        add.setOnClickListener(view -> {

            String accountingCode = editTextEditAccountingCode.getText().toString();
            String accountingDescription = editTextEditAccountingDescription.getText().toString();
            String accountingDebit = editTextEditAccountingDebit.getText().toString();
            String accountingCredit = editTextEditAccountingCredit.getText().toString();

            boolean repe = false;


            for (int i = 0; i < accountingArrayList.size(); i++) {
                if (accountingCode.equalsIgnoreCase(accountingArrayList.get(i).getCode())) {
                    repe = true;
                }

            }

            if (repe) {
                Toast.makeText(AddAccountingActivity.this, "The accounting movement already exists in the database.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                if (accountingCode.isEmpty() || accountingDescription.isEmpty() || accountingDebit.isEmpty() || accountingCredit.isEmpty()) {
                    Toast.makeText(AddAccountingActivity.this, "You can't add the accounting movement if there is any empty field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Accounting a = new Accounting(accountingCode, accountingDescription, accountingDebit, accountingCredit);
                dbh.addNewAccounting(a);
                Toast.makeText(AddAccountingActivity.this, "The accounting movement has been added.", Toast.LENGTH_SHORT).show();
                editTextEditAccountingCode.setText("");
                editTextEditAccountingDescription.setText("");
                editTextEditAccountingDebit.setText("");
                editTextEditAccountingCredit.setText("");
            }

        });

        // Below button goes back:
        back.setOnClickListener(view -> {
            Intent intent = new Intent(AddAccountingActivity.this, AccountingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}
