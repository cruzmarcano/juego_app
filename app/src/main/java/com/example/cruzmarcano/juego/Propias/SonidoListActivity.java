package com.example.cruzmarcano.juego.Propias;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.cruzmarcano.juego.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SonidoListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewlista;
    private RecyclerViewAdactador adactadorLista;
    private  String ruta = Environment.getExternalStorageDirectory()+ File.separator+"Sounds";
    private File[] listaVoz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido_list);

        recyclerViewlista=(RecyclerView)findViewById(R.id.recyclerSonido);
        recyclerViewlista.setLayoutManager(new LinearLayoutManager(this));
        adactadorLista=new RecyclerViewAdactador(optenrSinidos());
        recyclerViewlista.setAdapter(adactadorLista);

        //la clase File proporciona informacion de los archivos
        // pide como parametro la ruta del archivo a inspencionar
        File folder = new File(ruta);
        //Lista los archivos
        listaVoz = folder.listFiles();

        //lleno el arreglo con la informacion de la carpeta inspecionada

    }
    public  List<ListaMusica> optenrSinidos(){

        List<ListaMusica> sonido=new ArrayList<>();




            sonido.add(new ListaMusica("dfdfd","3:32"));




        return sonido;
    }
}
