package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MemoriaActivity extends AppCompatActivity {

    ImageButton memoria, lenguaje, orientacion, atencion, visual, memo_ima,memo_sonido1,memo_sonido2,memo_sonido3;
    Button guardar;
    EditText  nombre, instrucion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        Toolbar toolbar= (Toolbar) findViewById(R.id.barra);
        setSupportActionBar(toolbar);
        //cateamos todos los objetos que estan en la plantilla
        //texto de nombre e instruccion del juego
        nombre=(EditText) findViewById(R.id.memo_jueg_nomb);
        instrucion=(EditText) findViewById(R.id.memo_jue_inst);
        //imagen
        memo_ima = (ImageButton) findViewById(R.id.memo_ima);
        //sonido
        memo_sonido1 = (ImageButton) findViewById(R.id.btn_sonid_1);
        memo_sonido2 = (ImageButton) findViewById(R.id.btn_sonid_2);
        memo_sonido3 = (ImageButton) findViewById(R.id.btn_sonid_3);

        guardar=(Button)findViewById(R.id.memo_guar_btn);
        //numero de la plantilla
        int plantilla =1;


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
