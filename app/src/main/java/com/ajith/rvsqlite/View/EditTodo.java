package com.ajith.rvsqlite.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ajith.rvsqlite.R;


public class EditTodo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String name;
    private String id;

    private OnEditFragmentInteractionListener mListener;

    public EditTodo() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    static EditTodo newInstance(String param1, String param2) {
        EditTodo fragment = new EditTodo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            id = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_todo, container, false);

        final EditText editText = view.findViewById(R.id.name1);
        Button button = view.findViewById(R.id.send1);

        editText.setText(name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(editText.getText().toString(), id);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void onButtonPressed(String name, String id) {
        if (mListener != null) {
            mListener.onEditFragmentInteraction(name, id);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnEditFragmentInteractionListener) {
            mListener = (OnEditFragmentInteractionListener) context;
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

    public interface OnEditFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEditFragmentInteraction(String name, String id);
    }
}
