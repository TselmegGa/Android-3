package com.example.android;

import android.view.View;

import com.example.android.model.Feature;

import java.util.ArrayList;

public interface MainOnclickHandler {
    void onActivityClick(View view, int itemIndex, ArrayList<Feature> mFeatures);
}
