package com.example.cruzmarcano.juego.Propias;

import com.example.cruzmarcano.juego.R;

/**
 * Created by cruzmarcano on 3/2/18.
 */

public class ListaMusica   {
    private String nombre, duracion;
    private final int icon1;
    private final int play_img;

    public ListaMusica(String nombre, String tiempo) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.icon1 = R.drawable.ic_sonido;
        this.play_img = R.drawable.ic_play;


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getIcon1() {
        return icon1;
    }

    public int getPlay_img() {
        return play_img;
    }
}
