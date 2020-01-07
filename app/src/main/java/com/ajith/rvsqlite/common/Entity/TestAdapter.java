package com.ajith.rvsqlite.common.Entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    ArrayList<Todo> test_list;

    public TestAdapter(ArrayList<Todo> test_list) {
        this.test_list = test_list;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Todo todo = test_list.get(position);

        holder.textView.setText(todo.getTitle());
    }

    @Override
    public int getItemCount() {
        return test_list.size();
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.grocery_name);
        }
    }
}
