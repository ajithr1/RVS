package com.ajith.rvsqlite.Todo;

import com.ajith.rvsqlite.Model.Entity.Todo;

public interface ITodoInteractor {

    /**
     * Insert new To.Do in the database
     *
     * @param todo
     */
    void create(Todo todo);
}
