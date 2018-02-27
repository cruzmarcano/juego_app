package com.example.cruzmarcano.juego.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cruzmarcano.juego.R;
import com.example.cruzmarcano.juego.pojo.GruposPojo;

import java.util.List;

/**
 * Created by cruzmarcano on 25/2/18.
 */

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.ViewHolder> {
    Context contexto;
    List<GruposPojo> gruposPojos;
    //creamo esl constructor
    public GrupoAdapter(Context contexto, List<GruposPojo> gruposPojos) {
        this.contexto = contexto;
        this.gruposPojos = gruposPojos;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // contexto entra automaticamente por el parametro parent
        View vistaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grupo,parent,false);
        //cremos un objeto ViewHolder que es el nuestro y le pasamos la vista
        ViewHolder viewHolder=new ViewHolder(vistaItem);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(gruposPojos.get(position).getNombre());
        //color de fondo de la tarjeta
        holder.tarjetagrup.setCardBackgroundColor(Color.parseColor(gruposPojos.get(position).getColor()));

    }

    @Override
    public int getItemCount() {
        //cuantos va a mostrar
        return gruposPojos.size();
    }
    //creamos nuestra propia clase ViewHolder y extendemos de RecyclerView y ViewHolder del paquete
    //esto lo hacemos antes de sobreexcribir los metodos y antes de crear el extends de la clase EjercicioAdapter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView tarjetagrup;
        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            tarjetagrup=(CardView) itemView.findViewById(R.id.cardGrupo);
            nombre=(TextView) itemView.findViewById(R.id.nombreGrup);
            imagen=(ImageView) itemView.findViewById(R.id.imageGrup);

        }
    }
}
