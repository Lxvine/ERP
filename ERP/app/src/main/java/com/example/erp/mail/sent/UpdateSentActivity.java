package com.example.erp.mail.sent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.app.DBHandler;

import java.util.Objects;

public class UpdateSentActivity extends AppCompatActivity {

    String sentSubject, sentReceiver, sentBody;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sent);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView sentSubject1 = findViewById(R.id.textViewUpdateSentSubject);
        TextView sentSender1 = findViewById(R.id.textViewUpdateSentSender);
        TextView sentBody1 = findViewById(R.id.textViewUpdateSentBody);

        Button backButton = findViewById(R.id.backButtonUpdate);
        Button deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DBHandler(UpdateSentActivity.this);

        sentSubject = getIntent().getStringExtra("sent_subject");
        sentReceiver = getIntent().getStringExtra("sent_receiver");
        sentBody = getIntent().getStringExtra("sent_body");

        sentSubject1.setText(sentSubject);
        sentSender1.setText(sentReceiver);
        sentBody1.setText(sentBody);

        // Below button deletes the pokemon:
        deleteButton.setOnClickListener(view -> {

            dbHandler.deleteSent(sentSubject);

            Toast.makeText(UpdateSentActivity.this, "Sent Mail Deleted...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateSentActivity.this, SentActivity.class);
            startActivity(i);
        });

        // Below button goes back:
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(UpdateSentActivity.this, SentActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}