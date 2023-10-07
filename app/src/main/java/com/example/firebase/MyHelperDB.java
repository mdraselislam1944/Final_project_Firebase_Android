package com.example.firebase;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyHelperDB extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "ALARM_SYSTEM";
    private static final String TABLE_NAME = "ALARM_TABLE";
    public static final String ID = "_id";
    public static final String YEAR="year";
    public static final String MONTH="month";
    public static final String DATE="date";
    public static final String HOUR="hour";
    public static final String MINUTES="minutes";
    public static final String MESSAGE = "message";
    private static final int VERSION_NUMBER = 1;

    private static final String TABLE_STRUCTURE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    YEAR + " INTEGER, " +
                    MONTH + " INTEGER, " +
                    DATE + " INTEGER, " +
                    HOUR + " INTEGER, " +
                    MINUTES + " INTEGER, " +
                    MESSAGE + " TEXT)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;


    private Context context;

    public MyHelperDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context, "onCreate is created", Toast.LENGTH_LONG).show();
            db.execSQL(TABLE_STRUCTURE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception: " + e, Toast.LENGTH_LONG).show();
            Log.e("MyDatabase", "onCreate Exception", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "onUpgrade is created", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "onUpgrade is not created", Toast.LENGTH_LONG).show();
            Log.e("MyDatabase", "onUpgrade Exception", e);
        }
    }

    public long InsertData(int year, int month, int date, int hour, int minute, String message) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(YEAR, year);
        contentValues.put(MONTH, month);
        contentValues.put(DATE, date);
        contentValues.put(HOUR, hour);
        contentValues.put(MINUTES, minute);
        contentValues.put(MESSAGE, message);

        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close(); // Close the database connection after use

        return rowId;
    }

    // Inside MyHelperDB.java

    public List<AlarmData> getAllAlarms() {
        List<AlarmData> alarmDataList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int year = cursor.getInt(cursor.getColumnIndex(YEAR));
                @SuppressLint("Range") int month = cursor.getInt(cursor.getColumnIndex(MONTH));
                @SuppressLint("Range") int day = cursor.getInt(cursor.getColumnIndex(DATE));
                @SuppressLint("Range") int hour = cursor.getInt(cursor.getColumnIndex(HOUR));
                @SuppressLint("Range") int minute = cursor.getInt(cursor.getColumnIndex(MINUTES));
                @SuppressLint("Range") String message = cursor.getString(cursor.getColumnIndex(MESSAGE));

                AlarmData alarmData = new AlarmData(year, month, day, hour, minute, message);
                alarmDataList.add(alarmData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return alarmDataList;
    }

    public Cursor displayAllDataTime() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(SELECT_ALL, null);
    }

    public boolean deleteData(int rowId) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String whereClause = ID + "=?";
            String[] whereArgs = {String.valueOf(rowId)};
            int rowsAffected = db.delete(TABLE_NAME, whereClause, whereArgs);
            return rowsAffected > 0; // Returns true if at least one row was affected (deleted)
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return false;
        }
    }

    public long updateData(int id, int year, int month, int date, int hour, int minute, String message) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(YEAR, year);
        contentValues.put(MONTH, month);
        contentValues.put(DATE, date);
        contentValues.put(HOUR, hour);
        contentValues.put(MINUTES, minute);
        contentValues.put(MESSAGE, message);

        // Update the row with the specified ID
        int rowsAffected = sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(id)});

        sqLiteDatabase.close(); // Close the database connection after use

        return rowsAffected; // Return the number of rows affected by the update
    }



}
