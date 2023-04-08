package com.example.erp.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    boolean empty = true;
    // Variables declaration:
    private EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Remove the appbar:

        Objects.requireNonNull(getSupportActionBar()).hide();

        // Welcome toast:

        Toast.makeText(this, "If you are not signed up, write it anyway and the app will make it for you.", Toast.LENGTH_LONG).show();


        // Giving values to the variables:

        user = findViewById(R.id.editTextUser);
        password = findViewById(R.id.editTextPassword);
        ImageView iv = findViewById(R.id.loginImageView);
        Button loginButton = findViewById(R.id.loginButton);

        // ImageView easter egg toast:

        iv.setOnClickListener(view -> Toast.makeText(this, "The name of the app is the name of the fictitious EIE company that I made this year.", Toast.LENGTH_LONG).show());

        // Button action:

        loginButton.setOnClickListener(view -> {

            // User exists?

            if (check()) {

                // If it exists, it throws a toast that says welcome and goes to the next activity.

                Toast.makeText(LoginActivity.this, "Welcome " + user.getText() + ".", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, LoggedActivity.class);
                intent.putExtra("User", Objects.requireNonNull(user.getText()).toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {

                // If it does not exist, a toast is thrown that says that the user has been registered and the method that adds the user in SharedPreferences is called.:

                writeUser();
            }
        });

    }

    @SuppressLint("ApplySharedPref")
    public void writeUser() {

        // Collect entered data:
        String userAux = Objects.requireNonNull(user.getText()).toString();
        String passwordAux = password.getText().toString();

        // SharePreferences "Users":
        SharedPreferences pref = getSharedPreferences("Users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        // Users and their keys are introduced in the strings:
        editor.putString("User" + userAux, userAux);
        editor.putString("Password" + userAux, passwordAux);
        editor.commit();

        if (!empty) {
            Toast.makeText(LoginActivity.this, "User has been registered.", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean check() {

        // Collect entered data (Checking):
        String userAux = Objects.requireNonNull(user.getText()).toString();
        String passwordAux = password.getText().toString();
        String valueUser;
        String valuePass;

        // SharePreferences "Users" (Checking):
        SharedPreferences prefs = getSharedPreferences("Users", Context.MODE_PRIVATE);

        if (userAux.isEmpty() || passwordAux.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Mmmm... Empty fields.", Toast.LENGTH_SHORT).show();
            return false;
        }

        empty = false;

        // Users and their keys are introduced in the strings (Checking):
        valueUser = prefs.getString("User" + userAux, "Default");
        valuePass = prefs.getString("Password" + userAux, "Default");

        return userAux.equals(valueUser) && passwordAux.equals(valuePass);
    }


}