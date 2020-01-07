package com.ajith.rvsqlite.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ajith.rvsqlite.common.IListInteracted;
import com.ajith.rvsqlite.Model.ContentProvider.TodoContract;
import com.ajith.rvsqlite.Model.Entity.Todo;

import java.util.ArrayList;

import static com.ajith.rvsqlite.View.ListActivity.TAG;

public class TodoRepository implements IListInteracted {

    private ContentResolver contentResolver;

    public TodoRepository(ContentResolver contentResolver) {
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
        Log.d(TAG, "create: in TodoRepository");
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
        Log.d(TAG, "save: TodoRepository");
        ContentValues values = new ContentValues();
        values.put(TodoContract.TITLE, todo.getTitle());

        if (todo.getId() == Todo.UNSAVED_ID) {
            Log.d(TAG, "save: in insert ");
            Uri insertUri = getContentResolver().insert(TodoContract.CONTENT_URI, values);
            todo.setId(Long.valueOf(insertUri.getLastPathSegment()));
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
