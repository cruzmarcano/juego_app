package com.example.cruzmarcano.juego;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cruzmarcano.juego.adaptadores.EjercicioAdapter;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;

import java.util.ArrayList;



public class PrincipalEjercicios extends Fragment {
    RecyclerView listaEjercicio;
    TablasCampos datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        datos=new TablasCampos(getContext());

        //al crear un fragment este deve devolver una vista por eso se crea el vistaRaiz que crea infla
        View vistaRaiz = inflater.inflate(R.layout.principal_ejercicios, container, false);
        //cateamos el recyclerView
        listaEjercicio=(RecyclerView)vistaRaiz.findViewById(R.id.recyclerEjercicios);
        //esta linea le dice que todos los elementos ocuparan el mismo espacio
        listaEjercicio.setHasFixedSize(true);
        //el layoutManayer gestiona la disposicion de los elementos en la vista diseño

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        listaEjercicio.setLayoutManager(gridLayoutManager);

        EjercicioAdapter adactador=new EjercicioAdapter(getContext(), datos.selecEjercicios());
        listaEjercicio.setAdapter(adactador);







        return vistaRaiz;

    }

}
