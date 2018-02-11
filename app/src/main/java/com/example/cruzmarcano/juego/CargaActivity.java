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
            db.execSQL("INSERT INTO plantilla(planti_nombre) VALUES ('cartas');");
            db.execSQL("INSERT INTO recurso(recur_nomb) VALUES ( 'cocina.jpg');");
            db.execSQL("INSERT INTO grupo(grupo_nomb, grupo_color, grupo_imag) VALUES ('familia','verde',1);");
            db.execSQL("INSERT INTO jue_grup(juego_fk, grupo_fk) VALUES (1,1);");
            db.execSQL("INSERT INTO juego(planti_fk, juego_nomb, juego_instr,juego_pre_resp1_fk,juego_pre_resp2_fk,juego_pre_resp3_fk,juego_pre_resp4_fk) VALUES (1,'cartas_1','seleccione un carta',1,1,1,1);");

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
