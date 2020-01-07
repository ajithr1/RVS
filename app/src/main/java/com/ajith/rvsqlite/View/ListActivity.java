package com.ajith.rvsqlite.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Model.Entity.Todo;
import com.ajith.rvsqlite.Presenter.IListPresenter;
import com.ajith.rvsqlite.Presenter.ListPresenter;
import com.ajith.rvsqlite.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements  CreateTodoFragment.OnFragmentInteractionListener, IListView {

    public static final String TAG = "ajju";

    public static final String STATE_LIST = "TodoList";

    private IListPresenter presenter;

    private ListAdapter listAdapter;

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
        if (savedInstanceState == null) {
            listAdapter = new ListAdapter(this, new ArrayList<Todo>(), onTodoClickToEditListener);
        } else {
            ArrayList<Todo> todoArrayList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            listAdapter = new ListAdapter(this, todoArrayList, onTodoClickToEditListener);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        // Presenter
        getPresenter().refreshSession();
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

    @Override
    public void notifyListDataSetChanged() {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyListItemRemoved(int position) {
        listAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyListItemInserted(int position) {
        listAdapter.notifyItemInserted(position);
    }

    @Override
    public void showItemDialog(final Todo todo, final CharSequence[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which] == getString(R.string.edit)) {
                    presenter.onClickTodoItemToEdit(todo);
                }
                if (items[which] == getString(R.string.delete)) {
                    presenter.delete(todo);
                }
            }
        });
        builder.show();
    }

    @Override
    public void showTodoViewToEdit(Todo todo) {

    }

    @Override
    public void showTodoView() {

    }

    @Override
    public void onFragmentInteraction(String string) {
        Log.d(TAG, "onFragmentInteraction: string sent"+string);
        getPresenter().create(string);
        onBackPressed();
        getPresenter().refreshSession();
    }

    public void create(View view) {
        Log.d(TAG, "create: button Clicked");
        FragmentManager fragmentManager = getSupportFragmentManager();
        CreateTodoFragment createTodoFragment = CreateTodoFragment.newInstance();
        fragmentManager.beginTransaction().addToBackStack(null).add(R.id.con, createTodoFragment).commit();
    }

    private ListAdapter.TodoViewHolder.IOnClickToEditListener onTodoClickToEditListener = new ListAdapter.TodoViewHolder.IOnClickToEditListener() {
        @Override
        public void onClickToEditListener(Todo todo, int position) {
            Log.d(TAG, "onClickToEditListener: delete activity");
            getPresenter().delete(todo);
        }
    };
}
