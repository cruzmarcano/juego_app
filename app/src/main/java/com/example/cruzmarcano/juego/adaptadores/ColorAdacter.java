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
import java.util.List;

/**
 * Created by cruzmarcano on 6/3/18.
 */

public class ColorAdacter extends RecyclerView.Adapter<ColorAdacter.ViewHolder>{
    Context contexto;
    List<String> informacion;

    //estos lista contiene los datos que va a recibir



    //este es el constructor


    public ColorAdacter(Context contexto, List<String> informacion) {
        this.contexto = contexto;
        this.informacion = informacion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // contexto entra automaticamente por el parametro parent
        View vistaItem=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color,parent,false);
        //cremos un objeto ViewHolder que es el nuestro y le pasamos la vista
        ViewHolder viewHolder= new ViewHolder(vistaItem);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //aqui se enlazan la data con cada ViewHolder




        //color de fondo de la tarjeta
        holder.botonColor.setBackgroundColor(Color.parseColor(informacion.get(position)));
        //Toast.makeText(contexto,"este="+Integer.toString(s),Toast.LENGTH_LONG).show();

        holder.botonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(contexto, CrearGrupoActivity.class);
                intent.putExtra("datos",informacion.get(position));
                contexto.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        //cuantos va a mostrar
        return informacion.size();
    }

    //creamos nuestra propia clase ViewHolder y extendemos de RecyclerView y ViewHolder del paquete
    //esto lo hacemos antes de sobreexcribir los metodos y antes de crear el extends de la clase EjercicioAdapter
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Button botonColor;
        private CardView tarjeta ;

        public ViewHolder(View itemView) {
            super(itemView);
            botonColor=(Button) itemView.findViewById(R.id.botonColor);
            tarjeta=(CardView)itemView.findViewById(R.id.cardColor);
        }
    }






}
