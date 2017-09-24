package com.example.wasuradananjith.mydoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Wasura Dananjith on 25-Jan-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="MediQLite.db";
    public static final String TABLE_NAME="Request_Table";
    public static final String COL_1="RequestID";
    public static final String COL_2="ReferenceNo";
    public static final String COL_3="RequestedNo";

    public static final String CREATE_TABLE_REQUEST = "CREATE TABLE "+TABLE_NAME+"("
            + COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL_2+" TEXT,"
            +COL_3+ " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String refno, String reqno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,refno);
        contentValues.put(COL_3,reqno);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        Log.d("myTag", "getAllData method is working");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean updateData(String refno, String reqno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,refno);
        contentValues.put(COL_3,reqno);
        db.update(TABLE_NAME,contentValues,"ReferenceNo = ?",new String[] { refno });
        return true;
    }

    public Integer deleteData(String refno){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ReferenceNo = ?",new String[] { refno });
    }

    public void dropTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void deleteReqNo(String refno, String reqno){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("MYTAG", "MESSAGE 2");
        //db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL_2+"="+refno+" AND "+COL_3+"="+reqno+";");
        db.delete(TABLE_NAME,COL_2+"= ? AND "+COL_3 +"= ?",new String[] { refno,reqno});
        Log.d("MYTAG", "MESSAGE 3");
    }
}
