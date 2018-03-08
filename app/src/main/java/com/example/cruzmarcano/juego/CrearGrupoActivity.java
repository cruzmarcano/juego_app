package com.example.cruzmarcano.juego;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cruzmarcano.juego.adaptadores.CrearGrupoAdacter;
import com.example.cruzmarcano.juego.datos.BDalzheimer;
import com.example.cruzmarcano.juego.utilidades.TablasCampos;
import com.facebook.stetho.Stetho;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CrearGrupoActivity extends AppCompatActivity implements View.OnClickListener  {
    private final int FOTO=1;
    private final int RECIBIR_IMAGEN_CORTADA = 2;
    private final int SELECCIONAR_DE_GALERIA=3;
    private String imacarpeta = Environment.getExternalStorageDirectory()+File.separator+"AlzheimerApp"+File.separator;
    private String nombreImag;
    private String rutaImag;
    RecyclerView lista;
    TablasCampos datos;
    EditText nombre;
    String imagen,color;
    Button guardar,imagenBtn, colorBtn;
    CrearGrupoAdacter crearGrupoAdacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);
        Stetho.initializeWithDefaults(this);

        datos=new TablasCampos(CrearGrupoActivity.this);
        nombre=(EditText)findViewById(R.id.nombGrupo);
        imagenBtn=(Button)findViewById(R.id.imagGrupo2);
        colorBtn=(Button) findViewById(R.id.colorGrupo);
        guardar=(Button)findViewById(R.id.guardarGrup);

        imagenBtn.setOnClickListener(this);
        colorBtn.setOnClickListener(this);

        lista=(RecyclerView)findViewById(R.id.recyclerGrupo);


        lista.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(CrearGrupoActivity.this);
        lista.setLayoutManager(linearLayoutManager);

        crearGrupoAdacter=new CrearGrupoAdacter(getApplicationContext(),datos.selecEjercicios());
        lista.setAdapter(crearGrupoAdacter);


        Bundle extras = getIntent().getExtras();
        String s = extras.getString("datos");
        colorBtn.setBackgroundColor(Color.parseColor(s));




    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //en caso de presionar el boton de imagen
            case R.id.guardarGrup:
                ArrayList<Integer> r=crearGrupoAdacter.getNumbers();
                for (int i=0; i<r.size();i++){
                    Log.v("prueba", Integer.toString(r.get(i)));
                }
                String res=Integer.toString(r.get(0));
                Toast.makeText(CrearGrupoActivity.this,"el nuemero es="+res,Toast.LENGTH_LONG).show();
                break;
            case R.id.colorGrupo:

                Intent intent = new Intent(CrearGrupoActivity.this, ColorActivity.class);
                startActivity(intent);

                break;

            case R.id.imagGrupo2:
//abrimos un menu emergente con las occiones de imagen
                final CharSequence[] options={"Camara","Galeria","Recursos","Cancelar"};
                final AlertDialog.Builder bulder= new AlertDialog.Builder(CrearGrupoActivity.this);
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

        }
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
            scanercarpeta(imacarpeta + nombreImag, CrearGrupoActivity.this);

            //guardamos
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevacarpeta));
            startActivityForResult(cropIntent, RECIBIR_IMAGEN_CORTADA);
        }
        // en caso que no se pueda recortar
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Su dispositivo no puede cortar la imagen";
            Toast toast = Toast.makeText(CrearGrupoActivity.this, errorMessage, Toast.LENGTH_SHORT);
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

    //---------------validar tamaño------------
    //valida si la imagen es cuadrada
    public Boolean validarTamano(Uri ruta){

        Boolean cuadrado=false;

        try {
            //comvierte la ruta procicional en un Bitmap es necesario para saber su tamaño en pixeles
            Bitmap imagen=MediaStore.Images.Media.getBitmap(CrearGrupoActivity.this.getContentResolver(),ruta);
            //se pregunta si la imagen es cuadrada
            if(imagen.getHeight()==imagen.getWidth()){
                // si tene el mismo ancho y alto es cuadrada
                cuadrado=true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "falla al optener la imagen";
            Toast toast = Toast.makeText(CrearGrupoActivity.this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

        return cuadrado;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //vemos de donde viene nuestra respuesta
        switch (requestCode) {
            //si imagen viene de la camara
            case FOTO:
                if (resultCode == RESULT_OK) {

                    //se octiene la direccion provicional de la imagen
                    Uri path = data.getData();
                    // se manda a recortar  para que sea una imagen cuadrada
                    recortarImagen(path);

                }
                break;
            //------------------------recibir imagen cortada---------------------------
            case RECIBIR_IMAGEN_CORTADA:
                if (resultCode == RESULT_OK) {
                    //se recibe la imagen cortada
                    Bundle bundle = data.getExtras();
                    // si llegan datos
                    if (bundle != null) {
                        //se castea para que sea de tipo uri
                        Uri ubicacion = Uri.parse(String.valueOf(rutaImag));

                    }

                }

                break;
            //------------------------Galeria--------------------
            //si la imagen viene de la galeria
            case SELECCIONAR_DE_GALERIA:

                if (resultCode == RESULT_OK) {
                    //se optiene la direccion de la imagen en la galeria
                    Uri ubicacion = data.getData();

                    //se valida si es cuadrada
                    if (validarTamano(ubicacion) == false) {
                        //si no es se manda a recortar
                        recortarImagen(ubicacion);
                    } else {
                        // si es cuadrada se fija la ruta para ser guardada
                        rutaImag = String.valueOf(ubicacion);

                    }

                }
                break;
        }
    }

}


