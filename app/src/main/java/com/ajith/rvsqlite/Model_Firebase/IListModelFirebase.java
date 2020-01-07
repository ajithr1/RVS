package com.ajith.rvsqlite.Model_Firebase;

import com.ajith.rvsqlite.common.Entity.Todo;

import java.util.ArrayList;

public interface IListModelFirebase {

    /**
     * Get all To.Dos from database
     */
    ArrayList<Todo> getFire();

    /**
     * Update old To.Do
     */
    void updateFire(Todo todo);

    void createFire(Todo todo);

    void deleteFire(Todo todo);
}
