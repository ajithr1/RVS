package com.ajith.rvsqlite.Model.ContentProvider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class TodoContract implements BaseColumns {

    public TodoContract() { }

    private static final String AUTHORITY = "com.ajith.rvsqlite.Model.provider.TodoProvider";

    private static final String ENDPOINT = "todo";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + ENDPOINT);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.me.jrubio.todo.model.todo";

    static final String TABLE = "todo";

    public static final String TITLE        = "title";

    public static final String[] PROJECTION_ALL = {_ID, TITLE};

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE
            + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + " TEXT);";

    public static final String FIXTURE =
            "INSERT INTO " + TABLE
                    + " (" + TITLE
                    + ") "
                    + "VALUES ('TodoFixture', 'Description', 1434703928, 0);";
}
