package com.example.erp.mail.sent;

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

public class AddSentActivity extends AppCompatActivity {

    private ArrayList<Sent> sentArrayList;
    private EditText editTextEditSentSubject, editTextEditSentSender, editTextEditSentBody;
    private DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sent);

        Objects.requireNonNull(getSupportActionBar()).hide();

        sentArrayList = new ArrayList<>();
        dbh = new DBHandler(AddSentActivity.this);
        sentArrayList = dbh.readSent();

        editTextEditSentSubject = findViewById(R.id.idEdtSentSubject);
        editTextEditSentSender = findViewById(R.id.idEdtSentSender);
        editTextEditSentBody = findViewById(R.id.idEdtSentBody);

        Button add = findViewById(R.id.addButton);
        Button back = findViewById(R.id.backButton);

        // Below button adds a new client:
        add.setOnClickListener(view -> {

            String sentSubject = editTextEditSentSubject.getText().toString();
            String sentSender = editTextEditSentSender.getText().toString();
            String sentBody = editTextEditSentBody.getText().toString();

            boolean repe = false;


            for (int i = 0; i < sentArrayList.size(); i++) {
                if (sentSubject.equalsIgnoreCase(sentArrayList.get(i).getSubject())) {
                    repe = true;
                }

            }

            if (repe) {
                Toast.makeText(AddSentActivity.this, "The mail is already sent.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                if (sentSubject.isEmpty() || sentSender.isEmpty() || sentBody.isEmpty()) {
                    Toast.makeText(AddSentActivity.this, "You can't send an email if there is any empty field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Sent s = new Sent(sentSubject, sentSender, sentBody);
                dbh.addNewSent(s);
                Toast.makeText(AddSentActivity.this, "The mail has been sent.", Toast.LENGTH_SHORT).show();
                editTextEditSentSubject.setText("");
                editTextEditSentSender.setText("");
                editTextEditSentBody.setText("");
            }

        });

        // Below button goes back:
        back.setOnClickListener(view -> {
            Intent intent = new Intent(AddSentActivity.this, SentActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}