package com.ajith.rvsqlite.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Model.Entity.Todo;
import com.ajith.rvsqlite.R;

import java.util.ArrayList;

import static com.ajith.rvsqlite.List.ListActivity.TAG;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Todo> todo_list;
    private Context context;

    private static final int TYPE_NO_TODO = 0;
    private static final int TYPE_TODO = 1;

    ListAdapter(Context context, ArrayList<Todo> todo_list) {
        this.context = context;
        this.todo_list = todo_list;
    }

    void setTodo_list(ArrayList<Todo> todo_list) {
        this.todo_list = todo_list;
        notifyDataSetChanged();
    }

    ArrayList<Todo> getTodo_list() {
        return todo_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO_TODO:
                View noTodoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_todo, parent, false);
                return new NoTodoViewHolder(noTodoView);
            case TYPE_TODO:
                View todoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new TodoViewHolder(todoView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_NO_TODO:
                break;
            case TYPE_TODO:
                final Todo todo = todo_list.get(position);
                TodoViewHolder todoViewHolder = (TodoViewHolder) holder;
                // Data
                todoViewHolder.title.setText(todo.getTitle());
                Log.d(TAG, "onBindViewHolder: "+todo.getTitle());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: "+todo_list.size());
        return (todo_list.size() == 0) ? TYPE_NO_TODO : TYPE_TODO;
    }

    @Override
    public int getItemCount() {
        return (todo_list.size() == 0) ? 1 : todo_list.size();
    }

    /**
     * To.Do ViewHolder
     */
    static class TodoViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout cardBackground;
        CardView card;
        TextView title;

        TodoViewHolder(View itemView) {
            super(itemView);
            cardBackground = itemView.findViewById(R.id.recyclerView);
            card = itemView.findViewById(R.id.card_todo);
            title = itemView.findViewById(R.id.grocery_name);
        }
    }

    /**
     * If there are no To.Do's, use this ViewHolder
     */
    static class NoTodoViewHolder extends RecyclerView.ViewHolder {

        TextView noTodoText;

        NoTodoViewHolder(View itemView) {
            super(itemView);
            noTodoText = itemView.findViewById(R.id.no_text);
        }
    }
}
