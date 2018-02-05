package com.example.cruzmarcano.juego.Propias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cruzmarcano.juego.R;

import java.util.ArrayList;
import java.util.List;

public class SonidoListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewlista;
    private RecyclerViewAdactador adactadorLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido_list);

        recyclerViewlista=(RecyclerView)findViewById(R.id.recyclerSonido);
        recyclerViewlista.setLayoutManager(new LinearLayoutManager(this));
        adactadorLista=new RecyclerViewAdactador(optenrSinidos());
        recyclerViewlista.setAdapter(adactadorLista);
    }
    public  List<ListaMusica> optenrSinidos(){

        List<ListaMusica> sonido=new ArrayList<>();
        sonido.add(new ListaMusica("juan","2:03",R.drawable.galeria_24dp));

        return sonido;
    }
}
