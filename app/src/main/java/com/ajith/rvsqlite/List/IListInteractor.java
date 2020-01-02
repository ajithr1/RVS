package com.ajith.rvsqlite.List;

import com.ajith.rvsqlite.Model.Entity.Todo;
import com.ajith.rvsqlite.common.ITodoActionsInteracted;

import java.util.ArrayList;

public interface IListInteractor extends ITodoActionsInteracted {

    /**
     * Get all To.Dos from database
     * @return ArrayList
     */
    ArrayList<Todo> get();

    /**
     * Update old To.Do
     *
     * @param todo
     */
    void update(Todo todo);
}
