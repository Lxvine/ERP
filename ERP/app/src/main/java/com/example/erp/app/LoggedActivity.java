package com.example.erp.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erp.R;
import com.example.erp.accounting.Accounting;
import com.example.erp.accounting.AccountingActivity;
import com.example.erp.client.Client;
import com.example.erp.client.ClientActivity;
import com.example.erp.hr.HRActivity;
import com.example.erp.mail.MailActivity;
import com.example.erp.mail.received.Received;
import com.example.erp.mail.sent.Sent;
import com.example.erp.providers.Provider;
import com.example.erp.providers.ProviderActivity;
import com.example.erp.settings.SettingsActivity;

import java.util.Objects;

public class LoggedActivity extends AppCompatActivity {

    Button accounting, clients, providers, mail, hr, settings, logout, exit;
    private DBHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        // Remove the appbar:

        Objects.requireNonNull(getSupportActionBar()).hide();

        getSharedPreferences("Users", Context.MODE_PRIVATE);
        accounting = findViewById(R.id.accountingButton);
        clients = findViewById(R.id.clientsButton);
        providers = findViewById(R.id.providersButton);
        mail = findViewById(R.id.mailButton);
        hr = findViewById(R.id.hrButton);
        settings = findViewById(R.id.settingsButton);
        logout = findViewById(R.id.loginButton);
        exit = findViewById(R.id.exitButton);

        //Instance the dbhandler:
        dbh = new DBHandler(LoggedActivity.this);

        // Calling the method that introduce the default clients:
        startClientList();

        // Calling the method that introduce the default accounting moves:
        startAccountingList();

        // Calling the method that introduce the default providers:
        startProvidersList();

        // Calling the method that introduce the default received mails:
        startReceivedList();

        // Calling the method that introduce the default sent mails:
        startSendedList();

        // Button actions:

        accounting.setOnClickListener(view -> {
            Intent intent = new Intent(LoggedActivity.this, AccountingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Accounting menu...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        clients.setOnClickListener(view -> {
            Intent intent = new Intent(LoggedActivity.this, ClientActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Clients menu...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        providers.setOnClickListener(view -> {
            Intent intent = new Intent(LoggedActivity.this, ProviderActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Providers menu...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        mail.setOnClickListener(view -> {
            Intent intent = new Intent(LoggedActivity.this, MailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Massive mailing menu...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        hr.setOnClickListener(view -> {
            Intent intent = new Intent(LoggedActivity.this, HRActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Human Resources menu...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(LoggedActivity.this, SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "App settings menu...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {

            new AlertDialog.Builder(LoggedActivity.this)
                    .setIcon(R.drawable.eexit)
                    .setTitle("Logout...")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("YES!", (dialogInterface, which) -> {
                        Intent intent = new Intent(LoggedActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Toast.makeText(this, "Hello there!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    })
                    .setNegativeButton("NO!", null)
                    .show();


        });

        exit.setOnClickListener(view -> {

            new AlertDialog.Builder(LoggedActivity.this)
                    .setIcon(R.drawable.eexit)
                    .setTitle("Exit...")
                    .setMessage("Are you sure you want to quit?")
                    .setPositiveButton("YES!", (dialogInterface, which) -> {
                        Intent intent = new Intent(LoggedActivity.this, ExitActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Toast.makeText(this, "Hope to see you again!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("NO!", null)
                    .show();

        });

    }

    // Below method starts clients list:

    @SuppressLint("ApplySharedPref")
    private void startClientList() {
        SharedPreferences sp = getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        boolean start = sp.getBoolean("startClients", false);

        if (!start) {

            editor.putBoolean("startClients", true);
            editor.commit();

            Client c = new Client("Gymcrash", "Calle Venezuela 13, Bajo B, 36554, Vigo, Pontevedra", "gymcrash@gymcrash.com", "Servicios");
            dbh.addNewClient(c);

            c = new Client("Omarchu", "Calle Auricular 5, Local A, 31234, Pontevedra, Pontevedra", "omarchu@omarchu.es", "Tecnología");
            dbh.addNewClient(c);

            c = new Client("Boxba", "Calle Ahsoka 23, 1º C, 33245, Silleda, Pontevedra", "boxba@boxba.es", "Alimentación");
            dbh.addNewClient(c);

            c = new Client("Apple", "Calle Funk 17, 2º A, 37511, Milladoiro, A Coruña", "apple@apple.com", "Automoción");
            dbh.addNewClient(c);

        }

    }

    // Below method starts accounting list:

    @SuppressLint("ApplySharedPref")
    private void startAccountingList() {
        SharedPreferences sp = getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        boolean start = sp.getBoolean("startAccounting", false);

        if (!start) {

            editor.putBoolean("startAccounting", true);
            editor.commit();

            Accounting a = new Accounting("123", "Nomina Marzo 2023", "1234 €", "4321 €");
            dbh.addNewAccounting(a);

            a = new Accounting("34", "Factura Luz Febrero", "300 €", "0 €");
            dbh.addNewAccounting(a);

            a = new Accounting("545", "Entrada capital", "0 €", "1235 €");
            dbh.addNewAccounting(a);

            a = new Accounting("76", "Alquiler motocicleta xg312", "1 €", "1 €");
            dbh.addNewAccounting(a);

        }

    }

    // Below method starts providers list:

    @SuppressLint("ApplySharedPref")
    private void startProvidersList() {
        SharedPreferences sp = getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        boolean start = sp.getBoolean("startProviders", false);

        if (!start) {

            editor.putBoolean("startProviders", true);
            editor.commit();

            Provider p = new Provider("FCBarcelona", "Camp now, Barcelona, 34513, Barcelona", "fcb@fcb.com", "Servicios");
            dbh.addNewProvider(p);

            p = new Provider("Hollow", "Calle Romualdo 12, Bajo A, 37123, Oleiros, A Coruña", "hollow@hollow.es", "Alimentación");
            dbh.addNewProvider(p);

            p = new Provider("Knight", "Siador 7, Melón, 13344, Ourense ", "knight@knight.es", "Transporters");
            dbh.addNewProvider(p);

            p = new Provider("Creatinne", "Estrada Aveiro 14, Culleredo, 34353, Coruña", "creatinne@creatinne.com", "Tecnología");
            dbh.addNewProvider(p);

        }

    }

    // Below method starts received mails list:

    @SuppressLint("ApplySharedPref")
    private void startReceivedList() {
        SharedPreferences sp = getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        boolean start = sp.getBoolean("startReceived", false);

        if (!start) {

            editor.putBoolean("startReceived", true);
            editor.commit();

            Received r = new Received("Factura ordenador", "pccomponentes@gmail.com", "Enviamos factura ordenador");
            dbh.addNewReceived(r);

            r = new Received("Entradas RC Celta - Valladolid", "iagoaspas@iagoaspas.com", "Adjunto 14 entradas para el partido");
            dbh.addNewReceived(r);

            r = new Received("FCBarcelona fichajes top secret", "florentinoperez@gmail.com", "no hay dinero");
            dbh.addNewReceived(r);

            r = new Received("Nomina Juan Lopez", "consultoralamela@lamela.com", "Nomina correcta procedemos a archivarla, un saludo");
            dbh.addNewReceived(r);

        }

    }

    // Below method starts received mails list:

    @SuppressLint("ApplySharedPref")
    private void startSendedList() {
        SharedPreferences sp = getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        boolean start = sp.getBoolean("startSended", false);

        if (!start) {

            editor.putBoolean("startSended", true);
            editor.commit();

            Sent s = new Sent("Problema contabilidad marzo", "cantinglass@gmail.es", "Ha habido un problema, contacte con nosotros cuanto antes");
            dbh.addNewSent(s);

            s = new Sent("Compra de un ordenador nuevo", "pccomponentes@outlook.es", "Ordenador de la nasa");
            dbh.addNewSent(s);

            s = new Sent("Nomina de Juan Lopez", "abogados@abogados.com", "Adjuntamos copia de la nomina de abril de Juan Lopez");
            dbh.addNewSent(s);

            s = new Sent("Problema en la nomina de Juan Lopez", "consultoralamela@lamela.com", "Encontramos un error en la nomina, adjuntamos la nueva corregida");
            dbh.addNewSent(s);

        }

    }
}