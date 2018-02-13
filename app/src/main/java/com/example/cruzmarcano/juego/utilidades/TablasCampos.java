package com.example.cruzmarcano.juego.utilidades;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cruzmarcano on 12/2/18.
 */

public class TablasCampos {
    protected SQLiteDatabase bd;

    //constantes tabla Plantilla
    public static final String TABLA_PLANTILLA="plantilla";
    public static final String PLANTILLA_ID="plantiId";
    public static final String PLANTILLA_NOMBRE="plantiNombre";
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

//--------------------------------------------------------------------------------------------------------------------

    public TablasCampos(SQLiteDatabase bd) {
        this.bd = bd;
    }




    public Long insertarDatos(String tabla, ContentValues campos,String respuesta ){

        Long idResultado=this.bd.insert(tabla,respuesta,campos);

        return  idResultado;
    }


}
