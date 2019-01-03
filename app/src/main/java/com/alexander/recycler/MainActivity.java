package com.alexander.recycler;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private LinearLayoutManager manager;
    private List<BaseItem> workers;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();

        workers = new ArrayList<>();
        workers.addAll(WorkerGenerator.generateWorkers(5));


        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new CustomAdapter(workers);
        recyclerView.setAdapter(adapter);
    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);
    }

    private void initListeners(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workers = new ArrayList<>(workers);
                workers.addAll(WorkerGenerator.generateWorkers(5));
                adapter.onNewData(workers);
            }
        });
    }
}
