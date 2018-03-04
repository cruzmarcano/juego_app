package com.example.cruzmarcano.juego.adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cruzmarcano.juego.R;
import com.example.cruzmarcano.juego.pojo.EjerciciosPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cruzmarcano on 28/2/18.
 */

public class CrearGrupoAdacter extends RecyclerView.Adapter<CrearGrupoAdacter.ViewHolder> {
    Context contexto;
    //estos lista contiene los datos que va a recibir
    List<EjerciciosPojo> ejerciciospojo;
    //esta lista almacena el id de los juegos cuyos  checkbox fueron marcados
    private ArrayList<Integer> idJuegosCheckbox;



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
    public void onBindViewHolder(final CrearGrupoAdacter.ViewHolder holder, final int position) {
        //se inicializa el arreglo
        idJuegosCheckbox=new ArrayList<Integer>();
        //esto captura el nombre del juego
        holder.nombre.setText(ejerciciospojo.get(position).getNombre());
        //evita que se pierdan los checbox marcados al subir o bajar el scrol
        holder.checkBox.setOnCheckedChangeListener(null);
        //se crea un elemento onclick para capturar cundo se preciona el checkbox
        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //se valida si el checkbox fue marcado
                if(holder.checkBox.isChecked()){
                    //si es checkeado se guarda en el arreglo el id del ejercicio
                    idJuegosCheckbox.add(ejerciciospojo.get(position).getId());

                //si se deselecciona
                }else if(!holder.checkBox.isChecked()){
                     //se valida si existe el id dentro del arreglo
                  if(idJuegosCheckbox.contains(ejerciciospojo.get(position).getId())){
                        //si exite se pide que indique que posicion
                      int po=idJuegosCheckbox.indexOf(ejerciciospojo.get(position).getId());
                      //finalmente se elimina
                      idJuegosCheckbox.remove(po);

                  }

                }

            }
        });




        //color de fondo de la tarjeta ARVERTENCIA "se debe colocar un color dependiendo del juego"
        holder.tarjeta.setCardBackgroundColor(contexto.getResources().getColor(R.color.azul2));



    }
    // el metodo geter permite que se pueda aceder al arreglo desde oro activity solo vreando una
    // instancia de la clase e invocando el metodo
    public ArrayList<Integer> getNumbers() {
        return idJuegosCheckbox;
    }

    //numero de instancias que se crearan
    @Override
    public int getItemCount() {
        return ejerciciospojo.size();
    }
    //nuestra propia clase Viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView tarjeta;
        private TextView nombre;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            tarjeta=(CardView)itemView.findViewById(R.id.itemGrupEjer);
            nombre=(TextView)itemView.findViewById(R.id.nombEjerc);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkBox);

        }
    }
}
