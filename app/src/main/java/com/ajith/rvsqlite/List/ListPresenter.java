package com.ajith.rvsqlite.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ajith.rvsqlite.Model.Entity.Todo;
import com.ajith.rvsqlite.Model.TodoRepository;

import static com.ajith.rvsqlite.List.ListActivity.TAG;

class ListPresenter implements IListPresenter {

    private IListView view;
    private IListInteracted interacted;
    private Context context;

    private Todo editTodo;

    ListPresenter(ListActivity view, Context context) {
        this.view       = view;
        this.interacted = new TodoRepository(context.getContentResolver());
        this.context    = context;
    }

    @Override
    public void refreshSession() {
        Log.d(TAG, "refreshSession: "+interacted.get().toString());
        view.setTodo(interacted.get());
    }

    @Override
    public void onAddTodoButtonClick() {

    }

    @Override
    public void onClickTodoItemToEdit(Todo todo) {

    }

    @Override
    public void onLongClickTodoItem(Todo todo) {

    }

    @Override
    public void updateTodoIsCompleted(Todo todo, boolean completed, int position) {

    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public void create(String s) {

        Todo todo = new Todo();
        todo.setTitle(s);
        interacted.create(todo);
        Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
    }
}
