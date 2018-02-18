package com.example.cruzmarcano.juego.pojo;

/**
 * Created by cruzmarcano on 18/2/18.
 */
//creamos esta clase para gestionar los datos de una manera mas comoda
//ya que es mas facil indicar de donde probienen al bajarlos de la base de datos
// y trabajarlos en el CardView
public class EjerciciosPojo {
    String nombre, instruccion, dato1, dato2, dato3,dato4;
    int id, fk,color;

    public EjerciciosPojo(String nombre, String instruccion, String dato1, String dato2, String dato3, String dato4, int id, int fk, int color) {
        this.nombre = nombre;
        this.instruccion = instruccion;
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.dato3 = dato3;
        this.dato4 = dato4;
        this.id = id;
        this.fk = fk;
        this.color = color;
    }

    public EjerciciosPojo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public String getDato3() {
        return dato3;
    }

    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }

    public String getDato4() {
        return dato4;
    }

    public void setDato4(String dato4) {
        this.dato4 = dato4;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
