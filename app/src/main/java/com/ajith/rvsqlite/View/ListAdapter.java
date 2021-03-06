package com.ajith.rvsqlite.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Model_SQLite.Entity.Todo;
import com.ajith.rvsqlite.R;

import java.util.ArrayList;

import static com.ajith.rvsqlite.View.ListActivity.TAG;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Todo> todo_list;
    private Context context;

    private TodoViewHolder.DeleteItemInterface todoDeleteItemInterface;
    private TodoViewHolder.EditItemInterface editItemInterface;

    private static final int TYPE_NO_TODO = 0;
    private static final int TYPE_TODO = 1;

    ListAdapter(Context context, ArrayList<Todo> todo_list, TodoViewHolder.DeleteItemInterface todoDeleteItemInterface, TodoViewHolder.EditItemInterface editItemInterface) {
        Log.d(TAG, "ListAdapter: constructor");
        this.context = context;
        this.todo_list = todo_list;
        this.todoDeleteItemInterface = todoDeleteItemInterface;
        this.editItemInterface = editItemInterface;
    }

    void setTodo_list(ArrayList<Todo> todo_list) {
        Log.d(TAG, "list adapter");
        this.todo_list = todo_list;
        notifyDataSetChanged();
    }

    ArrayList<Todo> getTodo_list() {
        Log.d(TAG, "getTodo_list: ");
        return todo_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO_TODO:
                Log.d(TAG, "onCreateViewHolder: NO TODO");
                View noTodoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_todo, parent, false);
                return new NoTodoViewHolder(noTodoView);
            case TYPE_TODO:
                Log.d(TAG, "onCreateViewHolder: TODO");
                View todoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new TodoViewHolder(todoView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case TYPE_NO_TODO:
                break;
            case TYPE_TODO:
                final TodoViewHolder todoViewHolder = (TodoViewHolder) holder;
                final Todo todo = todo_list.get(position);
                // Data
                todoViewHolder.title.setText(todo.getTitle());
                Log.d(TAG, "onBindViewHolder: "+todo.getTitle());

                todoViewHolder.menu_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(context, todoViewHolder.menu_text);
                        popupMenu.inflate(R.menu.card_options_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()){
                                    case R.id.edit_option:
                                        editItemInterface.editItem(todo, position);
                                        Toast.makeText(context, todo.getTitle(), Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.delete_option:
                                        Log.d(TAG, "delete:  adapter"+todo.getId());
                                        todoDeleteItemInterface.deleteItem(todo, position);
                                        todo_list.remove(position);
                                        notifyDataSetChanged();
                                        break;
                                }
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });
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
        Log.d(TAG, "getItemCount: "+todo_list.size());
        return (todo_list.size() == 0) ? 1 : todo_list.size();
    }

    /**
     * To.Do ViewHolder
     */
    static class TodoViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout cardBackground;
        CardView card;
        TextView title;
        ImageButton menu_text;

        TodoViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "TodoViewHolder: ");
            cardBackground = itemView.findViewById(R.id.recyclerView);
            card = itemView.findViewById(R.id.card_todo);
            title = itemView.findViewById(R.id.grocery_name);
            menu_text = itemView.findViewById(R.id.options_text);
        }

        /**
         * Interface to delete
         */
        public interface DeleteItemInterface {

            void deleteItem(Todo todo, int position);

        }

        /**
         * Interface to delete
         */
        public interface EditItemInterface {

            void editItem(Todo todo, int position);

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
