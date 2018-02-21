package com.example.cruzmarcano.juego;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class EjercicioAtencion extends AppCompatActivity {

    ImageView imagen;
    ImageButton boton1, boton2, boton3;
    TextView instruccion;
    ArrayList<String> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_atencion);


        imagen=(ImageView)findViewById(R.id.imagen);
        boton1=(ImageButton)findViewById(R.id.boton_1);
        boton2=(ImageButton)findViewById(R.id.boton_2);
        boton3=(ImageButton)findViewById(R.id.boton_3);
        instruccion=(TextView)findViewById(R.id.instruccion);

        datos=new ArrayList<String>();

        datos.add("el texto es el siguiente");
        datos.add("android.resource://com.example.cruzmarcano.juego/drawable/cerdo");

        instruccion.setText(datos.get(0));
        Uri path = Uri.parse(datos.get(1));

        //Uri path = Uri.parse("android.resource://"+BuildConfig.APPLICATION_ID+"/" + R.drawable.cerdo);

        imagen.setImageURI(path);








    }
}
