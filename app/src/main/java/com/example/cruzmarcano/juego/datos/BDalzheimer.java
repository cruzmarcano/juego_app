package com.example.cruzmarcano.juego.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by cruzmarcano on 11/1/18.
 */

public class BDalzheimer extends SQLiteOpenHelper {

    //tabla grupo query
    String grupo = "CREATE TABLE `grupo` (\n" +
            "\t`grupo_id`INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`grupo_nomb`TEXT NOT NULL,`grupo_color`TEXT NOT NULL,`grupo_imag`TEXT)";

    //tabla jueg_grup query
    String jue_grup="CREATE TABLE `jue_grup` (\n" +
            "\t`jue_grup_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`juego_fk`\tINTEGER NOT NULL,\n" +
            "\t`grupo_fk`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`grupo_fk`) REFERENCES `grupo`(`grupo_id`),\n" +
            "\tFOREIGN KEY(`juego_fk`) REFERENCES `juego`(`juego_id`))";

    //tabla juego query
    String juego = "CREATE TABLE 'juego' ('id'	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"+
            "\t'plantillafk'	NUMERIC NOT NULL,'nombre'	TEXT NOT NULL, 'instrucion'	TEXT NOT NULL,\n"+
            "\t'dato1'	TEXT,'dato2'	TEXT, 'dato3'	TEXT,'dato4'	TEXT,\n"+
            "\tFOREIGN KEY('plantillafk') REFERENCES 'plantilla' ('planti_id'))";

    //tabla plantilla query
    String plantilla = "CREATE TABLE `plantilla` (\n" +
            "\t`planti_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`planti_nombre`\tTEXT NOT NULL)";


    public BDalzheimer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    // si no existe la base de datos la creamos ejecutando los querys
        db.execSQL(plantilla);
        db.execSQL(grupo);
        db.execSQL(juego);
        db.execSQL(jue_grup);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }



    public void palntillaMemoria(SQLiteDatabase db, List<String> list){
        String nombrejego=list.get(0);

        //db.execSQL("INSERT INTO plantilla(planti_nombre) VALUES (nombre);");

    }


}
