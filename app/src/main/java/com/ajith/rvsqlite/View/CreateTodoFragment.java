package com.ajith.rvsqlite.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ajith.rvsqlite.R;

import static com.ajith.rvsqlite.View.ListActivity.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateTodoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateTodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTodoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CreateTodoFragment() {
        // Required empty public constructor
    }

    static CreateTodoFragment newInstance() {
        return new CreateTodoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_todo, container, false);
        final EditText editText = view.findViewById(R.id.name);
        Button button = view.findViewById(R.id.send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(editText.getText().toString());
            }
        });
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void onButtonPressed(String string) {
        if (mListener != null) {
            mListener.onFragmentInteraction(string);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String string);
    }
}
