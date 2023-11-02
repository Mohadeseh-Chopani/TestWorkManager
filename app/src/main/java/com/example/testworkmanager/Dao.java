package com.example.testworkmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    long addData(Data data);

    @Query("SELECT * FROM log_db")
    LiveData<List<Data>> getData();

    @Update
    int updateData(Data data);
}
