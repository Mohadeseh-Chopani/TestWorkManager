package com.example.testworkmanager;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvData;
    Dao dao;
    Adapter adapter;
    String status;
    static Data data = new Data();
    List<Data> list = new ArrayList<Data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = DatabaseHolder.getDatabaseHolder(this).getDao();

        rvData = findViewById(R.id.rv_data);

        rvData.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new Adapter();
        rvData.setAdapter(adapter);

        list = dao.getData();
        adapter.addInformations(list);


        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(Work.class, 1, TimeUnit.MINUTES)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .build();


        WorkManager.getInstance(this).enqueue(workRequest);


        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                data.setUUID(workRequest.getId());
                data.setStatus(workInfo.getState().name());
                data.setFinished(workInfo.getState().isFinished());
                data.setTimeNew(format.format(new Date(System.currentTimeMillis())));
                dao.addData(data);

                Log.d(TAG, "onChanged: success ");
            }
        });
    }
}