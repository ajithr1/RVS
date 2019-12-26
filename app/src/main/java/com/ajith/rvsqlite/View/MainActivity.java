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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ajith.rvsqlite.Model.GroceryContract;
import com.ajith.rvsqlite.Model.GroceryDbHelper;
import com.ajith.rvsqlite.Presenter.Presenter;
import com.ajith.rvsqlite.Presenter.PresenterInt;
import com.ajith.rvsqlite.R;

public class MainActivity extends AppCompatActivity implements CreateList.OnFragmentInteractionListener, ViewInt {

    public static final String TAG = "ajju";

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

        Log.d(TAG, "onCreate: ");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryAdapter = new GroceryAdapter(this);
        recyclerView.setAdapter(groceryAdapter);
    }

    public void insert(View view) {
        Log.d(TAG, "create button clicked and fragment started");
        fragmentManager = getSupportFragmentManager();
        createShopList = CreateList.newInstance();
        fragmentManager.beginTransaction().addToBackStack(null).add(R.id.con, createShopList).commit();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        Log.d(TAG, "data from fragment"+ sendBackText);
        presenterInt = new Presenter();
        Log.d(TAG, "Insert View");
        boolean isInserted = presenterInt.insertToModel(sendBackText, getApplicationContext());
        if (!isInserted){
            Toast.makeText(this, "insertion failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
        }
        onBackPressed();
    }
}
