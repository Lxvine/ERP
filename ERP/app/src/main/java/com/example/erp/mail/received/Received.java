package com.example.erp.mail.received;

import androidx.annotation.NonNull;

public class Received {

    // Attributes Received class:

    public int id;
    public String subject;
    public String sender;
    public String body;

    // Constructor Received class:

    public Received(String subject, String sender, String body) {
        this.subject = subject;
        this.sender = sender;
        this.body = body;
    }

    // Received class Getters and Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }


    // toString Received class:

    @NonNull
    @Override
    public String toString() {
        return "Received{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
