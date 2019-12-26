package com.ajith.rvsqlite.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ajith.rvsqlite.R;

import java.util.Objects;

public class CreateList extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText editText;
    private String entered_name;

    public CreateList() {
        // Required empty public constructor
    }

    static CreateList newInstance() {
        return new CreateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_list, container, false);
        editText = view.findViewById(R.id.name);
        Button b = view.findViewById(R.id.send);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entered_name = editText.getText().toString();
                onButtonPressed(entered_name);
            }
        });
        return view;
    }

    private void onButtonPressed(String s) {
        if (mListener != null) {
            mListener.onFragmentInteraction(s);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(Objects.requireNonNull(context));
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String s);
    }
}
