package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

//implementamos la clase OnClickListener para poder aprobechar sus metodos y escribir menos codigo
public class EjercicioAtencion extends AppCompatActivity implements View.OnClickListener{

    ImageView imagen;
    ImageButton boton1, boton2, boton3;
    Button continuar;
    TextView toolBar, instruccion;
    SoundPool soundPool;
    int cargaSonido1;
    int cargaSonido2;
    int cargaSonido3;
    String respCorrepta,respuesta;
    ArrayList<String> datos;
    ArrayList<String> ruta;
    String[]informacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_atencion);

        Intent recibir=getIntent();
        Bundle bundle = recibir.getExtras();
        if(bundle!=null){
             informacion =bundle.getStringArray("datos");
        }

        imagen=(ImageView)findViewById(R.id.imagen);
        boton1=(ImageButton)findViewById(R.id.boton_1);
        boton2=(ImageButton)findViewById(R.id.boton_2);
        boton3=(ImageButton)findViewById(R.id.boton_3);
        continuar=(Button) findViewById(R.id.continuar_btn);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        continuar.setOnClickListener(this);


        instruccion=(TextView)findViewById(R.id.instruccion);
        toolBar=(TextView)findViewById(R.id.toolBar);

        respCorrepta=informacion[3];

        //este arreglo contiene la informacion que debe ser asignada a cada boton
        String[] contenido=new String[3];
        contenido[0]=informacion[3];
        contenido[1]="vaca";
        contenido[2]="gato";


        //coloca el nombre del ejercicio en la barra superior
        toolBar.setText(informacion[0]);
        //carga las instrucciones
        instruccion.setText(informacion[1]);
        //direccion uri de la imagen

        if(informacion[2].length()<8){
            //optenemos el identificador necesario para la libreria soundPool
            int ima_id = getApplicationContext().getResources().getIdentifier(informacion[2], "drawable",
                    getApplicationContext().getPackageName());
            imagen.setImageResource(ima_id );
        }else{
            Uri path = Uri.parse(informacion[2]);
            //cargar imagen
            imagen.setImageURI(path);
        }




        //esto determina cuantos canciones pueden sonar
        soundPool= new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        //esto permite gestionar el volumen  del sonido desde los botones laterales
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //este arreglo guarda la ruta del sonido
        ruta=new ArrayList<String>();

        //este arreglo lleva la cuenta para evitar que se repita el numero aleatoriao
        ArrayList<Integer> numero=new ArrayList<Integer>(3);
        //este ciclo llena el arreglo de forma aleatoria
        while (ruta.size()<3){
            int r=(int)(Math.random()*3);
            //si el numero no existe en el arreglo numero lo agrega
            if(!numero.contains(r)){
                numero.add(r);
                //asigna la ruta
                ruta.add(contenido[r]);
            }
        }



        //----------------Sonido1----------------------------------------------------------
        //este codigo lo hacemos para detectar de donde proviene la musica si de la carpeta raw
        //o de de una ruta absoluta del telefono, esta validacion es necesaria ya que
        //no pose pyede obtener rutas adsolutas de las carpetas res y hay que usar un identificador
        //el if valida que si la ruta es corta no proviene de la memoria externa del telefono
        if(ruta.get(0).length()<=8){
            //optenemos el identificador necesario para la libreria soundPool
            int sound_id = getApplicationContext().getResources().getIdentifier(ruta.get(0), "raw",
                    getApplicationContext().getPackageName());
            //usanos la funcion load que solicita identificador
            cargaSonido1=soundPool.load(this,sound_id,1);
        }else{
            //usanos la funcion load que solicita ruta
            cargaSonido1=soundPool.load(ruta.get(0),1);
        }

        //----------------Sonido2----------------------------------------------------------

        if(ruta.get(1).length()<=8){
            //optenemos el identificador necesario para la libreria soundPool
            int sound_id = getApplicationContext().getResources().getIdentifier(ruta.get(1), "raw",
                    getApplicationContext().getPackageName());
            //usanos la funcion load que solicita identificador
            cargaSonido2=soundPool.load(this,sound_id,1);
        }else{
            //usanos la funcion load que solicita ruta
            cargaSonido2=soundPool.load(ruta.get(1),1);
        }
        //----------------Sonido3----------------------------------------------------------
        if(ruta.get(2).length()<=8){
            //optenemos el identificador necesario para la libreria soundPool
            int sound_id = getApplicationContext().getResources().getIdentifier(ruta.get(2), "raw",
                    getApplicationContext().getPackageName());
            //usanos la funcion load que carga los sonidos solicitando la ruta
            cargaSonido3=soundPool.load(this,sound_id,1);
        }else{
            //usanos la funcion load que carga los sonidos solicitando la ruta
            cargaSonido3=soundPool.load(ruta.get(2),1);
        }



    }



    @Override
    public void onClick(View v) {
        //este switch nos permite identificar el boton precionado
        switch (v.getId()){
            case R.id.boton_1:

                soundPool.play(cargaSonido1,1,1,0,0,1);
                respuesta=ruta.get(0);


                break;

            case R.id.boton_2:

                soundPool.play(cargaSonido2,1,1,0,0,1);
                respuesta=ruta.get(1);;

                break;

            case R.id.boton_3:
                soundPool.play(cargaSonido3,1,1,0,0,1);
                respuesta=ruta.get(2);

                break;
            case R.id.continuar_btn:
                //soundPool.play(cargaSonido3,1,1,0,0,1);
                if(respuesta==respCorrepta){
                    Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Respuesta Equivocada",Toast.LENGTH_SHORT).show();
                }


                break;


        }
    }
}
