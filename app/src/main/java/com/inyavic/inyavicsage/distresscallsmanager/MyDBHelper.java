package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "distress_calls_manager.db";
    private static final String DCM_TABLE_NAME = "distress_calls_managers";
    private static final String DCM_COLUMN_ID = "_id";
    private static final String DCM_COLUMN_USERNAME = "username";
    private static final String DCM_COLUMN_PASSWORD = "password";
    private static final String DCI_TABLE_NAME = "distress_calls_info";
    private static final String DCI_COLUMN_ID = "_id";
    private static final String DCI_COLUMN_TYPE = "type";
    private static final String DCI_COLUMN_PRIORITY = "priority";
    private static final String DCI_COLUMN_CALLER_NAME = "caller_name";
    private static final String DCI_COLUMN_CALLER_PHONE_NUM = "caller_phone_num";
    private static final String DCI_COLUMN_CALLER_LOC = "caller_loc";
    private static final String DCI_COLUMN_DESCRIPTION = "description";
    private static final String DCI_COLUMN_REC_CALL_PATH = "rec_call_path";
    private static final String DCI_COLUMN_DATETIME_RECEIVED = "datetime_received";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DCM_TABLE_NAME + "(" +
                DCM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DCM_COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
                DCM_COLUMN_PASSWORD + " TEXT NOT NULL" +
                ");";
        String query2 = "CREATE TABLE " + DCI_TABLE_NAME + "(" +
                DCI_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DCI_COLUMN_TYPE + " TEXT NOT NULL, " +
                DCI_COLUMN_PRIORITY + " INTEGER NOT NULL, " +
                DCI_COLUMN_CALLER_NAME + " TEXT NOT NULL, " +
                DCI_COLUMN_CALLER_PHONE_NUM + " INTEGER NOT NULL, " +
                DCI_COLUMN_CALLER_LOC + " TEXT NOT NULL, " +
                DCI_COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                DCI_COLUMN_REC_CALL_PATH + " TEXT NOT NULL, " +
                DCI_COLUMN_DATETIME_RECEIVED + " TEXT NOT NULL" +
                ");";
        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DCM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DCI_TABLE_NAME);
        onCreate(db);
    }

    public void addDistressCallsManager(DistressCallsManagers DCM) {
        ContentValues values = new ContentValues();
        values.put(DCM_COLUMN_USERNAME, DCM.getUsername());
        values.put(DCM_COLUMN_PASSWORD, DCM.getPassword());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(DCM_TABLE_NAME, null, values);
        db.close();
    }

    public void updateDistressCallsManager(String DCM_ID, HashMap<String, String> updateMap) {
        ContentValues values = new ContentValues();

        for (Map.Entry<String, String> entry : updateMap.entrySet()) {
            values.put(entry.getKey(), entry.getValue());
        }

        SQLiteDatabase db = getWritableDatabase();
        db.update(DCM_TABLE_NAME, values, "_id = " + DCM_ID, null);
        db.close();
    }

    public void deleteDistressCallsManager(int DCM_ID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + DCM_TABLE_NAME + " WHERE " + DCM_COLUMN_ID + "=\"" + DCM_ID + "\";");
        db.close();
    }

    public ArrayList<DistressCallsManagers> getDistressCallsManagers() {
        ArrayList<DistressCallsManagers> DCM = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + DCM_TABLE_NAME;
        Cursor c = db.rawQuery(query, null);

        DistressCallsManagers DCM2;

        if (c.moveToFirst()) {
            do {
                DCM2 = new DistressCallsManagers();
                DCM2.set_id(c.getInt(c.getColumnIndex(DCM_COLUMN_ID)));
                DCM2.setUsername(c.getString(c.getColumnIndex(DCM_COLUMN_USERNAME)));
                DCM2.setPassword(c.getString(c.getColumnIndex(DCM_COLUMN_PASSWORD)));
                DCM.add(DCM2);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return DCM;
    }

    public String getDistressCallsManagerUsername(String DCM_ID) {
        String DCMUsername = null;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + DCM_COLUMN_USERNAME + " FROM " + DCM_TABLE_NAME
                + " WHERE " + DCM_COLUMN_ID + "=\"" + DCM_ID + "\";";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            DCMUsername = c.getString(c.getColumnIndex(DCM_COLUMN_USERNAME));
        }

        c.close();
        db.close();
        return DCMUsername;
    }

    public boolean isLoginDetailsCorrect(String username, String password) {
        boolean isLoginDetailsCorrect = false;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + DCM_COLUMN_ID + " FROM " + DCM_TABLE_NAME
                + " WHERE " + DCM_COLUMN_USERNAME + "=\"" + username
                + "\" AND " + DCM_COLUMN_PASSWORD + "=\"" + password + "\";";
        Cursor c = db.rawQuery(query, null);

        if (c.getCount() > 0) {
            isLoginDetailsCorrect = true;
        }

        c.close();
        db.close();
        return isLoginDetailsCorrect;
    }

    public ArrayList<DistressCallsManagers> getDistressCallsInfo() {
        ArrayList<DistressCallsManagers> DCM = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + DCM_TABLE_NAME;
        Cursor c = db.rawQuery(query, null);

        DistressCallsManagers DCM2;

        if (c.moveToFirst()) {
            do {
                DCM2 = new DistressCallsManagers();
                DCM2.set_id(c.getInt(c.getColumnIndex(DCM_COLUMN_ID)));
                DCM2.setUsername(c.getString(c.getColumnIndex(DCM_COLUMN_USERNAME)));
                DCM2.setPassword(c.getString(c.getColumnIndex(DCM_COLUMN_PASSWORD)));
                DCM.add(DCM2);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return DCM;
    }
}
