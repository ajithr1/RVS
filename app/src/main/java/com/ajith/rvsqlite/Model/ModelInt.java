package com.ajith.rvsqlite.Model;

import android.database.Cursor;

public interface ModelInt {

    boolean insertToDb(String s);

    String getItemAt(int position);

    int getCount();
}
