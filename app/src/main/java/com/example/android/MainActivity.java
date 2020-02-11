package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.android.model.Feature;
import com.example.android.tasks.GetItemAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainOnclickHandler {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //obtain a handle to the object
        mRecyclerView = findViewById(R.id.recycler_view_activity_list);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //connect it to a layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        GetItemAsyncTask activityAsyncTask = new GetItemAsyncTask(MainActivity.this);
        activityAsyncTask.execute();

    }
    public void linkAdapter(List<Feature> features) {
        // when task is running, adapter will set the data
        mAdapter = new MainRecycleViewAdapter(features, this);
        //connect it to the recyclerView
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityClick(View view, int itemIndex) {

    }
}
