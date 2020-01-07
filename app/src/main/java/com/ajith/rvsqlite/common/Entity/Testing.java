package com.ajith.rvsqlite.common.Entity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.R;

public class Testing extends AppCompatActivity {

    EditText editText_insert, editText_update;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        editText_insert = findViewById(R.id.insert_text);
        editText_update = findViewById(R.id.update_text);
        recyclerView = findViewById(R.id.test_rc);
    }

    public void insert(View view) {
    }

    public void edit(View view) {
    }

    public void delete(View view) {
    }
}
