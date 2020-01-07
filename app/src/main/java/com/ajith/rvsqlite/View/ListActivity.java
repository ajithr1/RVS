package com.ajith.rvsqlite.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ajith.rvsqlite.common.Entity.Testing;
import com.ajith.rvsqlite.common.Entity.Todo;
import com.ajith.rvsqlite.Presenter.IListPresenter;
import com.ajith.rvsqlite.Presenter.ListPresenter;
import com.ajith.rvsqlite.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements  CreateTodoFragment.OnFragmentInteractionListener, EditTodo.OnEditFragmentInteractionListener,IListView {

    public static final String TAG = "ajju";

    public static final String STATE_LIST = "TodoList";

    private IListPresenter presenter;

    private ListAdapter listAdapter;

    private Todo todo;

    public IListPresenter getPresenter() {
        if (presenter == null) {
            presenter = new ListPresenter(this, this);
        }
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);

        if (savedInstanceState == null) {
            listAdapter = new ListAdapter(this, new ArrayList<Todo>(), onTodoClickToEditListener, onEditItemInterface);
        } else {
            ArrayList<Todo> todoArrayList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            listAdapter = new ListAdapter(this, todoArrayList, onTodoClickToEditListener, onEditItemInterface);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        // Presenter
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getPresenter().refreshSession();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().refreshSession();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, listAdapter.getTodo_list());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setTodo(ArrayList<Todo> todo) {
        listAdapter.setTodo_list(todo);
    }

    public void create(View view) {
        Log.d(TAG, "create: button Clicked");
        FragmentManager fragmentManager = getSupportFragmentManager();
        CreateTodoFragment newTodo = CreateTodoFragment.newInstance();
        fragmentManager.beginTransaction().addToBackStack(null).add(R.id.con, newTodo).commit();
    }

    private ListAdapter.TodoViewHolder.DeleteItemInterface onTodoClickToEditListener = new ListAdapter.TodoViewHolder.DeleteItemInterface() {
        @Override
        public void deleteItem(Todo todo, int position) {
            Log.d(TAG, "deleteItem: delete activity");
            getPresenter().delete(todo);
        }
    };
    
    private ListAdapter.TodoViewHolder.EditItemInterface onEditItemInterface = new ListAdapter.TodoViewHolder.EditItemInterface() {
        @Override
        public void editItem(Todo mTodo, int position) {
            Log.d(TAG, "editItem: edit activity");

            todo = mTodo;

            Log.d(TAG, "editItem: "+todo.getId());

            FragmentManager fragmentManager = getSupportFragmentManager();
            EditTodo editTodo = EditTodo.newInstance(todo.getTitle(), String.valueOf(todo.getId()));
            fragmentManager.beginTransaction().addToBackStack(null).add(R.id.con, editTodo).commit();
        }
    };

    @Override
    public void onFragmentInteraction(String string) {
        getPresenter().create(string);
        onBackPressed();
        getPresenter().refreshSession();
    }

    @Override
    public void onEditFragmentInteraction(String name, String id) {
        todo.setTitle(name);
        todo.setId(Long.valueOf(id));
        getPresenter().edit(todo);
        onBackPressed();
        getPresenter().refreshSession();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.test){
            startActivity(new Intent(this, Testing.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
