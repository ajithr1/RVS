package com.ajith.rvsqlite.View;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Model.GroceryContract;
import com.ajith.rvsqlite.R;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private Context context;
    private Cursor cursor;

    GroceryAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
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

        if (!cursor.moveToPosition(position)){
            return;
        }else {
            String nameText = cursor.getString(cursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
            holder.textView.setText(nameText);
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class GroceryViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.grocery_name);
        }
    }

    void swapCursor(Cursor newCursor){

        if (cursor != null){
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
