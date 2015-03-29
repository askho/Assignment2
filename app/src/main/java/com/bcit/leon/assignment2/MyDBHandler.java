package com.bcit.leon.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jens-Lenovo on 2015-03-27.
 */
public class MyDBHandler extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE = "collection";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email_address";
    private static final String STUDENT_NUM = "student_number";
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE + "("
                + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + STUDENT_NUM + " INTEGER);";
        Log.w("MyDBHandler", CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
    public void addStudent(JSONObject jsonObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(FIRST_NAME, jsonObject.getString("first_name"));
            values.put(LAST_NAME, jsonObject.getString("last_name"));
            values.put(EMAIL, jsonObject.getString("email_address"));
            values.put(STUDENT_NUM, jsonObject.getString("student_number"));
            db.insert(TABLE, null, values);
        }catch(JSONException ex) {
            ex.printStackTrace();
        }finally {
            db.close();
        }
    }

    public ArrayList<Student> getAll() {
        String query = "Select * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Student> students = new ArrayList<Student>();
        //Student(String fname, String lname, String email, int sno)

        while (cursor.moveToNext()) {
            String fname = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
            String lname = cursor.getString(cursor.getColumnIndex(LAST_NAME));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            int studNo = cursor.getInt(cursor.getColumnIndex(STUDENT_NUM));
            Log.w("Cursor", cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
            students.add(new Student(fname, lname, email, studNo));
        }
        db.close();
        Log.w("cursor", "added all entries");
        return students;
    }
}

