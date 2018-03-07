package com.example.cruzmarcano.juego;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;
import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;
import com.facebook.stetho.Stetho;
import java.io.File;
import java.io.IOException;



public class MemoriaActivity extends AppCompatActivity {

    private static final String TAG = "MemoriaActivity";
    private static final int plantilla = 1;
    private ContentValues juegoDatos=new ContentValues();
    private String nombreImag;
    private String imacarpeta =Environment.getExternalStorageDirectory()+File.separator+"AlzheimerApp"+File.separator;
    private String sonidocarpeta =Environment.getExternalStorageDirectory()+File.separator+"Sounds";;
    private static final int TRACK=300;
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

        //ver base de datos en chrome
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_memoria);

        //barra de arriba
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
        final int plantilla =1;


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

        //boton orientacion
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

        memo_sonido1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarAudio(view);
            }
        });



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se guardan lo sdatos de los campos y plantilla
                juegoDatos.put(BDalzheimer.JUEGO_NOMBRE,nombre.getText().toString());
                juegoDatos.put(BDalzheimer.JUEGO_INSTRUCCION,instrucion.getText().toString());
                juegoDatos.put(BDalzheimer.JUEGO_PLANTI_FK,plantilla);
                //se llama a la base de datos
                BDalzheimer tablas = new BDalzheimer(MemoriaActivity.this);

                //se crea un objeto de la Tablas campos para usar sus metodos y poder insertar datos
                TablasCampos i=new TablasCampos(MemoriaActivity.this);
                String mensaje=i.insertarDatos(BDalzheimer.TABLA_JUEGO,juegoDatos,BDalzheimer.JUEGO_ID);
                Toast.makeText(MemoriaActivity.this,mensaje,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MemoriaActivity.this, PrincipalActivity.class);
                startActivity(intent);

            }
        });



    }

    private void opencamara() {

        //lanzamos el intent que abre la camara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

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
       // super.onActivityResult(requestCode, resultCode, data);


        //vemos de donde viene nuestra respuesta
        switch (requestCode){
            //si imagen viene de la camara
            case PHOTO_CODE:
                if (resultCode==RESULT_OK){
                    //se octiene la direccion provicional de la imagen
                    Uri path =data.getData();
                    // se manda a recortar  para que sea una imagen cuadrada
                    recortarImagen(path);

                }
                break;
                //------------------------Galeria--------------------
           //si la imagen viene de la galeria
            case SELECT_PICTURE:


                if(resultCode==RESULT_OK){
                    //se optiene la direccion de la imagen en la galeria
                    Uri path =data.getData();
                    //se valida si es cuadrada
                    if(validarTamano(path)==false){
                        recortarImagen(path);
                    }else{

                    }




                }
                break;
            //------------------------Cortar Imagen---------------------------
            case CROP_PIC_REQUEST_CODE:
                if(resultCode==RESULT_OK) {

                    Bitmap bitmap = BitmapFactory.decodeFile(imacarpeta+nombreImag);
                    memo_ima.setImageBitmap(bitmap);

                }

                break;
            //----------------------Recibir Track de Audio-------------------
            case TRACK:

                if (resultCode==RESULT_OK){

                    if(data.hasExtra("track")){
                        String sonidoRuta=data.getExtras().getString("track");
                        juegoDatos.put(BDalzheimer.JUEGO_DATOS2,sonidoRuta);

                    }

                }
                break;
        }
    }

    private void scanercarpeta(String ruta, Context contes) {
        MediaScannerConnection.scanFile(contes,
                new String[]{ruta}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String s, Uri uri) {
                        Log.i("External Storal","escaneo exitoso");



                    }
                });
    }

    private void decodeBitmap(String direccion) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(direccion);
        memo_ima.setImageBitmap(bitmap);
    }

    private void recortarImagen(Uri picUri) {

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
            File nuevacarpeta=new File(imacarpeta+nombreImag);
            //se escanea la carpeta para que aparezca la imagen dentro de la carpeta
            scanercarpeta(imacarpeta+nombreImag,this);
            //guardamos
            juegoDatos.put(BDalzheimer.JUEGO_DATOS1,imacarpeta+nombreImag);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevacarpeta));
            startActivityForResult(cropIntent, CROP_PIC_REQUEST_CODE);
        }
        // en caso que no se pueda recortar
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Su dispositivo no puede cortar la imagen";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    //---------------------buscar Audio-------------------------------

    public void buscarAudio(View v){

        File folder = new File(sonidocarpeta);
        //se reviza si existen datos de audio que listar
        if(folder.exists() && (folder.length()>0)){
            //si posee audio se llama el activity lis y se listan los audios
            Intent i=new Intent(getApplicationContext(),ListaActivity.class);
            //se va a la pantalla que lista los audios
            startActivityForResult(i,TRACK);
        }else {
            //si no posee audio se envia un mensaje al usuario
            String errorMessage = "No posees ingun sonido en tu galeria";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    //---------------validar tamaño------------
    //valida si la imagen es cuadrada
    public Boolean validarTamano(Uri ruta){

        Boolean cuadrado=false;

        try {
            //comvierte la ruta procicional en un Bitmap es necesario para saber su tamaño en pixeles
            Bitmap imagen=MediaStore.Images.Media.getBitmap(this.getContentResolver(),ruta);
            //se pregunta si la imagen es cuadrada
            if(imagen.getHeight()==imagen.getWidth()){
                // si tene el mismo ancho y alto es cuadrada
                cuadrado=true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "falla al optener la imagen";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

        return cuadrado;
    }

}
