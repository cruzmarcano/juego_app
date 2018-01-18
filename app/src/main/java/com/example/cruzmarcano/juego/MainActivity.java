package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageButton memoria, lenguaje, orientacion, atencion, visual ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.barra);
        setSupportActionBar(toolbar);

        //boton menu memoria
        memoria =(ImageButton)findViewById(R.id.memo_btn);
        memoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"memoria",Toast.LENGTH_LONG).show();
                Intent atencion =new Intent(MainActivity.this,AtencionActivity.class);
                startActivity(atencion);

            }
        });
        //boton lenguaje
        lenguaje =(ImageButton)findViewById(R.id.leng_btn);
        lenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"atencion",Toast.LENGTH_LONG).show();
            }
        });


    }


}
