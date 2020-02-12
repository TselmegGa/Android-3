package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("stone", true);
        edit.putBoolean("metal", true);
        edit.putBoolean("glass", true);
        edit.putBoolean("bronze", true);
        edit.putBoolean("iron", true);
        edit.apply();

        //obtain a handle to the object
        mRecyclerView = findViewById(R.id.recycler_view_activity_list);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //connect it to a layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Init features array
        ArrayList<Feature> mFeatures = getFeatures();

        Toast.makeText(this, "Aantal opgehaalde items: " + mFeatures.size(), Toast.LENGTH_LONG).show();

        // specify an adapter
        setAdapter(mFeatures);
    }
    private void setAdapter(ArrayList<Feature> mFeatures){
        mAdapter = new MainRecycleViewAdapter(mFeatures, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Feature> getFeatures(){
        ArrayList<Feature> features = new ArrayList<>();

        try {
            features = new GetItemAsyncTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return features;
    }

    private ArrayList<Feature> getPrefFeatures(ArrayList<Feature> features){
        String material = null;
        ArrayList<Feature> prefFeatures = new ArrayList<>();
        for (Feature item: features) {
            Log.v("",item.getMaterial());
            if (item.getMaterial().contains("Bronze")|| item.getMaterial().contains("bronze") || item.getMaterial().contains("Brons")|| item.getMaterial().contains("brons")){
                material = "bronze";
            }
            else if (item.getMaterial().contains("Steen")|| item.getMaterial().contains("steen")){
                material = "stone";
            }
            else if (item.getMaterial().contains("Glas")|| item.getMaterial().contains("glas")){
                material = "glass";
            }
            else if (item.getMaterial().contains("Metaal")|| item.getMaterial().contains("metaal")){
                material = "metal";
            }
            else if (item.getMaterial().contains("Ijzer")|| item.getMaterial().contains("ijzer")){
                material = "iron";
            }
            if(sharedPreferences.getBoolean(material,false)){
                prefFeatures.add(item);
            }

        }

        return prefFeatures;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_material, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.material_stone:
                if(item.isChecked()){
                    item.setChecked(false);
                    edit.putBoolean("stone", false);
                }
                else {
                    item.setChecked(true);
                    edit.putBoolean("stone", true);
                }
                edit.apply();
                break;
            case R.id.material_metal:
                if(item.isChecked()){
                    item.setChecked(false);
                    edit.putBoolean("metal", false);
                }
                else {
                    item.setChecked(true);
                    edit.putBoolean("metal", true);
                }
                edit.apply();
                break;
            case R.id.material_glass:
                if(item.isChecked()){
                    item.setChecked(false);
                    edit.putBoolean("glass", false);
                }
                else {
                    item.setChecked(true);
                    edit.putBoolean("glass", true);
                }
                edit.apply();
                break;
            case R.id.material_bronze:
                if(item.isChecked()){
                    item.setChecked(false);
                    edit.putBoolean("bronze", false);
                }
                else {
                    item.setChecked(true);
                    edit.putBoolean("bronze", true);
                }
                edit.apply();
                break;
            case R.id.material_iron:
                if(item.isChecked()){
                    item.setChecked(false);
                    edit.putBoolean("iron", false);
                }
                else {
                    item.setChecked(true);
                    edit.putBoolean("iron", true);
                }
                edit.apply();
                break;
        }
        setAdapter(getPrefFeatures(getFeatures()));
        return true;
    }
}
