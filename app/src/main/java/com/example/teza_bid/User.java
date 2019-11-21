package com.example.teza_bid;

import androidx.annotation.NonNull;

class User {
    String name;
    String uid;
    String email;

    public User() {
    }

    public User(String name, String uid, String email) {
        this.name = name;
        this.uid = uid;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User("+
                "uid='"+uid+'\''+
                ", name='"+name+'\''+
                ", email='"+email +'\''+
                ")";
    }
}
