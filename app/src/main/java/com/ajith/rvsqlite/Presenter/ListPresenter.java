package com.ajith.rvsqlite.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.ajith.rvsqlite.Model_Firebase.IListModelFirebase;
import com.ajith.rvsqlite.Model_Firebase.TodoRepositoryFirebase;
import com.ajith.rvsqlite.Model_SQLite.Entity.Todo;
import com.ajith.rvsqlite.Model_SQLite.TodoRepositorySQLite;
import com.ajith.rvsqlite.View.IListView;
import com.ajith.rvsqlite.View.ListActivity;
import com.ajith.rvsqlite.Model_SQLite.IListModelSQLite;

import java.util.Objects;

import static com.ajith.rvsqlite.View.ListActivity.TAG;

public class ListPresenter implements IListPresenter {

    private IListView view;
    private IListModelSQLite modelSQLite;
    private IListModelFirebase modelFirebase;
    private Context context;

    public ListPresenter(ListActivity view, Context context) {

        this.view = view;
        this.modelSQLite = new TodoRepositorySQLite(context.getContentResolver());
        modelFirebase = new TodoRepositoryFirebase();
        this.context = context;
    }

    @Override
    public void refreshSession() {
        Log.d(TAG, "refreshSession: " + modelSQLite.get().toString());
        view.setTodo(modelSQLite.get());
    }

    @Override
    public void edit(Todo todo) {

        if (checkConnection()){
            modelSQLite.update(todo);
            modelFirebase.updateFire(todo);
        }else {
            Toast.makeText(context, "Please Check your connection", Toast.LENGTH_SHORT).show();
        }
    }
    

    @Override
    public void delete(Todo todo) {

        if (checkConnection()){
            modelSQLite.delete(todo);
            modelFirebase.deleteFire(todo);
        }else {
            Toast.makeText(context, "Please Check your connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void create(String s) {

        Todo todo = new Todo();
        todo.setTitle(s);

        if (checkConnection()){
            modelSQLite.create(todo);
            modelFirebase.createFire(todo.getId(), s);
        }else {
            Toast.makeText(context, "Please Check your connection", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }
}
