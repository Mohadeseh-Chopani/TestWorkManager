package com.example.testworkmanager;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkManager.class).build();
        androidx.work.WorkManager.getInstance(this).enqueue(workRequest);

        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(WorkManager.class,1,TimeUnit.SECONDS).build();
        androidx.work.WorkManager.getInstance(this).enqueue(request);

        androidx.work.WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                .observe(this,new Observer<WorkInfo>(){
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if( workInfo.getState() == WorkInfo.State.SUCCEEDED)
                        {
                            Log.i(TAG, "onChanged: Success");
                        }
                    }
                });
    }
}