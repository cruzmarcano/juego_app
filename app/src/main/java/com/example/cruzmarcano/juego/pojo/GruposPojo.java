package com.example.cruzmarcano.juego.pojo;

/**
 * Created by cruzmarcano on 25/2/18.
 */

public class GruposPojo {
int id,fk;
String nombre, color, imagen;

    public GruposPojo(int id, String nombre, String color, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.imagen = imagen;
    }

    public GruposPojo() {
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
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
