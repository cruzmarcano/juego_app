package com.example.cruzmarcano.juego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cruzmarcano.juego.adaptadores.ColorAdacter;

import java.util.ArrayList;
import java.util.List;

public class ColorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        datos=new ArrayList<>();
        datos.add("#76aadb");
        datos.add("#3d6aac");
        datos.add("#BF0811");



        recyclerView=(RecyclerView)findViewById(R.id.recyclerColor);
        recyclerView.setHasFixedSize(true);
        //esto le dice como se debe mostrar el CardView en este caso se mostrara como cuadricuala de 4
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ColorActivity.this,4);
        recyclerView.setLayoutManager(gridLayoutManager);

        ColorAdacter adactador=new ColorAdacter(ColorActivity.this, datos);
        recyclerView.setAdapter(adactador);
    }
}
