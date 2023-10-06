package com.example.firebase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ALARM_SYSTEM";
    private static final String TABLE_NAME = "ALARM_TABLE";
    public static final String ID = "_id";
    public static final String MESSAGE = "message";
    public static final String DATE = "date";
    private static final int VERSION_NUMBER = 1;

    private static final String TABLE_STRUCTURE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MESSAGE + " TEXT, " +
                    DATE + " DATE)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    private Context context;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
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

    public long insertData(String message, String formatDate) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MESSAGE, message);
        contentValues.put(DATE, formatDate);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor displayAllData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(SELECT_ALL, null);
    }

    public long InsertData(String message, String formattedDate) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MESSAGE,message);
        contentValues.put(DATE,formattedDate);
        long rowId= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }
}
