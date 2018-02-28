package com.example.cruzmarcano.juego.adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cruzmarcano.juego.R;
import com.example.cruzmarcano.juego.pojo.EjerciciosPojo;

import java.util.List;

/**
 * Created by cruzmarcano on 28/2/18.
 */

public class CrearGrupoAdacter extends RecyclerView.Adapter<CrearGrupoAdacter.ViewHolder> {
    Context contexto;
    //estos lista contiene los datos que va a recibir
    List<EjerciciosPojo> ejerciciospojo;

    public CrearGrupoAdacter(Context contexto, List<EjerciciosPojo> ejerciciospojo) {
        this.contexto = contexto;
        this.ejerciciospojo = ejerciciospojo;
    }


    @Override
    public CrearGrupoAdacter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crear_grupo,parent,false);
        ViewHolder viewHolder= new ViewHolder(vistaItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CrearGrupoAdacter.ViewHolder holder, int position) {
        holder.nombre.setText(ejerciciospojo.get(position).getNombre());

        //color de fondo de la tarjeta
        holder.tarjeta.setCardBackgroundColor(contexto.getResources().getColor(R.color.azul2));
        //Toast.makeText(contexto,"este="+Integer.toString(s),Toast.LENGTH_LONG).show();


    }

    @Override
    public int getItemCount() {
        return ejerciciospojo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView tarjeta;
        private TextView nombre;
        public ViewHolder(View itemView) {
            super(itemView);
            tarjeta=(CardView)itemView.findViewById(R.id.itemGrupEjer);
            nombre=(TextView)itemView.findViewById(R.id.nombEjerc);
        }
    }
}
