package com.example.cruzmarcano.juego;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;


public class MemoriaActivity extends AppCompatActivity {

    private String APP_directorio="Galeria";
    private String Media_directorio= APP_directorio+"media";
    private String nombreImag;
    private  String ruta =Environment.getExternalStorageDirectory()+File.separator+"AlzheimerApp"+File.separator;
    private final int permisos=300;
    private final int PHOTO_CODE=100;
    private final int SELECT_PICTURE=10;
    final int CROP_PIC_REQUEST_CODE = 1;

    ImageButton memoria, lenguaje, orientacion, atencion, visual,memo_sonido1,memo_sonido2,memo_sonido3;
    ImageView memo_ima;
    Button guardar;
    EditText  nombre, instrucion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        Toolbar toolbar= (Toolbar) findViewById(R.id.barra);
        setSupportActionBar(toolbar);
        //creamos la carpeta donde guardaremos las imagenes y los sonidos
        String recursos = "AlzheimerApp";

        File f = new File(Environment.getExternalStorageDirectory(), recursos);
        if (!f.exists()) {
            f.mkdirs();
        }

        //cateamos todos los objetos que estan en la plantilla
        //texto de nombre e instruccion del juego
        nombre=(EditText) findViewById(R.id.memo_jueg_nomb);
        instrucion=(EditText) findViewById(R.id.memo_jue_inst);
        //imagen
        memo_ima = (ImageView) findViewById(R.id.memo_ima);
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
                            //abre la camara
                            opencamara();
                        }else if (options[i]=="Galeria"){
                            //abre galeria
                            opengaleria();
                        }else if(options[i]=="Cancelar"){
                            //cierra la ventana de opciones
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
        //optengo la fecha para usarla como nombre de la imagen
        Long fecha =System.currentTimeMillis()/1000;
        //combierto la fecha a string y la uno con la extencion para formar el nombre
        nombreImag =fecha.toString()+".jpg";
        File nuevacarpeta=new File(ruta+nombreImag);
        //lanzamos el intent que abre la camara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //se usa para almacenar la imagen
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevacarpeta));
        //recibimos los resultados
        startActivityForResult(intent,PHOTO_CODE);

    }

    private void opengaleria() {
        //el inten recibe dos parametros el primero abre el menu con las opciones
        // el segundo abre el el volumen de almacenamiento privado en el momento en que
        // se abre el menu del primer parametro
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //selecciona todos los arvhivos de tipo imagen
        intent.setType("image/*");
        //lanza el inten los parametros que reciben permiten seleccionar que aplicacion de
        //galeria nos mostrara las imagenes a la hora de seleccionar
        startActivityForResult(intent.createChooser(intent,"seleccione app de galeria"),SELECT_PICTURE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //vemos de donde viene nuestra respuesta
        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode==RESULT_OK){
                    MediaScannerConnection.scanFile(this,
                            new String[]{ruta+nombreImag}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String s, Uri uri) {
                                    Log.i("External Storal","escaneo exitoso");



                                }
                            });
                    //Uri path =data.getData();

                    Bitmap bitmap = BitmapFactory.decodeFile(ruta+nombreImag);
                    memo_ima.setImageBitmap(bitmap);



                    ;
                }
                break;
            case SELECT_PICTURE:


                if(resultCode==RESULT_OK){

                    Uri path =data.getData();
                    doCrop(path);


                }
                break;

            case CROP_PIC_REQUEST_CODE:
                if(resultCode==RESULT_OK) {
                    MediaScannerConnection.scanFile(this,
                            new String[]{ruta+nombreImag}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String s, Uri uri) {
                                    Log.i("External Storal","escaneo exitoso");



                                }
                            });
                    Bitmap bitmap = BitmapFactory.decodeFile(ruta+nombreImag);
                    memo_ima.setImageBitmap(bitmap);
                }

                break;

        }
    }

    private void decodeBitmap(String direccion) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(direccion);
        memo_ima.setImageBitmap(bitmap);
    }

    private void doCrop(Uri picUri) {
        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");

            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            cropIntent.putExtra("return-data", true);
            //optengo la fecha para usarla como nombre de la imagen
            Long fecha =System.currentTimeMillis()/1000;
            //combierto la fecha a string y la uno con la extencion para formar el nombre
            nombreImag =fecha.toString()+".jpg";
            File nuevacarpeta=new File(ruta+nombreImag);
            //guardamos
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevacarpeta));
            startActivityForResult(cropIntent, CROP_PIC_REQUEST_CODE);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "tu dispositivo no soporte esta opcion";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}
