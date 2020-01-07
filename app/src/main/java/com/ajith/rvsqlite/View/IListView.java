package com.ajith.rvsqlite.View;

import com.ajith.rvsqlite.common.Entity.Todo;

import java.util.ArrayList;

public interface IListView {

    /**
     * Set To.Dos in the Adapter
     */
    void setTodo(ArrayList<Todo> todo);
}
