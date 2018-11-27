package com.essenco.adviser.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.essenco.adviser.CallBackInterface;
import com.essenco.adviser.Domain.Advise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AsyncDAO extends AsyncTask<String, Void, String> {
    private static final String TAG = "AsyncDAO";
    private Context appContext;
    private CallBackInterface callBackInterface;
    private String API_URL;
    private String adviseSubject;
    private ArrayList<Advise> adviseArrayList = new ArrayList<>();
    private SQLiteOpenHelper db;

    public AsyncDAO(Context appContext, CallBackInterface callBackInterface, String API_URL, String adviseSubject) {
        this.appContext = appContext;
        this.callBackInterface = callBackInterface;
        this.API_URL = API_URL;
        this.adviseSubject = adviseSubject;
        this.db = new SQLiteOpenHelper(appContext);
    }

    @Override
    protected void onPostExecute(String response) {
        if (response == null || response == "") {
            Toast.makeText(appContext.getApplicationContext(),
                    "No advise found about entered keywords.", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray advises;
            // Getting JSON Array node
            if (adviseSubject != ""){
                advises = jsonObj.getJSONArray("slips");
                // looping through all advises
                for (int i = 0; i < advises.length(); i++) {
                    JSONObject a = advises.getJSONObject(i);
                    int id = a.getInt("slip_id");
                    String advice = a.getString("advice");
                    Advise advise = new Advise(id, advice);
                    createAdvise(advise);
                }
            }else{
              JSONObject test = jsonObj.getJSONObject("slip");
              String advice = test.getString("advice");
              int id = test.getInt("slip_id");
              Advise advise = new Advise(id, advice);

              createAdvise(advise);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callBackInterface.adviseList(adviseArrayList);
    }
    private void createAdvise(Advise advise){
        try{
            if (db.selectAdviseById(advise.getAdviseId()) != null){
                adviseArrayList.add(db.selectAdviseById(advise.getAdviseId()));
            }else{
                long id = db.createAdvise(advise);
                Advise a = db.selectAdviseById(id);
                adviseArrayList.add(a);
            }
        }catch (Exception e){
            throw e;
        }
    }
    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        int responseCode = -1;
        String response = "";
        try {
            // Maak een URL object
            URL url = new URL(API_URL);
            // Open een connection op de URL
            URLConnection urlConnection = url.openConnection();
            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }
            // Initialiseer een HTTP connectie
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            // Voer het request uit via de HTTP connection op de URL
            httpConnection.connect();
            // Kijk of het gelukt is door de response code te checken
            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getStringFromInputStream(InputStream inputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
