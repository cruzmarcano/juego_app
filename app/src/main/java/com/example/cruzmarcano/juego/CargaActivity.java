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
        BDalzheimer tablas = new BDalzheimer(CargaActivity.this);


        //ponemos en modo escritura
        SQLiteDatabase db =  tablas.getWritableDatabase();
        if(db != null){
            db.execSQL("INSERT INTO plantilla VALUES (null,'memoria','#e7c539');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'lenguaje','#cb74a2');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'orientacion','#a3bd31');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'arencion','#3D6AAC');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'visual','#782d83');");
            db.execSQL("INSERT INTO juego VALUES (null,'mascota',1,'sleccione imagen','dato1',null,null,null);");
            db.execSQL("INSERT INTO juego VALUES (null,'familia',1,'sleccione imagen','dato2',null,null,null);");
            db.execSQL("INSERT INTO juego VALUES (null,'comida',1,'sleccione imagen','dato3',null,null,null);");
            db.execSQL("INSERT INTO juego VALUES (null,'canciones',1,'sleccione imagen','dato4',null,null,null);");
            db.close();
        }

        /*

        if (db != null){
            TablasCampos i=new TablasCampos(CargaActivity.this);

            //datos precargados tabala plantilla
            ContentValues plantillaDatos=new ContentValues();
            plantillaDatos.put(BDalzheimer.PLANTILLA_NOMBRE,"Cartas");
            i.insertarDatos(BDalzheimer.TABLA_PLANTILLA,plantillaDatos,BDalzheimer.PLANTILLA_ID);


            //datos precargados tabala grupo
            ContentValues grupoDatos=new ContentValues();
            grupoDatos.put(BDalzheimer.GRUPO_NOMBRE,"Mascotas");
            grupoDatos.put(BDalzheimer.GRUPO_COLOR,"#98FB98");
            grupoDatos.put(BDalzheimer.GRUPO_IMAGEN,"mascota.jpg");
            i.insertarDatos(BDalzheimer.TABLA_GRUPO,grupoDatos,BDalzheimer.GRUPO_ID);


            //datos precargados tabala juego
            ContentValues juegoDatos=new ContentValues();
            juegoDatos.put(BDalzheimer.JUEGO_NOMBRE,"Mascota");
            juegoDatos.put(BDalzheimer.JUEGO_PLANTI_FK,1);
            juegoDatos.put(BDalzheimer.JUEGO_INSTRUCCION,"seleccione el sonido que concida con la imagen");
            juegoDatos.put(BDalzheimer.JUEGO_DATOS1,"perro.jpg");
            juegoDatos.put(BDalzheimer.JUEGO_DATOS2,"sonido.mp3");
            i.insertarDatos(BDalzheimer.TABLA_JUEGO,juegoDatos,BDalzheimer.JUEGO_ID);

            //datos precargados tabala juegoGrupo
            ContentValues juegoGrupoDatos=new ContentValues();
            juegoGrupoDatos.put(BDalzheimer.JG_GRUPO_FK,1);
            juegoGrupoDatos.put(BDalzheimer.JG_JUEGO_FK,1);
            i.insertarDatos(BDalzheimer.TABLA_JUEGO_GRUPO,juegoGrupoDatos,BDalzheimer.JUEGO_GRUPO_ID);

            db.close();
            //usar esta manera de cargar datos solo en la carga
            //db.execSQL("INSERT INTO plantilla VALUES (null,'cartas');");

        } */

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
