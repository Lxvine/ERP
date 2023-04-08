package com.example.erp.accounting;

import androidx.annotation.NonNull;

public class Accounting {

    // Attributes Accounting class:

    public int id;
    public String code, description, debit, credit;

    // Constructor Accounting class:

    public Accounting(String code, String description, String debit, String credit) {
        this.code = code;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
    }

    // Accounting class Getters and Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDebit() {
        return debit;
    }

    public String getCredit() {
        return credit;
    }


    // toString Client class:


    @NonNull
    @Override
    public String toString() {
        return "Accounting{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", debit='" + debit + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
