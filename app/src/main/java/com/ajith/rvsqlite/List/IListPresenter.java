package com.ajith.rvsqlite.List;

import com.ajith.rvsqlite.Model.Entity.Todo;

public interface IListPresenter {

    /**
     * Refresh data in view layer
     */
    void refreshSession();

    /**
     * Action when the user click on Fab button
     */
    void onAddTodoButtonClick();

    /**
     * Action when the user click to edit on To.Do item
     */
    void onClickTodoItemToEdit(Todo todo);

    /**
     * Action when the user long click on To.Do item
     */
    void onLongClickTodoItem(Todo todo);

    /**
     * Update To.Do completed property
     */
    void updateTodoIsCompleted(Todo todo, boolean completed, int position);

    /**
     * Delete old To.Do
     */
    void delete(Todo todo);

    void create(String s);
}
