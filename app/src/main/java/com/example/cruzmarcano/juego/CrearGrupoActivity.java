package com.example.cruzmarcano.juego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cruzmarcano.juego.adaptadores.CrearGrupoAdacter;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;

public class CrearGrupoActivity extends AppCompatActivity {
    RecyclerView lista;
    TablasCampos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        datos=new TablasCampos(CrearGrupoActivity.this);

        lista=(RecyclerView)findViewById(R.id.recyclerGrupo);

        lista.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        lista.setLayoutManager(linearLayoutManager);

        CrearGrupoAdacter crearGrupoAdacter=new CrearGrupoAdacter(getApplicationContext(),datos.selecEjercicios());
        lista.setAdapter(crearGrupoAdacter);
    }
}
