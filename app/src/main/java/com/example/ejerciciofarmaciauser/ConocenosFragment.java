package com.example.ejerciciofarmaciauser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConocenosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConocenosFragment extends Fragment {

    private TextView txtCF;
    private String conocenos;

    public ConocenosFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        conocenos = getArguments().getString("conocenos");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conocenos, container, false);
        txtCF=  v.findViewById(R.id.txtConocenos);
        txtCF.setText(conocenos);

        return v;
    }
}
