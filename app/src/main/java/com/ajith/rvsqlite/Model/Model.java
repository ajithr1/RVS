package com.ajith.rvsqlite.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Model implements ModelInt {

    private static final String TAG = "ajju";
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
        Log.d(TAG, "Insert Model");
        long result = sqLiteDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null,cv);

        return result != -1;
    }

    private Cursor getAllItems(){
        return sqLiteDatabase.query(GroceryContract.GroceryEntry.TABLE_NAME, null, null
                ,null, null, null , null + " DESC ");
    }

    @Override
    public int getCount(){
        cursor = getAllItems();
        return cursor.getCount();
    }

    @Override
    public String getItemAt(int position) {
        cursor = getAllItems();
        if (!cursor.moveToPosition(position)){
            return null;
        }else {
            return cursor.getString(cursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        }
    }
}
