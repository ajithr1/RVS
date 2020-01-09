package com.ajith.rvsqlite.Model_SQLite;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ajith.rvsqlite.Model_SQLite.ContentProvider.TodoContract;
import com.ajith.rvsqlite.Model_SQLite.Entity.Todo;

import java.util.ArrayList;
import java.util.Objects;

import static com.ajith.rvsqlite.View.ListActivity.TAG;

public class TodoRepositorySQLite implements IListModelSQLite {

    private ContentResolver contentResolver;

    public TodoRepositorySQLite(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    private ContentResolver getContentResolver() {
        return contentResolver;
    }

    @Override
    public ArrayList<Todo> get() {
        ArrayList<Todo> todoList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
                TodoContract.CONTENT_URI,
                TodoContract.PROJECTION_ALL,
                null,
                null,
                null);

        if (null == cursor || !cursor.moveToNext()) {
            return todoList;
        }

        do {
            Todo todo = new Todo(cursor);
            todoList.add(todo);
        } while (cursor.moveToNext());

        cursor.close();
        return todoList;
    }

    @Override
    public void update(Todo todo) {
        //todo.setEdited(new Date().getTime());
        save(todo);
    }

    @Override
    public void create(Todo todo) {
        Log.d(TAG, "create: in TodoRepositorySQLite");
        save(todo);
    }

    @Override
    public void delete(Todo todo) {
        Log.d(TAG, "delete: repo  "+todo);
        String[] selectionArgs = {String.valueOf(todo.getId())};
        getContentResolver().delete(
                TodoContract.CONTENT_URI,
                TodoContract._ID + " = ?",
                selectionArgs);
    }

    private void save(Todo todo) {
        Log.d(TAG, "save: TodoRepositorySQLite");
        ContentValues values = new ContentValues();
        values.put(TodoContract.TITLE, todo.getTitle());

        if (todo.getId() == Todo.UNSAVED_ID) {
            Log.d(TAG, "save: in insert ");
            Uri insertUri = getContentResolver().insert(TodoContract.CONTENT_URI, values);
            assert insertUri != null;
            todo.setId(Long.valueOf(Objects.requireNonNull(insertUri.getLastPathSegment())));
        } else {
            String[] selectionArgs = {String.valueOf(todo.getId())};
            getContentResolver().update(
                    TodoContract.CONTENT_URI,
                    values,
                    TodoContract._ID + " = ?",
                    selectionArgs);
        }
    }
}
