package com.bcit.leon.assignment2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Leon on 2015-03-27.
 */
public class Http extends AsyncTask<String, String, String> {
    MainActivity activity;
    public Http(MainActivity activity){
        this.activity = activity;
    }
    protected String doInBackground(String... input) {
        String apiKey = input[0];
        String dbName = input[1];
        String collection = input[2];
        String target = "https://api.mongolab.com/api/1/databases/"+dbName+"/collections/"+collection+"?apiKey="+apiKey;
        Log.w("http", target);
        HttpGet httpGet = new HttpGet(target);
        httpGet.setHeader("Content-type", "application/json");
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed";
    }
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONArray jsArray = new JSONArray(s);
            for(int i = 0; i < jsArray.length(); i++) {
                JSONObject temp = jsArray.getJSONObject(i);
                activity.updateSql(temp);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        /*try {
            //JSONObject result = new JSONObject(s);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}

