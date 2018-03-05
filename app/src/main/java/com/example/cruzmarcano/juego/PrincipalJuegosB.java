package com.example.cruzmarcano.juego;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class PrincipalJuegosB extends Fragment {
    private TextView nombre, instruccion;
    private ImageButton imagen,sonido;
    private Button guardar;
private ContentValues juegoDatos=new ContentValues();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View raiz=inflater.inflate(R.layout.principal_juegos_b, container, false);
        //cateamos botones y camps de texto
        nombre=(TextView)raiz.findViewById(R.id.atenNomb);
        instruccion=(TextView)raiz.findViewById(R.id.atenInst);
        imagen=(ImageButton)raiz.findViewById(R.id.ateImaBtn1);
        sonido=(ImageButton)raiz.findViewById(R.id.ateImaBtn2);
        guardar=(Button)raiz.findViewById(R.id.guardarB);




        return raiz;
    }

}
