package com.example.testworkmanager;

import static android.app.PendingIntent.getActivity;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.WorkInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Work extends Worker {

    Context context;
    WorkerParameters workerParameters;
    Dao dao;
   static String status;
   static boolean isFinished = false;

   WorkInfo workInfo ;
    public Work(@NonNull Context context, @NonNull WorkerParameters workerParams,WorkInfo workInfo) {
        super(context, workerParams);
        this.context = context;
        this.workerParameters=workerParams;
        this.workInfo = workInfo;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: this work manager is running ");
        return Result.success();
    }
}
