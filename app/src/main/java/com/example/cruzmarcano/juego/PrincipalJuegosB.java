package com.example.cruzmarcano.juego;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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


import static android.app.Activity.RESULT_OK;


public class PrincipalJuegosB extends Fragment implements View.OnClickListener{

    //variables globales
    private TextView nombre,instruccion;
    private ImageButton imagen,sonido;
    private Button guardar;
    private final int PHOTO_CODE=100;


private ContentValues juegoDatos=new ContentValues();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
            case R.id.ateImaBtn1:
                final CharSequence[] options={"Camara","Galeria","Recursos","Cancelar"};
                final AlertDialog.Builder bulder= new AlertDialog.Builder(getContext());
                bulder.setTitle("Seleccione una opcion");
                bulder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialigo, int i) {

                        switch (options[i].toString()){
                            case "Camara":
                                abrirCamara();
                                //abre la camara

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


                break;
            case R.id.guardarB:


                break;

        }

    }

    private void abrirCamara(){
        //lanzamos el intent que abre la camara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //recibimos los resultados
        startActivityForResult(intent,PHOTO_CODE);
    }

    private void abrirGaleria() {
    }

    private void opencamara() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);


        //vemos de donde viene nuestra respuesta
        switch (requestCode){
            //si imagen viene de la camara
            case PHOTO_CODE:
                if (resultCode==RESULT_OK){

                    //se octiene la direccion provicional de la imagen
                    //Uri path =data.getData();
                    // se manda a recortar  para que sea una imagen cuadrada
                    //recortarImagen(path);


                }
                break;

        }
    }

}
