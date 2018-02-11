package com.example.cruzmarcano.juego.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cruzmarcano on 11/1/18.
 */

public class BDalzheimer extends SQLiteOpenHelper {

    //tabla grupo query
    String grupo = "CREATE TABLE `grupo` (\n" +
            "\t`grupo_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`grupo_nomb`\tTEXT NOT NULL,`grupo_color`\tTEXT NOT NULL,\n" +
            "\t`grupo_imag`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`grupo_imag`) REFERENCES `recurso`(`recur_id`))";

    //tabla jueg_grup query
    String jue_grup="CREATE TABLE `jue_grup` (\n" +
            "\t`jue_grup_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`juego_fk`\tINTEGER NOT NULL,\n" +
            "\t`grupo_fk`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`grupo_fk`) REFERENCES `grupo`(`grupo_id`),\n" +
            "\tFOREIGN KEY(`juego_fk`) REFERENCES `juego`(`juego_id`))";

    //tabla juego query
    String juego = "CREATE TABLE `juego` (\n" +
            "\t`juego_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`planti_fk`\tINTEGER NOT NULL,\n" +
            "\t`juego_nomb`\tTEXT NOT NULL,\n" +
            "\t`juego_instr`\tTEXT NOT NULL,\n" +
            "\t`juego_pre_resp1_fk`\tINTEGER NOT NULL,\n" +
            "\t`juego_pre_resp2_fk`\tINTEGER NOT NULL,\n" +
            "\t`juego_pre_resp3_fk`\tINTEGER NOT NULL,\n" +
            "\t`juego_pre_resp4_fk`\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(`planti_fk`) REFERENCES `platilla`(`planti_id`),\n" +
            "\tFOREIGN KEY(`juego_pre_resp3_fk`) REFERENCES `recurso`(`recur_id`),\n" +
            "\tFOREIGN KEY(`juego_pre_resp4_fk`) REFERENCES `recurso`(`recur_id`),\n" +
            "\tFOREIGN KEY(`juego_pre_resp1_fk`) REFERENCES `recurso`(`recur_id`),\n" +
            "\tFOREIGN KEY(`juego_pre_resp2_fk`) REFERENCES `recurso`(`recur_id`))";

    //tabla plantilla query
    String plantilla = "CREATE TABLE `plantilla` (\n" +
            "\t`planti_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`planti_nombre`\tTEXT NOT NULL)";

    //tabla recurso query
    String recurso = "CREATE TABLE `recurso` (\n" +
            "\t`recur_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`recur_nomb`\tTEXT NOT NULL)";

    public BDalzheimer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    // si no existe la base de datos la creamos ejecutando los querys
        db.execSQL(plantilla);
        db.execSQL(recurso);
        db.execSQL(grupo);
        db.execSQL(juego);
        db.execSQL(jue_grup);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
