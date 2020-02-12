package com.example.android.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.MainActivity;
import com.example.android.model.Feature;
import com.example.android.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetItemAsyncTask extends AsyncTask<Void, Void, ArrayList<Feature>> {
    private JSONArray array;

    @Override
    protected ArrayList<Feature> doInBackground(Void... str) {
        ArrayList<Feature> features = new ArrayList<>();

        URL url = NetworkUtils.buildUrl();
        String response = null;

        try {
            response = NetworkUtils.sendGET(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            if(response != null) {
                JSONObject json = new JSONObject(response);
                array = json.getJSONArray("features");
            } else
                throw new RuntimeException("Response is null.");

            for (int i=0; i < array.length(); i++) {
                JSONObject jArray = array.getJSONObject(i);
                JSONObject featureJson = jArray.getJSONObject("attributes");

                int id = 0;
                String identification = "";
                String object = "";
                String geography = "";
                String artist = "";
                String material = "";
                String desc = "";
                String substrate = "";
                String urlObject = "";

                if (featureJson.has("OBJECTID")) {
                    id = featureJson.getInt("OBJECTID");
                }

                if (featureJson.has("IDENTIFICATIE")) {
                    identification = featureJson.getString("IDENTIFICATIE");
                }

                if (featureJson.has("AANDUIDINGOBJECT")) {
                    object = featureJson.getString("AANDUIDINGOBJECT");
                }

                if (featureJson.has("GEOGRAFISCHELIGGING")) {
                    geography = featureJson.getString("GEOGRAFISCHELIGGING");
                }

                if (featureJson.has("KUNSTENAAR")) {
                    artist = featureJson.getString("KUNSTENAAR");
                }

                if (featureJson.has("MATERIAAL")) {
                    material = featureJson.getString("MATERIAAL");
                }

                if (featureJson.has("OMSCHRIJVING")) {
                    desc = featureJson.getString("OMSCHRIJVING");
                }

                if (featureJson.has("ONDERGROND")) {
                    substrate = featureJson.getString("ONDERGROND");
                }

                if (featureJson.has("URL")) {
                    urlObject = featureJson.getString("URL");
                }

                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                Date date = null;

                if(featureJson.has("PLAATSINGSDATUM") && !featureJson.isNull("PLAATSINGSDATUM")){
                    int dateInt = featureJson.getInt("PLAATSINGSDATUM");
                    try {
                        date = originalFormat.parse("" + dateInt);
                    } catch (ParseException e){
                        e.printStackTrace();
                    }
                }

                Feature feature = new Feature(id, identification, object, geography, artist, material, desc, substrate, date, urlObject);
                features.add(feature);
            }
        } catch(JSONException e){
            e.printStackTrace();
            return features;
        }
        return features;
    }
}
