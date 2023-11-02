package com.example.testworkmanager;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;

import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements Adapter.Listener {

    RecyclerView rvData;
    Dao dao;
    Adapter adapter;
    static WorkInfo Info;
    PeriodicWorkRequest workRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = DatabaseHolder.getDatabaseHolder(this).getDao();

        rvData = findViewById(R.id.rv_data);

        rvData.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new Adapter(this);
        rvData.setAdapter(adapter);

        workRequest = new PeriodicWorkRequest.Builder(Work.class, 1, TimeUnit.MINUTES)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .build();

        String uniqueWorkName = "myUniqueWork";
        ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.REPLACE;

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                uniqueWorkName,
                existingPeriodicWorkPolicy,
                workRequest
        );
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Info = workInfo;
                Log.d(TAG, "onChanged: success " + Info.getId());
                dao.getData().observe(MainActivity.this, new Observer<List<Data>>() {
                    @Override
                    public void onChanged(List<Data> dataList) {
                        adapter.deleteItem();
                        adapter.addInformation(dataList);
                        rvData.smoothScrollToPosition(0);
                        rvData.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    public void cancelWork(Data data, String status) {
        if (Objects.equals(status, "ENQUEUED")) {
            WorkManager.getInstance(this).cancelWorkById(data.getUUID());
            data.setStatus("CANCELLED");
        } else if (Objects.equals(status, "CANCELLED")) {
            WorkManager.getInstance(this).enqueue(workRequest);
            data.setStatus("ENQUEUED");
        }
        int result = dao.updateData(data);
        if (result > 0) {
            adapter.updateData(data);
        }
    }
}