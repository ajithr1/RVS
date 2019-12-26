package com.ajith.rvsqlite.Presenter;

import android.content.Context;
import android.database.Cursor;

import com.ajith.rvsqlite.Model.Model;
import com.ajith.rvsqlite.Model.ModelInt;

public class Presenter implements PresenterInt {

    ModelInt modelInt;
    @Override
    public Cursor getA(Context context) {
        modelInt = new Model(context);
        return modelInt.getAllItems();
    }
}
