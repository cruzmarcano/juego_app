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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CargaActivity.this, MemoriaActivity.class);
                startActivity(intent);

                //se crea la base de datos
                BDalzheimer tablas = new BDalzheimer(CargaActivity.this,"Alzhaimer",  null,1 );

                //ponemos en modo escritura
                SQLiteDatabase db =  tablas.getWritableDatabase();

                //si se creo la bd por primera vez
                // le colocamos los datos de la plantilla
                if (db != null){
                    db.execSQL("INSERT INTO plantilla(planti_id, planti_nombre) VALUES (null, 'cartas');");
                    db.execSQL("INSERT INTO recurso(recur_id, recur_nomb) VALUES (null, 'cocina.jpg');");
                    db.execSQL("INSERT INTO grupo(grupo_id, grupo_nomb, grupo_color, grupo_imag) VALUES (null, 'familia','verde',1);");
                    db.execSQL("INSERT INTO juego(juego_id, planti_fk, juego_nomb, juego_instr,\n" +
                               "\t juego_pre_resp,juego_pre_resp2,juego_pre_resp3,juego_pre_resp4) VALUES (null, 1,\n" +
                               "\t'cartas_1','seleccione un carta',1,1,1,1);");
                    db.execSQL("INSERT INTO jue_grup(jue_grup, juego_fk, grupo_fk) VALUES (null, 1,1);");

                }
            }
        },2000);
    }
}
