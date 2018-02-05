package com.example.cruzmarcano.juego;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class ListaActivity extends AppCompatActivity {
    //ruta de la carpeta
    private  String ruta = Environment.getExternalStorageDirectory()+File.separator+"Sounds";
    ListView lista;
    String[] valores;
    String files;
    MediaPlayer mp;
    Uri direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //la clase File proporciona informacion de los archivos
        // pide como parametro la ruta del archivo a inspencionar
        File folder = new File(ruta);
        //Lista los archivos
        File[] listaVoz = folder.listFiles();
        //defino tamaño de arreglo
        valores=new String[listaVoz.length];





        //lleno el arreglo con la informacion de la carpeta inspecionada
        for (int i = 0; i < listaVoz.length; i++)         {

            if (listaVoz[i].isFile())             {
                valores[i] = new String(listaVoz[i].getName());
            }
        }


        //Castamos la lista que se va a mostrar
        lista=(ListView)findViewById(R.id.lista);

        //creamos un adactador que dira la forma como se muestra la informacion
        ArrayAdapter<String> adactador =new ArrayAdapter<String>(ListaActivity.this,android.R.layout.simple_expandable_list_item_1,valores);

        //agregamos el adacter al listView
        lista.setAdapter(adactador);
        // creamos la accion cuando se preciona cada item
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                direccion= Uri.parse(ruta+File.separator+valores[i]);
                mp = MediaPlayer.create(ListaActivity.this,direccion);
                mp.start();

            }
        });


    }
}
