package com.ajith.rvsqlite.Presenter;

import android.content.Context;
import android.util.Log;

import com.ajith.rvsqlite.Model.Model;
import com.ajith.rvsqlite.Model.ModelInt;

public class Presenter implements PresenterInt {

    private static final String TAG = "ajju";

    private ModelInt modelInt;

    @Override
    public boolean insertToModel(String s, Context context) {
        Log.d(TAG, "Insert Presenter");
        modelInt = new Model(context);
        return modelInt.insertToDb(s);
    }

    @Override
    public String getItemAt(int pos, Context context) {
        modelInt = new Model(context);
        return modelInt.getItemAt(pos);
    }

    @Override
    public int getItemCount(Context context) {
        modelInt = new Model(context);
        return modelInt.getCount();
    }
}
