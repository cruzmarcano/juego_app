package com.example.cruzmarcano.juego.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cruzmarcano.juego.CrearGrupoActivity;
import com.example.cruzmarcano.juego.Propias.RecyclerViewAdactador;
import com.example.cruzmarcano.juego.R;

import java.util.ArrayList;

/**
 * Created by cruzmarcano on 6/3/18.
 */

public class ColorAdacter extends RecyclerView.Adapter<ColorAdacter.ViewHolder>{
    Context context;
    ArrayList<String> colores;

    public ColorAdacter(Context context, ArrayList<String> colores) {
        this.context = context;
        this.colores = colores;
    }

    @Override
    public ColorAdacter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_color,parent,false);
        ViewHolder viewHolder=new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ColorAdacter.ViewHolder holder, final int position) {
        int color=Color.parseColor("#76aadb");
        //holder.botonColor.setBackgroundColor(89);
            holder.botonColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* Intent intent =new Intent(context, CrearGrupoActivity.class);
                    intent.putExtra("color",colores[position]);
                    context.startActivity(intent);*/
                }
            });
    }

    @Override
    public int getItemCount() {
        return colores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView targetaColor;
        private Button botonColor;
        public ViewHolder(View itemView) {
            super(itemView);
            targetaColor=(CardView) itemView.findViewById(R.id.cardColor);
            botonColor=(Button) itemView.findViewById(R.id.botonColor);
        }
    }
}
