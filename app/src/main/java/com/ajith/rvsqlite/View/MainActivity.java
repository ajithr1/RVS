package com.ajith.rvsqlite.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ajith.rvsqlite.Model.GroceryContract;
import com.ajith.rvsqlite.Model.GroceryDbHelper;
import com.ajith.rvsqlite.Presenter.Presenter;
import com.ajith.rvsqlite.Presenter.PresenterInt;
import com.ajith.rvsqlite.R;

public class MainActivity extends AppCompatActivity implements CreateList.OnFragmentInteractionListener, ViewInt {

    FragmentManager fragmentManager;
    CreateList createShopList;
    SQLiteDatabase sqLiteDatabase;

    private GroceryAdapter groceryAdapter;

    PresenterInt presenterInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDbHelper dbHelper = new GroceryDbHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryAdapter = new GroceryAdapter(this, getAll(getApplicationContext()));
        recyclerView.setAdapter(groceryAdapter);
    }

    public void insert(View view) {
        fragmentManager = getSupportFragmentManager();
        createShopList = CreateList.newInstance();
        fragmentManager.beginTransaction().addToBackStack(null).add(R.id.con, createShopList).commit();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, sendBackText);

        long result = sqLiteDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null,cv);
        groceryAdapter.swapCursor(getAll(getApplicationContext()));
        onBackPressed();

        if (result == -1) {
            Toast.makeText(this, "Inserted text - " + sendBackText, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public Cursor getAll(Context context) {
        presenterInt = new Presenter();
        return presenterInt.getA(context);
    }
}
