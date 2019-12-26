package com.ajith.rvsqlite.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Model implements ModelInt {

    public static final String TAG = "ajju";
    private Cursor cursor;

    private SQLiteDatabase sqLiteDatabase;
    private GroceryDbHelper groceryDbHelper;

    public Model(Context context) {
         groceryDbHelper = new GroceryDbHelper(context);
         sqLiteDatabase = groceryDbHelper.getWritableDatabase();
    }

    @Override
    public boolean insertToDb(String s) {
        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, s);

        long result = sqLiteDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null,cv);

        if (result == -1){
            Log.d(TAG, "insert returned -1");
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllItems(){
        return sqLiteDatabase.query(GroceryContract.GroceryEntry.TABLE_NAME, null, null
                ,null, null, null , null + " DESC ");
    }
}
