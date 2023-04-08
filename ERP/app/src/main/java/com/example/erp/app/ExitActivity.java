package com.example.erp.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        Objects.requireNonNull(getSupportActionBar()).hide();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, 3500);

    }
}