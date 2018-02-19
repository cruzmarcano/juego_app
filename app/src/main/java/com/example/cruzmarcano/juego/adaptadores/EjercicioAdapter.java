package com.example.cruzmarcano.juego.adaptadores;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cruzmarcano.juego.R;
import com.example.cruzmarcano.juego.pojo.EjerciciosPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cruzmarcano on 18/2/18.
 */
// primero se crea la clase EjercicioAdapter y hacemos el extends solo cuando creemos nuestra clase ViewHolder (propia)
// cuando se hace el extends tecordar que el ViewHolder colocado entre los simbolos <> debe ser na nuestra
public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.ViewHolder>{
    Context contexto;
    //estos lista contiene los datos que va a recibir
    List<EjerciciosPojo> ejerciciospojo;
    //este es el constructor
    public EjercicioAdapter(Context contexto, List<EjerciciosPojo> ejerciciospojo) {
        this.contexto = contexto;
        this.ejerciciospojo = ejerciciospojo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // contexto entra automaticamente por el parametro parent
        View vistaItem=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio,parent,false);
        //cremos un objeto ViewHolder que es el nuestro y le pasamos la vista
        ViewHolder viewHolder= new ViewHolder(vistaItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //aqui se enlazan la data con cada ViewHolder
        holder.nombre.setText(ejerciciospojo.get(position).getNombre());
        Log.v("prueba",ejerciciospojo.get(position).getNombre());
        //color de fondo de la tarjeta
        holder.tarjeta.setCardBackgroundColor(contexto.getResources().getColor(R.color.azul2));

    }

    @Override
    public int getItemCount() {
        //cuantos va a mostrar
        return ejerciciospojo.size();
    }

    //creamos nuestra propia clase ViewHolder y extendemos de RecyclerView y ViewHolder del paquete
    //esto lo hacemos antes de sobreexcribir los metodos y antes de crear el extends de la clase EjercicioAdapter
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre;
        private CardView tarjeta;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre=(TextView) itemView.findViewById(R.id.nombreJuego);
            tarjeta=(CardView)itemView.findViewById(R.id.tarjeta);
        }
    }






}
