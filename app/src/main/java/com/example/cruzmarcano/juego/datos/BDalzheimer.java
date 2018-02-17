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
    private static final String DATA_BASE_NAME="alzhaimer";
    private static final int VERSION=1;


    //constantes tabla Plantilla

        public static final String TABLA_PLANTILLA="plantilla";
        public static final String PLANTILLA_ID="plantiId";
        public static final String PLANTILLA_NOMBRE="plantiNombre";
    public static final String[] allcolumPlantilla= new String[]{
            PLANTILLA_ID, PLANTILLA_NOMBRE
    };
    // query para crear tabla plantila
    public static final String CREAR_TABLA_PLANTILLA= "CREATE TABLE " +  TABLA_PLANTILLA +
            "(" + PLANTILLA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLANTILLA_NOMBRE + " TEXT NOT NULL)";
    //---------------------------------------------------------------------------------------------------------------
    //constantes tabla grupo
    public static final String TABLA_GRUPO="grupo";
    public static final String GRUPO_ID="grupoId";
    public static final String GRUPO_NOMBRE="grupoNombre";
    public static final String GRUPO_COLOR="grupoColor";
    public static final String GRUPO_IMAGEN="grupoImagen";
    // query para crear tabla Grupo
    public static final String CREAR_TABLA_GRUPO= "CREATE TABLE " +  TABLA_GRUPO + " (" + GRUPO_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + GRUPO_NOMBRE + " TEXT NOT NULL," + GRUPO_COLOR +
            " TEXT NOT NULL,"+ GRUPO_IMAGEN +" TEXT NOT NULL)";
//----------------------------------------------------------------------------------------------------------------

    //constantes tabla Juego
    public static final String TABLA_JUEGO="juego";
    public static final String JUEGO_ID="juegoId";
    public static final String JUEGO_PLANTI_FK="juegoPlantiFk";
    public static final String JUEGO_NOMBRE="juegoNombre";
    public static final String JUEGO_INSTRUCCION="juegoInstruccion";
    public static final String JUEGO_DATOS1="datos1";
    public static final String JUEGO_DATOS2="datos2";
    public static final String JUEGO_DATOS3="datos3";
    public static final String JUEGO_DATOS4="datos4";
    // query para crear tabla juego
    public static final String CREAR_TABLA_JUEGO= "CREATE TABLE " +  TABLA_JUEGO + " ( " + JUEGO_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + JUEGO_PLANTI_FK + " INTEGER NOT NULL, " + JUEGO_NOMBRE +
            " TEXT NOT NULL," + JUEGO_INSTRUCCION + " TEXT NOT NULL," + JUEGO_DATOS1 + " TEXT," + JUEGO_DATOS2 +
            " TEXT," + JUEGO_DATOS3 + " TEXT," + JUEGO_DATOS4 + " TEXT,FOREIGN KEY(" + JUEGO_PLANTI_FK +
            ") REFERENCES " + TABLA_PLANTILLA +" (" + PLANTILLA_ID +"))";
//------------------------------------------------------------------------------------------------------------------

    //constantes tabla JuegoGrupo
    public static final String TABLA_JUEGO_GRUPO="juegoGrupo";
    public static final String JUEGO_GRUPO_ID="juegoGrupoId";
    public static final String JG_GRUPO_FK="jgGrupoFk";
    public static final String JG_JUEGO_FK="jgJuegoFk";
    // query para crear tabla Grupo
    public static final String CREAR_TABLA_JUEGO_GRUPO= "CREATE TABLE " +  TABLA_JUEGO_GRUPO+ " (" + JUEGO_GRUPO_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + JG_GRUPO_FK + " INTEGER NOT NULL,"+JG_JUEGO_FK +
            " INTEGER NOT NULL,FOREIGN KEY(" + JG_GRUPO_FK + ") REFERENCES " + TABLA_GRUPO +" (" + GRUPO_ID +"),"+
            " FOREIGN KEY(" + JG_JUEGO_FK + ") REFERENCES " + TABLA_JUEGO +" (" + JUEGO_ID +"))";



    public BDalzheimer(Context context) {
        super(context,DATA_BASE_NAME,null,VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // si no existe la base de datos la creamos ejecutando los querysque
        // se encuentran en la clase TablasCampos
        db.execSQL(CREAR_TABLA_PLANTILLA);
        db.execSQL(CREAR_TABLA_GRUPO);
        db.execSQL(CREAR_TABLA_JUEGO);
        db.execSQL(CREAR_TABLA_JUEGO_GRUPO);
        //Log.v("prueba",TablasCampos.CREAR_TABLA_JUEGO_GRUPO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }




}
