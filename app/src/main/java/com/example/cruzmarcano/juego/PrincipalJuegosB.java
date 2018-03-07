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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;
import com.facebook.stetho.Stetho;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static android.app.Activity.RESULT_OK;


public class PrincipalJuegosB extends Fragment implements View.OnClickListener{

    //variables globales
    private TextView nombre,instruccion;
    private ImageButton imagen,sonido;
    private Button guardar;
    private final int FOTO=1;
    private final int RECIBIR_IMAGEN_CORTADA = 2;
    private final int SELECCIONAR_DE_GALERIA=3;
    private final int GRABAR=4;
    private static final int TRACK=5;
    private String imacarpeta = Environment.getExternalStorageDirectory()+File.separator+"AlzheimerApp"+File.separator;
    private String sonidocarpeta =Environment.getExternalStorageDirectory()+File.separator+"Sounds";;
    private String nombreImag;
    private String rutaImag;
    private String rutaAudio;
    private ContentValues datos;
    private static final int plantilla = 1;


    private ContentValues juegoDatos=new ContentValues();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Stetho.initializeWithDefaults(getContext());
        View raiz=inflater.inflate(R.layout.principal_juegos_b, container, false);


        //cateamos botones y camps de texto
        nombre=(TextView)raiz.findViewById(R.id.atenNomb);
        instruccion=(TextView)raiz.findViewById(R.id.atenInst);
        imagen=(ImageButton)raiz.findViewById(R.id.ateImaBtn1);
        sonido=(ImageButton)raiz.findViewById(R.id.ateImaBtn2);
        guardar=(Button)raiz.findViewById(R.id.guardarB);

        //llamados a click
        imagen.setOnClickListener(this);
        sonido.setOnClickListener(this);
        guardar.setOnClickListener(this);



        return raiz;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //en caso de presionar el boton de imagen
            case R.id.ateImaBtn1:
                //abrimos un menu emergente con las occiones de imagen
                final CharSequence[] options={"Camara","Galeria","Recursos","Cancelar"};
                final AlertDialog.Builder bulder= new AlertDialog.Builder(getContext());
                bulder.setTitle("Seleccione una opcion");
                bulder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialigo, int i) {

                        switch (options[i].toString()){
                            case "Camara":
                                //abre la camara
                                abrirCamara();


                                break;
                            case "Galeria":
                                //abre la galeria de imagenes del telefono
                                abrirGaleria();
                                break;
                            case "Recurso":
                                //abre la carpeta de dibujos propios

                                break;
                            case "Cancelar":
                                //cierra el menu
                                dialigo.dismiss();
                                break;
                        }

                    }
                });
                //muestra la ventana
                bulder.show();
                break;
            case R.id.ateImaBtn2:
                final CharSequence[] options2={"Grabar","Sonidos", "Recurso", "Cancelar"};
                final AlertDialog.Builder bulder2= new AlertDialog.Builder(getContext());
                bulder2.setTitle("Seleccione una opcion");
                bulder2.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialigo, int i) {

                        switch (options2[i].toString()){
                            case "Grabar":
                                //abre la camara
                                abrirMicrofono();


                                break;
                            case "Sonidos":
                                //abre la galeria de imagenes del telefono
                                buscarAudio();
                                break;
                            case "Recurso":
                                //abre la carpeta de dibujos propios

                                break;
                            case "Cancelar":
                                //cierra el menu
                                dialigo.dismiss();
                                break;
                        }

                    }
                });
                //muestra la ventana
                bulder2.show();


                break;
            case R.id.guardarB:

                datos=new ContentValues();
                BDalzheimer tablas = new BDalzheimer(getContext());

                datos.put(BDalzheimer.JUEGO_NOMBRE,nombre.getText().toString());
                datos.put(BDalzheimer.JUEGO_INSTRUCCION,instruccion.getText().toString());
                datos.put(BDalzheimer.JUEGO_PLANTI_FK,plantilla);
                datos.put(BDalzheimer.JUEGO_DATOS1,rutaImag);
                datos.put(BDalzheimer.JUEGO_DATOS2,rutaAudio);

                TablasCampos i=new TablasCampos(getContext());
                String mensaje=i.insertarDatos(BDalzheimer.TABLA_JUEGO,datos,BDalzheimer.JUEGO_ID);
                Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG).show();
                //Log.v("prueba",rutaAudio);

                break;

        }

    }

    private void buscarAudio() {
        File folder = new File(sonidocarpeta);
        //se reviza si existen datos de audio que listar
        if(folder.exists() && (folder.length()>0)){
            //si posee audio se llama el activity lis y se listan los audios
            Intent i=new Intent(getContext(),ListaActivity.class);
            //se va a la pantalla que lista los audios
            startActivityForResult(i,TRACK);
        }else {
            //si no posee audio se envia un mensaje al usuario
            String errorMessage = "No posees ingun sonido en tu galeria";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void abrirMicrofono() {
        Intent intent =   new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivityForResult(intent, GRABAR);

    }

    private void abrirCamara(){
        //lanzamos el intent que abre la camara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //enviamos los resultados
        startActivityForResult(intent,FOTO);
    }

    private void recortarImagen(Uri picUri) {

        try {
            //inten corta la imagen
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            cropIntent.putExtra("return-data", true);
            //optengo la fecha para usarla como nombre de la imagen
            Long fecha = System.currentTimeMillis() / 1000;
            //utilizo la variable gloval nombre ima para establecer el nombre de la imagen
            nombreImag =fecha.toString()+".jpg";
            //establecemos la ruta
            rutaImag=imacarpeta + nombreImag;
            //creo un archivo y le doy la ruta y el nombre
            File nuevacarpeta = new File(rutaImag);

            //se escanea la carpeta para que aparezca la imagen dentro de la carpeta
            scanercarpeta(imacarpeta + nombreImag, getContext());

            //guardamos
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevacarpeta));
            startActivityForResult(cropIntent, RECIBIR_IMAGEN_CORTADA);
        }
        // en caso que no se pueda recortar
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Su dispositivo no puede cortar la imagen";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    private void abrirGaleria() {
        //el inten recibe dos parametros el primero abre el menu con las opciones
        // el segundo abre el el volumen de almacenamiento privado en el momento en que
        // se abre el menu del primer parametro
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //selecciona todos los arvhivos de tipo imagen
        intent.setType("image/*");
        //lanza el inten los parametros que reciben permiten seleccionar que aplicacion de
        //galeria nos mostrara las imagenes a la hora de seleccionar
        startActivityForResult(intent.createChooser(intent,"seleccione app de galeria"),SELECCIONAR_DE_GALERIA);
    }

  //este metodo escanea la carpeta AlzheimerApp para que las imagenes guardadas sen reconocidas
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
    //este metodo recibe todos los resultados de los intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //vemos de donde viene nuestra respuesta
        switch (requestCode){
            //si imagen viene de la camara
            case FOTO:
                if (resultCode==RESULT_OK){

                    //se octiene la direccion provicional de la imagen
                    Uri path =data.getData();
                    // se manda a recortar  para que sea una imagen cuadrada
                    recortarImagen(path);

                }
                break;
            //------------------------recibir imagen cortada---------------------------
            case RECIBIR_IMAGEN_CORTADA:
                if(resultCode==RESULT_OK) {
                    //se recibe la imagen cortada
                    Bundle bundle=data.getExtras();
                    // si llegan datos
                    if (bundle!=null){
                        //se castea para que sea de tipo uri
                        Uri ubicacion= Uri.parse(String.valueOf(rutaImag));
                        //se asigna a la imagen
                        imagen.setImageURI(ubicacion);

                    }

                }

                break;
            //------------------------Galeria--------------------
            //si la imagen viene de la galeria
            case SELECCIONAR_DE_GALERIA:

                if(resultCode==RESULT_OK){
                    //se optiene la direccion de la imagen en la galeria
                    Uri ubicacion =data.getData();

                    //se valida si es cuadrada
                    if(validarTamano(ubicacion)==false){
                        //si no es se manda a recortar
                        recortarImagen(ubicacion);
                    }else{
                        // si es cuadrada se fija la ruta para ser guardada
                        rutaImag= String.valueOf(ubicacion);
                        //se le asigna a la imagen
                        imagen.setImageURI(ubicacion);
                    }

                }
                break;
            case GRABAR:
                if (resultCode==RESULT_OK){
                    //pregunta de donde viene el audio

                        //audio que llega de grabador
                        Uri path = data.getData();
                       //se establece la ruta
                        rutaAudio = String.valueOf(path);


                }
                break;

            case TRACK:

                if (resultCode==RESULT_OK){


                    rutaAudio=data.getExtras().getString("track");




                }
                break;

        }
    }

    //---------------validar tamaño------------
    //valida si la imagen es cuadrada
    public Boolean validarTamano(Uri ruta){

        Boolean cuadrado=false;

        try {
            //comvierte la ruta procicional en un Bitmap es necesario para saber su tamaño en pixeles
            Bitmap imagen=MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),ruta);
            //se pregunta si la imagen es cuadrada
            if(imagen.getHeight()==imagen.getWidth()){
                // si tene el mismo ancho y alto es cuadrada
                cuadrado=true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "falla al optener la imagen";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

        return cuadrado;
    }

}
