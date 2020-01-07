package com.ajith.rvsqlite.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ajith.rvsqlite.common.Entity.Todo;
import com.ajith.rvsqlite.Model_SQLite.TodoRepositorySQLite;
import com.ajith.rvsqlite.View.IListView;
import com.ajith.rvsqlite.View.ListActivity;
import com.ajith.rvsqlite.Model_SQLite.IListModelSQLite;

import static com.ajith.rvsqlite.View.ListActivity.TAG;

public class ListPresenter implements IListPresenter {

    private IListView view;
    private IListModelSQLite interacted;
    private Context context;
    
    public ListPresenter(ListActivity view, Context context) {

        this.view = view;
        this.interacted = new TodoRepositorySQLite(context.getContentResolver());
        this.context = context;
    }

    @Override
    public void refreshSession() {
        Log.d(TAG, "refreshSession: " + interacted.get().toString());
        view.setTodo(interacted.get());
    }

    @Override
    public void edit(Todo todo) {
        interacted.create(todo);
    }
    

    @Override
    public void delete(Todo todo) {
        interacted.delete(todo);
        Log.d(TAG, "delete: presenter");
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void create(String s) {

        Todo todo = new Todo();
        todo.setTitle(s);
        interacted.create(todo);
        Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
    }
}
