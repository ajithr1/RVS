package com.ajith.rvsqlite.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ajith.rvsqlite.Model.GroceryContract.*;

public class GroceryDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "groceryList.db";
    public static final int DATABASE_VERSION = 1;

    public GroceryDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERY_LIST_TABLE =
                "CREATE TABLE " + GroceryEntry.TABLE_NAME + "(" +
                GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroceryEntry.COLUMN_NAME + " TEXT NOT NULL" + ");";

        db.execSQL(SQL_CREATE_GROCERY_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + GroceryEntry.TABLE_NAME);
        onCreate(db);
    }
}
