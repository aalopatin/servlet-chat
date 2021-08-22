package com.example.ServletChat.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

public class Message {
    private Timestamp datetime;
    private String user;
    private String message;

    public Message(Timestamp datetime, String user, String message) {
        this.datetime = datetime;
        this.user = user;
        this.message = message;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

}
