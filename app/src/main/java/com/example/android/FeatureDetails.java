package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FeatureDetails extends AppCompatActivity {
    private TextView mFeatureObject;
    private TextView mFeatureGeography;
    private TextView mFeatureArtist;
    private TextView mFeatureDesc;
    private TextView mFeatureMaterial;
    private TextView mFeatureSubstrate;
    private TextView mFeatureDate;
    private ImageView mFeatureImage;

    private String object = "";
    private String geography = "";
    private String artist = "";
    private String desc = "";
    private String material = "";
    private String substrate = "";
    private String date = null;
    private String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_details);

        mFeatureObject      = findViewById(R.id.feature_object);
        mFeatureGeography   = findViewById(R.id.feature_geography);
        mFeatureArtist      = findViewById(R.id.feature_artist);
        mFeatureDesc        = findViewById(R.id.feature_desc);
        mFeatureMaterial    = findViewById(R.id.feature_material);
        mFeatureSubstrate   = findViewById(R.id.feature_substrate);
        mFeatureDate        = findViewById(R.id.feature_date);
        mFeatureImage       = findViewById(R.id.feature_image);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("object")) {
                object = intent.getStringExtra("object");
                mFeatureObject.setText(object);
            }
            if (intent.hasExtra("geography")) {
                geography = intent.getStringExtra("geography");
                mFeatureGeography.setText(geography);
            }
            if (intent.hasExtra("artist")) {
                artist = intent.getStringExtra("artist");
                mFeatureArtist.setText("Kunstenaar: " + artist);
            }
            if (intent.hasExtra("image")) {
                image = intent.getStringExtra("image");
                Picasso.get().load(Uri.parse(image)).into(mFeatureImage);
            }
            if (intent.hasExtra("desc")) {
                desc = intent.getStringExtra("desc");
                mFeatureDesc.setText("Beschrijving: " + desc);
            }
            if (intent.hasExtra("material")) {
                material = intent.getStringExtra("material");
                mFeatureMaterial.setText("Materiaal: " + material);
            }
            if (intent.hasExtra("substrate")) {
                substrate = intent.getStringExtra("substrate");
                mFeatureSubstrate.setText("Ondergrond: " + substrate);
            }
            if (intent.hasExtra("date")) {
                date = intent.getStringExtra("date");
                mFeatureDate.setText("Plaatsingdatum: " + date);
            }
        }
    }
}
