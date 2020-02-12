package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.model.Feature;
import com.example.android.tasks.GetItemAsyncTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

        //Init features array
        ArrayList<Feature> mFeatures = new ArrayList<>();

        //Get features
        try {
            mFeatures = new GetItemAsyncTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Aantal opgehaalde items: " + mFeatures.size(), Toast.LENGTH_LONG).show();

//        Collections.sort(mFeatures);

        // specify an adapter
        mAdapter = new MainRecycleViewAdapter(mFeatures, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityClick(View view, int itemIndex, ArrayList<Feature> mFeatures) {
        Intent intent = new Intent(getApplicationContext(), FeatureDetails.class);
        intent.putExtra("object", mFeatures.get(itemIndex).getObject());
        intent.putExtra("geography", mFeatures.get(itemIndex).getGeography());
        intent.putExtra("artist", mFeatures.get(itemIndex).getArtist());
        intent.putExtra("desc", mFeatures.get(itemIndex).getDesc());
        intent.putExtra("material", mFeatures.get(itemIndex).getMaterial());
        intent.putExtra("substrate", mFeatures.get(itemIndex).getSubstrate());
        intent.putExtra("date", mFeatures.get(itemIndex).getDate());
        intent.putExtra("image", mFeatures.get(itemIndex).getImageUrl());

        Log.d(this.getClass().getSimpleName(), "Starting intent: " + FeatureDetails.class.getSimpleName());
        startActivity(intent);
    }
}
