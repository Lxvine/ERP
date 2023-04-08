package com.example.erp.mail.received;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.app.DBHandler;

import java.util.Objects;

public class UpdateReceivedActivity extends AppCompatActivity {

    String receivedSubject, receivedSender, receivedBody;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_received);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView receivedSubject1 = findViewById(R.id.textViewReceivedSubject);
        TextView receivedSender1 = findViewById(R.id.textViewReceivedSender);
        TextView receivedBody1 = findViewById(R.id.textViewReceivedBody);

        Button backButton = findViewById(R.id.backButtonUpdate);
        Button deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DBHandler(UpdateReceivedActivity.this);

        receivedSubject = getIntent().getStringExtra("received_subject");
        receivedSender = getIntent().getStringExtra("received_sender");
        receivedBody = getIntent().getStringExtra("received_body");

        receivedSubject1.setText(receivedSubject);
        receivedSender1.setText(receivedSender);
        receivedBody1.setText(receivedBody);

        // Below button deletes the pokemon:
        deleteButton.setOnClickListener(view -> {

            dbHandler.deleteReceived(receivedSubject);

            Toast.makeText(UpdateReceivedActivity.this, "Received Mail Deleted...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateReceivedActivity.this, ReceivedActivity.class);
            startActivity(i);
        });

        // Below button goes back:
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(UpdateReceivedActivity.this, ReceivedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
