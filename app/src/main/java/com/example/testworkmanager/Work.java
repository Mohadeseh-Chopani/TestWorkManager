package com.example.testworkmanager;

import static android.app.PendingIntent.getActivity;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.WorkInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Work extends Worker {
    Dao dao;
    Context context;
    WorkerParameters workerParameters;

    public Work(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        this.workerParameters = workerParams;
    }

    @NonNull
    @Override
    public Result doWork() {

        Data data = new Data();
        dao = DatabaseHolder.getDatabaseHolder(context).getDao();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        data.setUUID(MainActivity.Info.getId());
        data.setStatus(MainActivity.Info.getState().name());
        data.setFinished(MainActivity.Info.getState().isFinished());
        data.setTimeNew(format.format(new Date(System.currentTimeMillis())));
        dao.addData(data);
        Log.d(TAG, "doWork: this work manager is running "+MainActivity.Info.getId());
        return Result.success();
    }
}
