package com.ajith.rvsqlite.Model.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ajith.rvsqlite.Model.Connection.Database;

import static com.ajith.rvsqlite.List.ListActivity.TAG;

public class TodoProvider extends ContentProvider {

    private Database database;

    @Override
    public boolean onCreate() {
        this.database = new Database(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TodoContract.TABLE);
        return doQuery(builder, projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert: TodoProvider");
        Long insertedId = this.doInsert(TodoContract.TABLE, values);
        return Uri.withAppendedPath(uri, String.valueOf(insertedId));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = this.database.getWritableDatabase();
        return db.delete(TodoContract.TABLE, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return this.doUpdate(TodoContract.TABLE, values, selection, selectionArgs);
    }

    private Cursor doQuery(SQLiteQueryBuilder builder,
                           String[] projection,
                           String selection,
                           String[] selectionArgs,
                           String sortOrder) {
        SQLiteDatabase db = this.database.getReadableDatabase();
        return builder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
    }

    private Long doInsert(String table, ContentValues values) {
        SQLiteDatabase db = this.database.getWritableDatabase();
        return db.insert(table, null, values);
    }

    private int doUpdate(String table,
                         ContentValues values,
                         String selection,
                         String[] selectionArgs) {
        SQLiteDatabase db = this.database.getWritableDatabase();
        return db.update(table, values, selection, selectionArgs);
    }
}
