package com.example.testworkmanager;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    long addData(Data data);

    @Query("SELECT * FROM log_db")
    List<Data> getData();
}
