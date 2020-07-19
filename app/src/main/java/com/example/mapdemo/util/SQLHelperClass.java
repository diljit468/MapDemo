package com.example.mapdemo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.mapdemo.Model.Favourite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLHelperClass extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelperClass";
    public static final String dbName = "Database";
    public static final int dbVersion = '1';
    private Context context;
    public static final String TABLE_NAME = "Location";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE =  "longitude";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_PINCODE = "pincode";


    public SQLHelperClass(Context context) {
        super(context, dbName, null, dbVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABLE_NAME+
                "(" +COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LATITUDE+ " TEXT," +
                COLUMN_LONGITUDE+ " TEXT," +
                COLUMN_CITY+ " TEXT, " +
                COLUMN_STATE+ " TEXT," +
                COLUMN_PINCODE+ " TEXT)";

        sqLiteDatabase.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    /*insertion*/
    public void addLocation(Favourite favourite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LATITUDE, favourite.getLatitude());
        cv.put(COLUMN_LONGITUDE, favourite.getLongitude());
        cv.put(COLUMN_CITY, favourite.getCity());
        cv.put(COLUMN_STATE, favourite.getState());
        cv.put(COLUMN_PINCODE, favourite.getPincode());
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllLocation(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public List<Favourite> getAllNotes() {
        List<Favourite> favourites = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " +TABLE_NAME + " ORDER BY " +
                COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Favourite note = new Favourite();
                note.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                note.setLatitude(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)));
                note.setLongitude(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE)));
                note.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
                note.setState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
                note.setPincode(cursor.getString(cursor.getColumnIndex(COLUMN_PINCODE)));
                favourites.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return favourites;
    }


    void updateLocation(String row_id,String latitude, String longitude, String city, String state, int pincode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(String.valueOf(COLUMN_LATITUDE), latitude);
        cv.put(String.valueOf(COLUMN_LONGITUDE), longitude);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_STATE, state);
        cv.put(String.valueOf(COLUMN_PINCODE), pincode);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

  public  void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}
