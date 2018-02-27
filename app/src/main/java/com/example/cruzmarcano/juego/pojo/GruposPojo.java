package com.example.cruzmarcano.juego.pojo;

/**
 * Created by cruzmarcano on 25/2/18.
 */

public class GruposPojo {
int id,fk;
String nombre, color, imagen;

    public GruposPojo(int id, int fk, String nombre, String color, String imagen) {
        this.id = id;
        this.fk = fk;
        this.nombre = nombre;
        this.color = color;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
