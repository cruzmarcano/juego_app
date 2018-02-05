package com.example.cruzmarcano.juego.Propias;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cruzmarcano.juego.R;

import java.util.List;

/**
 * Created by cruzmarcano on 4/2/18.
 */

public class RecyclerViewAdactador extends RecyclerView.Adapter<RecyclerViewAdactador.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre, duracion;
        ImageView icon1,play_img;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre=(TextView)itemView.findViewById(R.id.nombre);
            duracion=(TextView)itemView.findViewById(R.id.duracion);
            icon1=(ImageView)itemView.findViewById(R.id.icon1);
            play_img=(ImageView)itemView.findViewById(R.id.play_btn);

        }
    }

    List<ListaMusica> lista;

    public RecyclerViewAdactador(List<ListaMusica> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sonido,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position).getNombre());
        holder.duracion.setText(lista.get(position).getDuracion());
        holder.icon1.setImageResource(lista.get(position).getIcon1());
        holder.play_img.setImageResource(lista.get(position).getPlay_img());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }



}
