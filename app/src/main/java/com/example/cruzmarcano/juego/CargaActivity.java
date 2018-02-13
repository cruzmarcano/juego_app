package com.example.cruzmarcano.juego;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;

public class CargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);


        //se crea la base de datos
        BDalzheimer tablas = new BDalzheimer(CargaActivity.this,"Alzhaimer",  null,1 );

        //ponemos en modo escritura
        SQLiteDatabase db =  tablas.getWritableDatabase();


        if (db != null){
            TablasCampos i=new TablasCampos(db);

            //datos precargados tabala plantilla
            ContentValues plantillaDatos=new ContentValues();
            plantillaDatos.put(TablasCampos.PLANTILLA_NOMBRE,"Cartas");
            i.insertarDatos(TablasCampos.TABLA_PLANTILLA,plantillaDatos,TablasCampos.PLANTILLA_ID);


            //datos precargados tabala grupo
            ContentValues grupoDatos=new ContentValues();
            grupoDatos.put(TablasCampos.GRUPO_NOMBRE,"Mascotas");
            grupoDatos.put(TablasCampos.GRUPO_COLOR,"#98FB98");
            grupoDatos.put(TablasCampos.GRUPO_IMAGEN,"mascota.jpg");
            i.insertarDatos(TablasCampos.TABLA_GRUPO,grupoDatos,TablasCampos.GRUPO_ID);


            //datos precargados tabala juego
            ContentValues juegoDatos=new ContentValues();
            juegoDatos.put(TablasCampos.JUEGO_NOMBRE,"Mascota");
            juegoDatos.put(TablasCampos.JUEGO_PLANTI_FK,1);
            juegoDatos.put(TablasCampos.JUEGO_INSTRUCCION,"seleccione el sonido que concida con la imagen");
            juegoDatos.put(TablasCampos.JUEGO_DATOS1,"perro.jpg");
            juegoDatos.put(TablasCampos.JUEGO_DATOS2,"sonido.mp3");
            i.insertarDatos(TablasCampos.TABLA_JUEGO,juegoDatos,TablasCampos.JUEGO_ID);

            //datos precargados tabala juegoGrupo
            ContentValues juegoGrupoDatos=new ContentValues();
            juegoGrupoDatos.put(TablasCampos.JG_GRUPO_FK,1);
            juegoGrupoDatos.put(TablasCampos.JG_JUEGO_FK,1);
            i.insertarDatos(TablasCampos.TABLA_JUEGO_GRUPO,juegoGrupoDatos,TablasCampos.JUEGO_GRUPO_ID);

            db.close();
            //usar esta manera de cargar datos solo en la carga
            //db.execSQL("INSERT INTO plantilla VALUES (null,'cartas');");

        }

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {






                //si se creo la bd por primera vez
                // le colocamos los datos de la plantilla


            }
        },500);*/

        Intent intent = new Intent(CargaActivity.this, MemoriaActivity.class);
        startActivity(intent);
    }
}
