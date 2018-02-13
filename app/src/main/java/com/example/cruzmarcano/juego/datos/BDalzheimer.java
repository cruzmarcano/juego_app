package com.example.cruzmarcano.juego.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cruzmarcano.juego.utilidades.TablasCampos;

import java.util.List;

/**
 * Created by cruzmarcano on 11/1/18.
 */

public class BDalzheimer extends SQLiteOpenHelper {


    public BDalzheimer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // si no existe la base de datos la creamos ejecutando los querysque
        // se encuentran en la clase TablasCampos
        db.execSQL(TablasCampos.CREAR_TABLA_PLANTILLA);
        db.execSQL(TablasCampos.CREAR_TABLA_GRUPO);
        db.execSQL(TablasCampos.CREAR_TABLA_JUEGO);
        db.execSQL(TablasCampos.CREAR_TABLA_JUEGO_GRUPO);
        //Log.v("prueba",TablasCampos.CREAR_TABLA_JUEGO_GRUPO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }



}
