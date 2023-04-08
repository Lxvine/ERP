package com.example.erp.mail.sent;

import androidx.annotation.NonNull;

public class Sent {

    // Attributes Sended class:

    public int id;
    public String subject, receiver, body;

    // Constructor Sended class:

    public Sent(String subject, String receiver, String body) {
        this.subject = subject;
        this.receiver = receiver;
        this.body = body;
    }

    // Sended class Getters and Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getBody() {
        return body;
    }


    // toString Sended class:

    @NonNull
    @Override
    public String toString() {
        return "Sended{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", receiver='" + receiver + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

