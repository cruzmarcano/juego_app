package com.example.cruzmarcano.juego;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cruzmarcano.juego.adaptadores.CrearGrupoAdacter;
import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;

import java.util.ArrayList;

public class CrearGrupoActivity extends AppCompatActivity  {
    RecyclerView lista;
    TablasCampos datos;

    EditText nombre;
    String imagen;
    String color;
    Button guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        datos=new TablasCampos(CrearGrupoActivity.this);
        nombre=(EditText)findViewById(R.id.nombGrupo);
        guardar=(Button)findViewById(R.id.guardarGrup);

        lista=(RecyclerView)findViewById(R.id.recyclerGrupo);


        lista.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        lista.setLayoutManager(linearLayoutManager);

        CrearGrupoAdacter crearGrupoAdacter=new CrearGrupoAdacter(getApplicationContext(),datos.selecEjercicios());
        lista.setAdapter(crearGrupoAdacter);

        //datos provicionales
        color="#c85b30";
        imagen="gato";



    }


}


