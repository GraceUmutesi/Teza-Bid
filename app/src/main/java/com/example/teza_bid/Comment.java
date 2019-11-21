package com.example.teza_bid;

import com.google.firebase.database.ServerValue;

public class Comment {

    private String comment;
    private Object name;
    private String key;

    public Comment(){}

    public Comment(String comment, Object name) {
        this.comment = comment;
        this.name = name;
        this.key = key;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toString(){
        return "Comment{"+
                "comment'"+comment+'\''+
                "name ='"+ name + '\''+
                ", key='" + key + '\'' +
                '}';
    }
}
