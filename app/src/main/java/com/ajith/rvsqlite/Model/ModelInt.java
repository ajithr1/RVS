package com.ajith.rvsqlite.Model;

import android.database.Cursor;

public interface ModelInt {

    boolean insertToDb(String s);

    Cursor getAllItems();
}
