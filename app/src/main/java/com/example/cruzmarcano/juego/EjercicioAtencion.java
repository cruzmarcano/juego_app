package com.example.cruzmarcano.juego;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

//implementamos la clase OnClickListener para poder aprobechar sus metodos y escribir menos codigo
public class EjercicioAtencion extends AppCompatActivity implements View.OnClickListener{

    ImageView imagen;
    ImageButton boton1, boton2, boton3;
    TextView instruccion;
    SoundPool soundPool;
    int cargaSonido;
    int cargaSonido2;
    int cargaSonido3;
    ArrayList<String> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_atencion);


        imagen=(ImageView)findViewById(R.id.imagen);
        boton1=(ImageButton)findViewById(R.id.boton_1);
        boton2=(ImageButton)findViewById(R.id.boton_2);
        boton3=(ImageButton)findViewById(R.id.boton_3);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);

        instruccion=(TextView)findViewById(R.id.instruccion);

        datos=new ArrayList<String>();

        datos.add("el texto es el siguiente");
        datos.add("android.resource://com.example.cruzmarcano.juego/drawable/cerdo");

        //carga las instrucciones
        instruccion.setText(datos.get(0));
        //carga la imagen
        Uri path = Uri.parse(datos.get(1));

        //esto determina cuantos canciones pueden sonar
        soundPool= new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        //esto permite gestionar el volumen  del sonido desde los botones laterales
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // esto carga los sonidos
        cargaSonido=soundPool.load(this,R.raw.cerdo,1);
        cargaSonido2=soundPool.load(this,R.raw.perro,1);
        cargaSonido3=soundPool.load(this,R.raw.gato,1);

        imagen.setImageURI(path);








    }

    @Override
    public void onClick(View v) {
        //este switch nos permite identificar el boton precionado
        switch (v.getId()){
            case R.id.boton_1:

                soundPool.play(cargaSonido,1,1,0,0,1);

                break;

            case R.id.boton_2:

                soundPool.play(cargaSonido2,1,1,0,0,1);

                break;

            case R.id.boton_3:
                soundPool.play(cargaSonido3,1,1,0,0,1);

                break;


        }
    }
}
