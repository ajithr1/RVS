package com.ajith.rvsqlite.Model_Firebase;

import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Model_SQLite.Entity.Todo;

public interface IListModelFirebase {

    /**
     * Get all To.Dos from database
     */
    void getFire(RecyclerView recyclerView);

    /**
     * Update old To.Do
     */
    void updateFire(Todo todo);

    void createFire(long id, String name);

    void deleteFire(Todo todo);
}
