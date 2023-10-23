package com.example.testworkmanager;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.provider.UserDictionary;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManager extends Worker {

    public WorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: this work manager is running ");
        return Result.success();
    }
}
