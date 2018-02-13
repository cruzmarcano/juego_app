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
            Long respuesta1 =i.insertarDatos(TablasCampos.TABLA_PLANTILLA,plantillaDatos,TablasCampos.PLANTILLA_ID);
            Toast.makeText(this," resultado="+respuesta1,Toast.LENGTH_LONG).show();
            //datos precargados tabala grupo
            ContentValues grupoDatos=new ContentValues();
            grupoDatos.put(TablasCampos.GRUPO_NOMBRE,"Mascotas");
            grupoDatos.put(TablasCampos.GRUPO_COLOR,"#98FB98");
            grupoDatos.put(TablasCampos.GRUPO_IMAGEN,"mascota.jpg");
            Long respuesta =i.insertarDatos(TablasCampos.TABLA_GRUPO,grupoDatos,TablasCampos.GRUPO_ID);
            Toast.makeText(this," resultado="+respuesta,Toast.LENGTH_LONG).show();

            db.close();

            //db.execSQL("INSERT INTO plantilla VALUES (null,'cartas');");
            //db.execSQL("INSERT INTO grupo(grupo_nomb, grupo_color, grupo_imag) VALUES ('familia','verde','imagen1.jpg');");
            //db.execSQL("INSERT INTO jue_grup(juego_fk, grupo_fk) VALUES (1,1);");
           // db.execSQL("INSERT INTO juego VALUES(null, 1, 'carta','cartas para jugar','dato1.jpg',null,null,null);");
            //db.execSQL(TablasCampos.CREAR_TABLA_PLANTILLA);

        }

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {






                //si se creo la bd por primera vez
                // le colocamos los datos de la plantilla


            }
        },500);*/

        //Intent intent = new Intent(CargaActivity.this, MemoriaActivity.class);
        //startActivity(intent);
    }
}
