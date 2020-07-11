package com.example.ejerciciofarmaciauser;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UbicacionesFragment extends Fragment {

    TextView txtU1, txtU2, txtU3, txtU4;
    String ubic1, ubic2, ubic3, ubic4;

    public UbicacionesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ubic1 = getArguments().getString("ubic1");
        ubic2 = getArguments().getString("ubic2");
        ubic3 = getArguments().getString("ubic3");
        ubic4 = getArguments().getString("ubic4");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ubicaciones, container, false);

        txtU1 = v.findViewById(R.id.txtUbic1);
        txtU2 = v.findViewById(R.id.txtUbic2);
        txtU3 = v.findViewById(R.id.txtUbic3);
        txtU4 = v.findViewById(R.id.txtUbic4);


        txtU1.setText(ubic1);
        txtU2.setText(ubic2);
        txtU3.setText(ubic3);
        txtU4.setText(ubic4);

        return v;
    }
}
