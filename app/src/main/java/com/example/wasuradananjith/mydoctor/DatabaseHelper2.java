package com.example.wasuradananjith.mydoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Wasura Dananjith on 02-Feb-17.
 */

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper2.class.getSimpleName();

    public static final String DB_NAME ="details.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE ="users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_PHONE = "phone";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE "+USER_TABLE+"("
            + COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_USERNAME+" TEXT,"
            +COLUMN_PASS+ " TEXT,"+COLUMN_PHONE+" TEXT);";

    public DatabaseHelper2(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }

    public void addUser(String username, String password, String phone){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,username);
        values.put(COLUMN_PASS,password);
        values.put(COLUMN_PHONE,phone);

        long id = db.insert(USER_TABLE,null,values);
        db.close();

        Log.d(TAG,"user inserted" + id);
    }

    public boolean getUser(String username, String pass){
        String selectQuery = "select "+COLUMN_USERNAME+","+COLUMN_PASS+" from "+USER_TABLE+" where "+
                COLUMN_USERNAME+ " = "+"'"+username+"'"+" and "+
                COLUMN_PASS+" = "+"'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public Cursor getPhoneNumber(String username, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from "+USER_TABLE+" WHERE username="+"'"+username+"'"+ " AND password="+"'"+
                pass+"'",null);
        return cursor;
    }
}
