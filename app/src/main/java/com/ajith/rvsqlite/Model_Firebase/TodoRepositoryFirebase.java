package com.ajith.rvsqlite.Model_Firebase;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajith.rvsqlite.Model_SQLite.Entity.Todo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class TodoRepositoryFirebase implements IListModelFirebase {

    private static DatabaseReference fireDb = FirebaseDatabase.getInstance().getReference("Todo");

    @Override
    public void getFire(final RecyclerView recyclerView) {

        fireDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<Todo> list = new ArrayList<>();
                list.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Todo to = postSnapshot.getValue(Todo.class);
                    //adding artist to the list
                    list.add(to);
                    assert to != null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void createFire(long id, String name) {
        //String key = fireDb.push().getKey();

        Todo to = new Todo(id, name);
        fireDb.child(String.valueOf(id)).setValue(to);
    }

    @Override
    public void updateFire(Todo to) {
        fireDb.child(String.valueOf(to.getId())).setValue(to);
    }

    @Override
    public void deleteFire(Todo todo) {
        fireDb.child(String.valueOf(todo.getId())).removeValue();
    }
}
