package com.bcit.leon.assignment2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jens-Lenovo on 2015-03-27.
 */
public class MyDBHandler extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "people.db";
    private static final String TABLE = "collection";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void writeAll() {
        String query = "Select * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        //SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Person> people = new ArrayList<Person>();

        /*
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            product = null;
        }
        */

        try
        {
            cursor.moveToFirst();

            while(!(cursor.isAfterLast()))
            {
                String fname;
                String lname;
                String email;
                String id;
                people.add(new Person(fname, lname, email, id));
                cursor.moveToNext();
            }

        }
        finally
        {
            // make sure to close the cursor
            cursor.close();
        }


        db.close();
    }
}

