package com.example.cruzmarcano.juego.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Adapter;

import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.pojo.EjerciciosPojo;
import com.example.cruzmarcano.juego.pojo.GruposPojo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public static final String[] jueosCampos={
           BDalzheimer.JUEGO_ID, BDalzheimer.JG_JUEGO_FK,BDalzheimer.JUEGO_NOMBRE,BDalzheimer.JUEGO_INSTRUCCION,
            BDalzheimer.JUEGO_DATOS1,BDalzheimer.JUEGO_DATOS2,BDalzheimer.JUEGO_DATOS3,BDalzheimer.JUEGO_DATOS4
    };


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

    public List<EjerciciosPojo> selecEjercicios (){
        abrirDatabase();
        //Cursor cursor=this.baseDeDatos.query(BDalzheimer.TABLA_JUEGO,jueosCampos,null,null,null,null,null);
        Cursor cursor=this.baseDeDatos.rawQuery("select * from juego",null);
        List<EjerciciosPojo> ejerciciosPojo=new ArrayList<EjerciciosPojo>();
        if(cursor.getCount()>0){

            while (cursor.moveToNext()){
                EjerciciosPojo ejercicio=new EjerciciosPojo();
                ejercicio.setId(cursor.getInt(cursor.getColumnIndex(BDalzheimer.JUEGO_ID)));
                ejercicio.setFk(cursor.getInt(cursor.getColumnIndex(BDalzheimer.JUEGO_PLANTI_FK)));
                ejercicio.setNombre(cursor.getString(cursor.getColumnIndex(BDalzheimer.JUEGO_NOMBRE)));
                ejercicio.setInstruccion(cursor.getString(cursor.getColumnIndex(BDalzheimer.JUEGO_INSTRUCCION)));
                ejercicio.setDato1(cursor.getString(cursor.getColumnIndex(BDalzheimer.JUEGO_DATOS1)));
                ejercicio.setDato2(cursor.getString(cursor.getColumnIndex(BDalzheimer.JUEGO_DATOS2)));
                ejercicio.setDato3(cursor.getString(cursor.getColumnIndex(BDalzheimer.JUEGO_DATOS3)));
                ejercicio.setDato4(cursor.getString(cursor.getColumnIndex(BDalzheimer.JUEGO_DATOS4)));
                ejerciciosPojo.add(ejercicio);

            }
        }
        cerraDatabase();
        return ejerciciosPojo;

    }

    public List<GruposPojo> selecGrupos (){
        abrirDatabase();
        Cursor cursor=this.baseDeDatos.rawQuery("select * from grupo",null);
        List<GruposPojo> gruposPojo=new ArrayList<GruposPojo>();
        if(cursor.getCount()>0){

            while (cursor.moveToNext()){
                GruposPojo grupo=new GruposPojo();
                grupo.setId(cursor.getInt(cursor.getColumnIndex(BDalzheimer.GRUPO_ID)));
                grupo.setNombre(cursor.getString(cursor.getColumnIndex(BDalzheimer.GRUPO_NOMBRE)));
                grupo.setColor(cursor.getString(cursor.getColumnIndex(BDalzheimer.GRUPO_COLOR)));
                grupo.setImagen(cursor.getString(cursor.getColumnIndex(BDalzheimer.GRUPO_IMAGEN)));

                gruposPojo.add(grupo);

            }
        }
        cerraDatabase();
        return gruposPojo;

    }

    public String guardarGrupo(ContentValues campos, ArrayList<Integer> datos){

        abrirDatabase();
        Long idResultado=this.baseDeDatos.insert(BDalzheimer.TABLA_GRUPO,BDalzheimer.GRUPO_ID,campos);
        int idGrupo=idResultado.intValue();
        if(idResultado!=-1){

            for (int i = 0; i < datos.size(); i++){

                this.baseDeDatos.execSQL("INSERT INTO "+ BDalzheimer.TABLA_JUEGO_GRUPO  +" VALUES (null,'"+ idGrupo+ "','"+ datos.get(i) +"');");
            }

            cerraDatabase();

            return "Guardado";
        }else {
            cerraDatabase();
            return "Error al guardar";
        }


    }













}
