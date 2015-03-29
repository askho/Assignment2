package com.bcit.leon.assignment2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDBHandler(this, null, null, 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onGetData(View view) {
        EditText apiKey = (EditText)this.findViewById(R.id.apiKey);
        EditText dbName = (EditText)this.findViewById(R.id.dbName);
        EditText collection = (EditText)this.findViewById(R.id.collection);
        Http http = new Http(this);
        db.reset();
        http.execute(apiKey.getText().toString(), dbName.getText().toString(), collection.getText().toString());
    }
    public void updateSql(JSONObject jsonObject) throws JSONException {
        //TextView textView = (TextView)this.findViewById(R.id.textView);
        db.addStudent(jsonObject);
    }


    public void getDB(View view) {
        TextView list = (TextView)findViewById(R.id.sqliteList);
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        Log.w("getDB", "starting db.getAll()");
        ArrayList<Student> students = db.getAll();
        Log.w("getDB", "finished db.getAll()");
        list.setText("");
        for (Student s : students) {
            list.append(s + "\n");
        }
    }
}
