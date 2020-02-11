package com.example.android.tasks;

import android.os.AsyncTask;

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

public class GetItemAsyncTask extends AsyncTask<String, Void, String> {

        private MainActivity main;

        public GetItemAsyncTask(MainActivity main) {
            this.main = main;
        }

        @Override
        protected String doInBackground(String... str) {


            URL url = NetworkUtils.buildUrl();
            String response = null;
            try {
                response = NetworkUtils.sendGET(url);

            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(response);
            return response;
        }

        @Override
        protected void onPostExecute(String str) {
            ArrayList<Feature> features = new ArrayList<>();
            try {
                JSONObject j = new JSONObject(str);
                JSONArray jsonArray = j.getJSONArray("features");
                JSONObject json = new JSONObject();
                JSONObject jArray;
                for (int i=0;i<jsonArray.length();i++) {
                    jArray = jsonArray.getJSONObject(i);

                    json = jArray.getJSONObject("attributes");
                    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                    Date date = null;
                    if(json.has("PLAATSINGSDATUM") && !json.isNull("PLAATSINGSDATUM")){
                        int dateInt = json.getInt("PLAATSINGSDATUM");
                        try {
                            date = originalFormat.parse("" + dateInt);
                        } catch (ParseException e){
                            e.printStackTrace();
                        }
                    }

                    Feature feature = new Feature(json.getInt("OBJECTID"), json.getString("IDENTIFICATIE"), json.getString("AANDUIDINGOBJECT"), json.getString("GEOGRAFISCHELIGGING"), json.getString("KUNSTENAAR"), json.getString("MATERIAAL"), json.getString("OMSCHRIJVING"), json.getString("ONDERGROND"), date , json.getString("URL"));
                    features.add(feature);
                }
                main.linkAdapter(features);
            } catch(JSONException e){
                e.printStackTrace();
            }

        }


}
