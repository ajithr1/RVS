package com.ajith.rvsqlite.Presenter;

import android.content.Context;

public interface PresenterInt {

    boolean insertToModel(String string, Context context);

    String getItemAt(int pos, Context context);

    int getItemCount(Context context);
}
