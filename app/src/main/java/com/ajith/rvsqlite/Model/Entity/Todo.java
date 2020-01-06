package com.ajith.rvsqlite.Model.Entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.ajith.rvsqlite.Model.ContentProvider.TodoContract;

public class Todo implements Parcelable {

    public static final long UNSAVED_ID = Long.MAX_VALUE;

    private long id;
    private String title;

    public Todo() {
        this.id = UNSAVED_ID;
        this.title = "";
    }

    public Todo(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Todo(Cursor cursor) {
        int idIdx           = cursor.getColumnIndex(TodoContract._ID);
        int titleIdx        = cursor.getColumnIndex(TodoContract.TITLE);

        this.id          = cursor.getInt(idIdx);
        this.title       = cursor.getString(titleIdx);
    }

    public Todo(Parcel in) {
        this.id          = in.readLong();
        this.title       = in.readString();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}
