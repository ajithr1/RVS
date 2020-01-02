package com.ajith.rvsqlite.common;

import com.ajith.rvsqlite.Model.Entity.Todo;

public interface ITodoActionsInteracted {

    /**
     * Delete To.Do in the database
     * @param todo
     */
    void delete(Todo todo);
}
