package com.ajith.rvsqlite.Model_SQLite;

import com.ajith.rvsqlite.common.Entity.Todo;

import java.util.ArrayList;

public interface IListModelSQLite {

    /**
     * Get all To.Dos from database
     */
    ArrayList<Todo> get();

    /**
     * Update old To.Do
     */
    void update(Todo todo);

    void create(Todo todo);

    void delete(Todo todo);
}
