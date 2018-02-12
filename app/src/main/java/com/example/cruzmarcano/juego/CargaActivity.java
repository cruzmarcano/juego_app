package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cruzmarcano.juego.datos.BDalzheimer;

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
            db.execSQL("INSERT INTO plantilla VALUES (null,'cartas');");
            db.execSQL("INSERT INTO grupo(grupo_nomb, grupo_color, grupo_imag) VALUES ('familia','verde','imagen1.jpg');");
            db.execSQL("INSERT INTO jue_grup(juego_fk, grupo_fk) VALUES (1,1);");
            db.execSQL("INSERT INTO juego VALUES(null, 1, 'carta','cartas para jugar','dato1.jpg',null,null,null);");
            db.close();
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
