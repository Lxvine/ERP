package com.example.erp.client;

import androidx.annotation.NonNull;

public class Client {

    // Attributes Client class:

    public int id;
    public String name, address, email, sector;

    // Constructor Client class:

    public Client(String name, String address, String email, String sector) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.sector = sector;
    }

    // Client class Getters and Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getSector() {
        return sector;
    }

    // toString Client class:

    @NonNull
    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + id +
                ", client_name='" + name + '\'' +
                ", client_address='" + address + '\'' +
                ", client_email='" + email + '\'' +
                ", client_sector='" + sector + '\'' +
                '}';
    }
}