package com.example.cruzmarcano.juego;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cruzmarcano.juego.adaptadores.CrearGrupoAdacter;
import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;
import com.facebook.stetho.Stetho;

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
        Stetho.initializeWithDefaults(this);

        datos=new TablasCampos(CrearGrupoActivity.this);
        nombre=(EditText)findViewById(R.id.nombGrupo);
        guardar=(Button)findViewById(R.id.guardarGrup);

        lista=(RecyclerView)findViewById(R.id.recyclerGrupo);


        lista.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        lista.setLayoutManager(linearLayoutManager);

        final CrearGrupoAdacter crearGrupoAdacter=new CrearGrupoAdacter(getApplicationContext(),datos.selecEjercicios());
        lista.setAdapter(crearGrupoAdacter);

        //datos provicionales
        color="#c85b30";
        imagen="gato";

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> r=crearGrupoAdacter.getNumbers();
                for (int i=0; i<r.size();i++){
                    Log.v("prueba", Integer.toString(r.get(i)));
                }
                String res=Integer.toString(r.get(0));
                Toast.makeText(CrearGrupoActivity.this,"el nuemero es="+res,Toast.LENGTH_LONG).show();
            }
        });



    }


}


