package com.example.rs.caughther;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bharath on 29/3/18.
 */

public class databaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String tableName = "messages";
    private static final String DATABASE_NAME = "CHdb";

    private static final String sender = "sender";
    private static final String message = "message";
    private static final String id = "id";

    private static final String createDb="CREATE TABLE "+tableName+"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+sender+" TEXT, "+message+" TEXT)";


    public databaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(createDb);
        Log.d("DB", "DB created..");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }



    public void add(String se,String me){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(sender,se);
        cv.put(message,me);

        db.insert(tableName, null, cv);
        Log.d("Inserted:", "DB values inserted..");
        db.close();
    }

    public JSONArray get() throws JSONException {


        String selectQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        final JSONArray arr=new JSONArray();
        if (cursor.moveToFirst()) {
            do {
                JSONObject t = new JSONObject();
                t.put("sender",cursor.getString(1));
                t.put("message",cursor.getString(2));
                arr.put(t);

            } while (cursor.moveToNext());
        }
        return  arr;
    }



}
