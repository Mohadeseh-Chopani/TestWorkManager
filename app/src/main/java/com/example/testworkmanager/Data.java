package com.example.testworkmanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "log_db")
public class Data {
    @PrimaryKey(autoGenerate = true)
    int id;

    public String getTimeNew() {
        return timeNew;
    }

    public void setTimeNew(String timeNew) {
        this.timeNew = timeNew;
    }

    String timeNew;

    String status;
    java.util.UUID UUID;
    boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public java.util.UUID getUUID() {
        return UUID;
    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
