package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;

import java.io.File;

public class CargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);


        //se crea la base de datos
        BDalzheimer tablas = new BDalzheimer(CargaActivity.this);


        //ponemos en modo escritura
        SQLiteDatabase db =  tablas.getWritableDatabase();
        //validamos que no existan datos cargados
        Cursor cursor=db.rawQuery("select * from plantilla",null);
        //se se cumplen las condiciones precargamos las tablas
        if(db != null && cursor.getCount()<=0){

            //if(){}
            db.execSQL("INSERT INTO plantilla VALUES (null,'memoria','#e7c539');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'lenguaje','#cb74a2');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'orientacion','#a3bd31');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'arencion','#3D6AAC');");
            db.execSQL("INSERT INTO plantilla VALUES (null,'visual','#782d83');");
            db.execSQL("INSERT INTO grupo VALUES (null,'GrupoFamilia','#782d83','bebe');");
            db.execSQL("INSERT INTO grupo VALUES (null,'GrupoMascota','#c85b30','cerdo');");
            db.execSQL("INSERT INTO grupo VALUES (null,'GrupoMusica','#bf0811','violin');");
            db.execSQL("INSERT INTO juego VALUES (null,1,'Mascota','seleccione el sonido que tenga tenga relacion con la  imagen','cerdo','cerdo',null,null);");
            db.execSQL("INSERT INTO juego VALUES (null,1,'familia','seleccione el sonido que tenga tenga relacion con la  imagen','bebe','bebe',null,null);");
            db.execSQL("INSERT INTO juego VALUES (null,1,'Musica','Seleccione sonido del intrumento musicar mostrado en la imagen','violin','violin',null,null);");
            db.execSQL("INSERT INTO juego VALUES (null,1,'Veiculos','Que sonido esta relacionado con la imagen ','carro','carro',null,null);");
            db.close();
        }

        //se crea la carpeta de galeria de imagenes reortadas
        File f = new File(Environment.getExternalStorageDirectory(), "AlzheimerApp");
        if (!f.exists()) {
            f.mkdirs();
        }


        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //si se creo la bd por primera vez
                // le colocamos los datos de la plantilla

            }
        },500);*/

        Intent intent = new Intent(CargaActivity.this, PrincipalActivity.class);
        startActivity(intent);
        this.finish();
    }
}
