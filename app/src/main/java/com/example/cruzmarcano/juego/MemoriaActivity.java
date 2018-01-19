package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MemoriaActivity extends AppCompatActivity {

    ImageButton memoria, lenguaje, orientacion, atencion, visual ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        Toolbar toolbar= (Toolbar) findViewById(R.id.barra);
        setSupportActionBar(toolbar);

        /*boton menu memoria
        memoria =(ImageButton)findViewById(R.id.memo_btn);
        memoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MemoriaActivity.this,"memoria",Toast.LENGTH_LONG).show();
                //Intent atencion =new Intent(MemoriaActivity.this,AtencionActivity.class);
                //startActivity(atencion);

            }
        });*/
        //boton lenguaje
        lenguaje =(ImageButton)findViewById(R.id.leng_btn);
        lenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lengua =new Intent(MemoriaActivity.this,LenguaActivity.class);
                startActivity(lengua);
                overridePendingTransition(R.anim.aparecer,R.anim.desbanecer);
            }
        });

        //boton lenguaje
        orientacion =(ImageButton)findViewById(R.id.orien_btn);
        orientacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MemoriaActivity.this,"orientacion",Toast.LENGTH_LONG).show();
            }
        });


    }


}
