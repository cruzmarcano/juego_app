package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class LenguaActivity extends AppCompatActivity {

    ImageButton memoria, lenguaje, orientacion, atencion, visual ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengua);

        Toolbar toolbar= (Toolbar) findViewById(R.id.barra);
        setSupportActionBar(toolbar);

        //boton menu memoria
        memoria =(ImageButton)findViewById(R.id.memo_btn);
        memoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MemoriaActivity.this,"memoria",Toast.LENGTH_LONG).show();
                Intent atencion =new Intent(LenguaActivity.this,MemoriaActivity.class);
                startActivity(atencion);

            }
        });
        /*botlenguaje =(ImageButton)findViewById(R.id.leng_btn);
        lenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LenguaActivity.this,"atencion",Toast.LENGTH_LONG).show();
            }
        });*/


        //boton lenguaje
        orientacion =(ImageButton)findViewById(R.id.leng_btn);
        orientacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LenguaActivity.this,"orientacion",Toast.LENGTH_LONG).show();
            }
        });


    }


}
