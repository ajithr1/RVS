package com.ajith.rvsqlite.common;

import com.ajith.rvsqlite.Model.Entity.Todo;

import java.util.ArrayList;

public interface IListInteracted extends ITodoActionsInteracted {

    /**
     * Get all To.Dos from database
     */
    ArrayList<Todo> get();

    /**
     * Update old To.Do
     */
    void update(Todo todo);

    void create(Todo todo);
}
