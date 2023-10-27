package com.example.testworkmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(version = 1 , exportSchema = false , entities = {Data.class})
public abstract class DatabaseHolder extends RoomDatabase {

    private static DatabaseHolder databaseHolder;

    public static DatabaseHolder getDatabaseHolder(Context context){
        if(databaseHolder==null){
            databaseHolder= Room.databaseBuilder(context.getApplicationContext(),DatabaseHolder.class,"log_db").
                    allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseHolder;
    }
    public abstract Dao getDao();

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
