package com.example.erp.providers;

import androidx.annotation.NonNull;

public class Provider {

    // Attributes Provider class:

    public int id;
    public String name, address, email, sector;

    // Constructor Provider class:

    public Provider(String name, String address, String email, String sector) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.sector = sector;
    }

    // Provider class Getters and Setters:

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

    // toString Provider class:

    @NonNull
    @Override
    public String toString() {
        return "Provider{" +
                "provider_id=" + id +
                ", provider_name='" + name + '\'' +
                ", provider_address='" + address + '\'' +
                ", provider_email='" + email + '\'' +
                ", provider_sector='" + sector + '\'' +
                '}';
    }
}