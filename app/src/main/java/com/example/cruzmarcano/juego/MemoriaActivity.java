package com.example.cruzmarcano.juego;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;


public class MemoriaActivity extends AppCompatActivity {

    private String APP_directorio="Galeria";
    private String Media_directorio= APP_directorio+"media";
    private String Nombre_temporal= "temporal.jpg";

    private final int PHOTO_CODE=100;
    private final int SELECT_PICTURE=200;

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


        memo_ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options={"Camara","Galeria", "Cancelar"};
                final AlertDialog.Builder bulder= new AlertDialog.Builder(MemoriaActivity.this);
                bulder.setTitle("Seleccione una opcion");
                bulder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialigo, int i) {
                        if(options[i]=="Camara"){
                            opencamara();
                        }else if (options[i]=="Galeria"){
                            //el inten recibe dos parametros el primero abre el menu con las opciones
                            // el segundo abre el el volumen de almacenamiento privado en el momento en que
                            // se abre el menu del primer parametro
                            Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            //selecciona todos los arvhivos de tipo imagen
                            intent.setType("images/*");
                            //lanza el inten los parametros que reciben permiten seleccionar que aplicacion de
                            //galeria nos mostrara las imagenes a la hora de seleccionar
                            startActivityForResult(intent.createChooser(intent,"seleccione app de galeria"),SELECT_PICTURE);
                        }else if(options[i]=="Cancelar"){
                            //cierra la ventana
                            dialigo.dismiss();
                        };

                    }
                });
                //muestra la ventana
                bulder.show();
            }
        });


    }

    private void opencamara() {
        //crea la ruta donde se guardara la carpeta que contendra la image
        // "se debe cambiar por la carpeta donde se guardan las imagenes"
        File carpeta = new File(Environment.getExternalStorageDirectory(),Media_directorio);
        //crea la carpeta
        carpeta.mkdirs();
        //aqui se guarda la ruta donde se ubica la foto "esto es lo que debe almacenar en bd"
        String ruta=Environment.getExternalStorageDirectory()+File.separator+Media_directorio+File.separator+Nombre_temporal;
        File nuevacarpeta=new File(ruta);
        //lanzamos el intent que abre la camara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //se usa para almacenar la imagen
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevacarpeta));
        //recibimos los resultados
        startActivityForResult(intent,PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //vemos de donde viene nuestra respuesta
        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode==RESULT_OK){
                    String direccion = Environment.getExternalStorageDirectory()+File.separator+Media_directorio+File.separator+Nombre_temporal;
                    //decodificamos la direcion para colocarla
                    decodeBitmap(direccion);
                }
            case SELECT_PICTURE:
                if(requestCode==RESULT_OK){
                    //al traer de galeria no hace falta codificar solo guardamos directamente la rireccion
                    Uri direccion =data.getData();
                    memo_ima.setImageURI(direccion);
                }

        }
    }

    private void decodeBitmap(String direccion) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(direccion);
        memo_ima.setImageBitmap(bitmap);
    }
}
