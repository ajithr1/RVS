package com.ajith.rvsqlite.View;

import com.ajith.rvsqlite.Model.Entity.Todo;

import java.util.ArrayList;

public interface IListView {

    /**
     * Set To.Dos in the Adapter
     */
    void setTodo(ArrayList<Todo> todo);

    /**
     * Notify To.Dos data set has changed
     */
    void notifyListDataSetChanged();

    /**
     * Notify item removed in Adapter
     */
    void notifyListItemRemoved(int position);

    /**
     * Notify item inserted in Adapter
     */
    void notifyListItemInserted(int position);

    /**
     * Show Dialog with To.Do actions
     */
    void showItemDialog(Todo todo, CharSequence[] items);

    /**
     * Show TodoView to edit old To.Do
     */
    void showTodoViewToEdit(Todo todo);

    /**
     * Show TodoView to create new To.Do
     */
    void showTodoView();
}
