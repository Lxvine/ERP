package com.example.erp.mail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.mail.received.ReceivedActivity;
import com.example.erp.mail.sent.SentActivity;

import java.util.Objects;

public class MailActivity extends AppCompatActivity {

    Button received, sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        Objects.requireNonNull(getSupportActionBar()).hide();

        received = findViewById(R.id.receivedButton);
        sent = findViewById(R.id.sentButton);

        // Button actions:

        received.setOnClickListener(view -> {
            Intent intent = new Intent(MailActivity.this, ReceivedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        sent.setOnClickListener(view -> {
            Intent intent = new Intent(MailActivity.this, SentActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}