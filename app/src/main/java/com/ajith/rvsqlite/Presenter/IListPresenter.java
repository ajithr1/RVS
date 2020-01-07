package com.ajith.rvsqlite.Presenter;

import com.ajith.rvsqlite.common.Entity.Todo;

public interface IListPresenter {

    /**
     * Refresh data in view layer
     */
    void refreshSession();

    /**
     * Action when the user click to edit on To.Do item
     */
    void edit(Todo todo);

    /**
     * Delete old To.Do
     */
    void delete(Todo todo);

    void create(String s);
}
