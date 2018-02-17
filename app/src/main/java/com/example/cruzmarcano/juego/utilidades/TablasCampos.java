package com.example.cruzmarcano.juego.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Adapter;

import com.example.cruzmarcano.juego.datos.BDalzheimer;

import java.lang.reflect.Array;

/**
 * Created by cruzmarcano on 12/2/18.
 */

public class TablasCampos {
    protected SQLiteOpenHelper dbHelper;
    protected SQLiteDatabase baseDeDatos;


//--------------------------------------------------------------------------------------------------------------------

    public TablasCampos(Context contexto) {
        dbHelper= new BDalzheimer(contexto);
        //this.baseDeDatos = bd;
    }


    public String insertarDatos(String tabla, ContentValues campos,String respuesta ){
        abrirDatabase();
        Long idResultado=this.baseDeDatos.insert(tabla,respuesta,campos);

        if(idResultado!=-1){
            cerraDatabase();
            return "Guardado";
        }else {
            cerraDatabase();
            return "Error al guardar";
        }

    }

    protected void abrirDatabase(){
        baseDeDatos =dbHelper.getWritableDatabase();
    }
    protected void cerraDatabase(){
        dbHelper.close();
    }

    public Adapter selecDatos (String tabla, String[] campos, String parametro){

        Cursor cursor=this.baseDeDatos.query(tabla,campos,null,null,null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){

            }
        }
        return null;
    }







}
