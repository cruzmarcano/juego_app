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
    List<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        datos=new ArrayList<>();
        datos.add("#3F51B5");
        datos.add("#B45F04");
        datos.add("#ff4081");
        datos.add("#e7c539");
        datos.add("#c85b30");
        datos.add("#76aadb");
        datos.add("#088A08");
        datos.add("#BF0811");
        datos.add("#DFA94A");
        datos.add("#cb74a2");
        datos.add("#5b5b5f");
        datos.add("#3D6AAC");
        datos.add("#782d83");
        datos.add("#a3bd31");
        datos.add("#cbc9c9");
        datos.add("#5fa199");






        recyclerView=(RecyclerView)findViewById(R.id.recyclerColor);
        recyclerView.setHasFixedSize(true);
        //esto le dice como se debe mostrar el CardView en este caso se mostrara como cuadricuala de 4
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ColorActivity.this,4);
        recyclerView.setLayoutManager(gridLayoutManager);

        ColorAdacter adactador=new ColorAdacter(ColorActivity.this, datos);
        recyclerView.setAdapter(adactador);
    }
}
