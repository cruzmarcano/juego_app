package com.example.cruzmarcano.juego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PrincipalJuegosA extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View raiz=inflater.inflate(R.layout.principal_juegos_a, container, false);
        // Inflate the layout for this fragment
        TextView texto=(TextView)raiz.findViewById(R.id.textoA);

        return raiz;
    }


}
