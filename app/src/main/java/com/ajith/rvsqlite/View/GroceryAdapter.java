package com.ajith.rvsqlite.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Presenter.Presenter;
import com.ajith.rvsqlite.Presenter.PresenterInt;
import com.ajith.rvsqlite.R;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private Context context;
    private PresenterInt p;

    private static final String TAG = "ajju";

    GroceryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        p = new Presenter();
        Log.d(TAG, p.getItemAt(position, context));
        holder.textView.setText(p.getItemAt(position, context));
    }

    @Override
    public int getItemCount() {
        p = new Presenter();
        Log.d(TAG, String.valueOf(p.getItemCount(context)));
        return p.getItemCount(context);
    }

    class GroceryViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.grocery_name);
        }
    }
}
