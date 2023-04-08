package com.example.erp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.erp.accounting.Accounting;
import com.example.erp.client.Client;
import com.example.erp.mail.received.Received;
import com.example.erp.mail.sent.Sent;
import com.example.erp.providers.Provider;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // Creating constant variables for the database:

    // Below variable is for our database name:

    private static final String DB_NAME = "erp";

    // Below variable is for our database version:

    private static final int DB_VERSION = 1;

    // Below variables are for our tables name:

    private static final String ACCOUNTING_TABLE_NAME = "accounting";

    private static final String CLIENTS_TABLE_NAME = "clients";

    private static final String PROVIDERS_TABLE_NAME = "providers";

    private static final String RECEIVED_TABLE_NAME = "received";

    private static final String SENT_TABLE_NAME = "sent";

    // Accounting table:

    private static final String ACCOUNTING_ID_COLUMN = "accounting_id";
    private static final String ACCOUNTING_CODE_COLUMN = "accounting_code";
    private static final String ACCOUNTING_DESCRIPTION_COLUMN = "accounting_description";
    private static final String ACCOUNTING_DEBIT_COLUMN = "accounting_debit";
    private static final String ACCOUNTING_CREDIT_COLUMN = "accounting_credit";

    // Clients table:

    private static final String CLIENT_ID_COLUMN = "client_id";
    private static final String CLIENT_NAME_COLUMN = "client_name";
    private static final String CLIENT_ADDRESS_COLUMN = "client_address";
    private static final String CLIENT_EMAIL_COLUMN = "client_email";
    private static final String CLIENT_SECTOR_COLUMN = "client_sector";


    // Providers table:

    private static final String PROVIDER_ID_COLUMN = "provider_id";
    private static final String PROVIDER_NAME_COLUMN = "provider_name";
    private static final String PROVIDER_ADDRESS_COLUMN = "provider_address";
    private static final String PROVIDER_EMAIL_COLUMN = "provider_email";
    private static final String PROVIDER_SECTOR_COLUMN = "provider_sector";

    // Massive Mailing:

    // Received mail table:

    private static final String RECEIVED_ID_COLUMN = "received_id";
    private static final String RECEIVED_SUBJECT_COLUMN = "received_subject";
    private static final String RECEIVED_SENDER_COLUMN = "received_sender";
    private static final String RECEIVED_BODY_COLUMN = "received_body";

    // Sended mail table:

    private static final String SENT_ID_COLUMN = "sent_id";
    private static final String SENT_SUBJECT_COLUMN = "sent_subject";
    private static final String SENT_RECEIVER_COLUMN = "sent_receiver";
    private static final String SENT_BODY_COLUMN = "sent_body";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Executing the clients table query:
        db.execSQL("CREATE TABLE " + CLIENTS_TABLE_NAME + " ("
                + CLIENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CLIENT_NAME_COLUMN + " TEXT,"
                + CLIENT_ADDRESS_COLUMN + " TEXT,"
                + CLIENT_EMAIL_COLUMN + " TEXT,"
                + CLIENT_SECTOR_COLUMN + " TEXT)");

        // Executing the accounting table query:
        db.execSQL("CREATE TABLE " + ACCOUNTING_TABLE_NAME + " ("
                + ACCOUNTING_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOUNTING_CODE_COLUMN + " TEXT,"
                + ACCOUNTING_DESCRIPTION_COLUMN + " TEXT,"
                + ACCOUNTING_DEBIT_COLUMN + " TEXT,"
                + ACCOUNTING_CREDIT_COLUMN + " TEXT)");

        // Executing the clients table query:
        db.execSQL("CREATE TABLE " + PROVIDERS_TABLE_NAME + " ("
                + PROVIDER_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROVIDER_NAME_COLUMN + " TEXT,"
                + PROVIDER_ADDRESS_COLUMN + " TEXT,"
                + PROVIDER_EMAIL_COLUMN + " TEXT,"
                + PROVIDER_SECTOR_COLUMN + " TEXT)");

        // Executing the received mails table query:
        db.execSQL("CREATE TABLE " + RECEIVED_TABLE_NAME + " ("
                + RECEIVED_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RECEIVED_SUBJECT_COLUMN + " TEXT,"
                + RECEIVED_SENDER_COLUMN + " TEXT,"
                + RECEIVED_BODY_COLUMN + " TEXT)");

        // Executing the sent mails table query:
        db.execSQL("CREATE TABLE " + SENT_TABLE_NAME + " ("
                + SENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SENT_SUBJECT_COLUMN + " TEXT,"
                + SENT_RECEIVER_COLUMN + " TEXT,"
                + SENT_BODY_COLUMN + " TEXT)");
    }

    /*
     * CLIENT
     */


    // Below method is use to add new client to our sqlite database:
    public void addNewClient(Client c) {

        // Creating a variable for our sqlite database and calling writable method as we are writing data in our database:
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating a variable for content values:
        ContentValues cv = new ContentValues();

        // We are passing all values along with its key and value pair:
        cv.put(CLIENT_NAME_COLUMN, c.getName());
        cv.put(CLIENT_ADDRESS_COLUMN, c.getAddress());
        cv.put(CLIENT_EMAIL_COLUMN, c.getEmail());
        cv.put(CLIENT_SECTOR_COLUMN, c.getSector());


        // After adding the values we are passing content values to our table:
        db.insert(CLIENTS_TABLE_NAME, null, cv);

        // At last we are closing our database after adding database:
        db.close();

    }

    // Below is the method for updating the client:
    public void updateClient(String originalClientName, String clientName, String clientAddress, String clientEmail, String clientSector) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CLIENT_NAME_COLUMN, clientName);
        cv.put(CLIENT_ADDRESS_COLUMN, clientAddress);
        cv.put(CLIENT_EMAIL_COLUMN, clientEmail);
        cv.put(CLIENT_SECTOR_COLUMN, clientSector);

        db.update(CLIENTS_TABLE_NAME, cv, "client_name=?", new String[]{originalClientName});
        db.close();
    }

    // Below is the method for deleting the client:
    public void deleteClient(String clientName) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(CLIENTS_TABLE_NAME, "client_name=?", new String[]{clientName});
        db.close();
    }

    // Read Client method:
    public ArrayList<Client> readClient() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorClient = db.rawQuery("SELECT * FROM " + CLIENTS_TABLE_NAME, null);
        ArrayList<Client> clientArrayList = new ArrayList<>();

        if (cursorClient.moveToFirst()) {
            do {
                clientArrayList.add(new Client(cursorClient.getString(1),
                        cursorClient.getString(2),
                        cursorClient.getString(3),
                        cursorClient.getString(4)));
            } while (cursorClient.moveToNext());
        }
        cursorClient.close();
        return clientArrayList;
    }

    /*
     * ACCOUNTING:
     */

    // Below method is use to add new accounting to our sqlite database:
    public void addNewAccounting(Accounting a) {

        // Creating a variable for our sqlite database and calling writable method as we are writing data in our database:
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating a variable for content values:
        ContentValues cv = new ContentValues();

        // We are passing all values along with its key and value pair:
        cv.put(ACCOUNTING_CODE_COLUMN, a.getCode());
        cv.put(ACCOUNTING_DESCRIPTION_COLUMN, a.getDescription());
        cv.put(ACCOUNTING_DEBIT_COLUMN, a.getDebit());
        cv.put(ACCOUNTING_CREDIT_COLUMN, a.getCredit());


        // After adding the values we are passing content values to our table:
        db.insert(ACCOUNTING_TABLE_NAME, null, cv);

        // At last we are closing our database after adding database:
        db.close();

    }

    // Below is the method for updating the accounting:
    public void updateAccounting(String originalAccountingCode, String accountingCode, String accountingDescription, String accountingDebit, String accountinCredit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ACCOUNTING_CODE_COLUMN, accountingCode);
        cv.put(ACCOUNTING_DESCRIPTION_COLUMN, accountingDescription);
        cv.put(ACCOUNTING_DEBIT_COLUMN, accountingDebit);
        cv.put(ACCOUNTING_CREDIT_COLUMN, accountinCredit);

        db.update(ACCOUNTING_TABLE_NAME, cv, "accounting_code=?", new String[]{originalAccountingCode});
        db.close();
    }


    // Below is the method for deleting the accounting:
    public void deleteAccounting(String accountingCode) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(ACCOUNTING_TABLE_NAME, "accounting_code=?", new String[]{accountingCode});
        db.close();
    }

    // Read Accounting method:
    public ArrayList<Accounting> readAccounting() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorAccounting = db.rawQuery("SELECT * FROM " + ACCOUNTING_TABLE_NAME, null);

        ArrayList<Accounting> accountingArrayList = new ArrayList<>();

        if (cursorAccounting.moveToFirst()) {
            do {
                accountingArrayList.add(new Accounting(cursorAccounting.getString(1),
                        cursorAccounting.getString(2),
                        cursorAccounting.getString(3),
                        cursorAccounting.getString(4)));
            } while (cursorAccounting.moveToNext());
        }
        cursorAccounting.close();
        return accountingArrayList;
    }

    /*
     *   PROVIDER
     */

    // Below method is use to add new Provider to our sqlite database:
    public void addNewProvider(Provider p) {

        // Creating a variable for our sqlite database and calling writable method as we are writing data in our database:
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating a variable for content values:
        ContentValues cv = new ContentValues();

        // We are passing all values along with its key and value pair:
        cv.put(PROVIDER_NAME_COLUMN, p.getName());
        cv.put(PROVIDER_ADDRESS_COLUMN, p.getAddress());
        cv.put(PROVIDER_EMAIL_COLUMN, p.getEmail());
        cv.put(PROVIDER_SECTOR_COLUMN, p.getSector());


        // After adding the values we are passing content values to our table:
        db.insert(PROVIDERS_TABLE_NAME, null, cv);

        // At last we are closing our database after adding database:
        db.close();

    }

    // Below is the method for updating the provider:
    public void updateProvider(String originalProviderName, String providerName, String providerAddress, String providerEmail, String providerSector) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PROVIDER_NAME_COLUMN, providerName);
        cv.put(PROVIDER_ADDRESS_COLUMN, providerAddress);
        cv.put(PROVIDER_EMAIL_COLUMN, providerEmail);
        cv.put(PROVIDER_SECTOR_COLUMN, providerSector);

        db.update(PROVIDERS_TABLE_NAME, cv, "provider_name=?", new String[]{originalProviderName});
        db.close();
    }

    // Below is the method for deleting the provider:
    public void deleteProvider(String providerName) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(PROVIDERS_TABLE_NAME, "provider_name=?", new String[]{providerName});
        db.close();
    }

    // Read Client method:
    public ArrayList<Provider> readProvider() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorProvider = db.rawQuery("SELECT * FROM " + PROVIDERS_TABLE_NAME, null);
        ArrayList<Provider> providerArrayList = new ArrayList<>();

        if (cursorProvider.moveToFirst()) {
            do {
                providerArrayList.add(new Provider(cursorProvider.getString(1),
                        cursorProvider.getString(2),
                        cursorProvider.getString(3),
                        cursorProvider.getString(4)));
            } while (cursorProvider.moveToNext());
        }
        cursorProvider.close();
        return providerArrayList;
    }

    /*
     * RECEIVED MAIL
     */

    // Below method is use to add new Received Mail to our sqlite database:
    public void addNewReceived(Received r) {

        // Creating a variable for our sqlite database and calling writable method as we are writing data in our database:
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating a variable for content values:
        ContentValues cv = new ContentValues();

        // We are passing all values along with its key and value pair:
        cv.put(RECEIVED_SUBJECT_COLUMN, r.getSubject());
        cv.put(RECEIVED_SENDER_COLUMN, r.getSender());
        cv.put(RECEIVED_BODY_COLUMN, r.getBody());


        // After adding the values we are passing content values to our table:
        db.insert(RECEIVED_TABLE_NAME, null, cv);

        // At last we are closing our database after adding database:
        db.close();

    }

    // Below is the method for deleting the Received Mail:
    public void deleteReceived(String receivedSubject) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(RECEIVED_TABLE_NAME, "received_subject=?", new String[]{receivedSubject});
        db.close();
    }

    // Read Received Mail method:
    public ArrayList<Received> readReceived() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorReceived = db.rawQuery("SELECT * FROM " + RECEIVED_TABLE_NAME, null);
        ArrayList<Received> receivedArrayList = new ArrayList<>();

        if (cursorReceived.moveToFirst()) {
            do {
                receivedArrayList.add(new Received(cursorReceived.getString(1),
                        cursorReceived.getString(2),
                        cursorReceived.getString(3)));
            } while (cursorReceived.moveToNext());
        }
        cursorReceived.close();
        return receivedArrayList;
    }


    /*
     * SENT MAIL
     */

    // Below method is use to add new Sent Mail to our sqlite database:
    public void addNewSent(Sent s) {

        // Creating a variable for our sqlite database and calling writable method as we are writing data in our database:
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating a variable for content values:
        ContentValues cv = new ContentValues();

        // We are passing all values along with its key and value pair:
        cv.put(SENT_SUBJECT_COLUMN, s.getSubject());
        cv.put(SENT_RECEIVER_COLUMN, s.getReceiver());
        cv.put(SENT_BODY_COLUMN, s.getBody());


        // After adding the values we are passing content values to our table:
        db.insert(SENT_TABLE_NAME, null, cv);

        // At last we are closing our database after adding database:
        db.close();

    }

    // Below is the method for deleting the Sended Mail:
    public void deleteSent(String sentSubject) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(SENT_TABLE_NAME, "sent_subject=?", new String[]{sentSubject});
        db.close();
    }

    // Read Received Mail method:
    public ArrayList<Sent> readSent() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorSent = db.rawQuery("SELECT * FROM " + SENT_TABLE_NAME, null);
        ArrayList<Sent> sentArrayList = new ArrayList<>();

        if (cursorSent.moveToFirst()) {
            do {
                sentArrayList.add(new Sent(cursorSent.getString(1),
                        cursorSent.getString(2),
                        cursorSent.getString(3)));
            } while (cursorSent.moveToNext());
        }
        cursorSent.close();
        return sentArrayList;
    }


    // onUpgrade method:

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // Clients table on upgrade:

        db.execSQL("DROP TABLE IF EXISTS " + CLIENTS_TABLE_NAME);
        onCreate(db);

        // Accounting table on upgrade:

        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTING_TABLE_NAME);
        onCreate(db);

        // Providers table on upgrade:

        db.execSQL("DROP TABLE IF EXISTS " + PROVIDERS_TABLE_NAME);
        onCreate(db);

        // Received mails table on upgrade:

        db.execSQL("DROP TABLE IF EXISTS " + RECEIVED_TABLE_NAME);
        onCreate(db);

        // Sent mails table on upgrade:

        db.execSQL("DROP TABLE IF EXISTS " + SENT_TABLE_NAME);
        onCreate(db);

    }
}
