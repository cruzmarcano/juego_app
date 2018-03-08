package com.example.cruzmarcano.juego;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class PrincipalActivity extends AppCompatActivity {
    ImageButton botonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //casteamos el taplayout que creamos
        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabs);
        //castemos viewPager que es la pagina que nos permitinar
        //cambiar de pagina mediante gestos
        ViewPager viewPager =(ViewPager)findViewById(R.id.pagina);

        viewPager.setAdapter(new AdaptadorDePaginas (getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        botonMenu=(ImageButton) findViewById(R.id.botonMenu);
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu= new PopupMenu(PrincipalActivity.this,botonMenu);
                popupMenu.getMenuInflater().inflate(R.menu.toolbar_menu,popupMenu.getMenu());

                 popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        opcion(item.getTitle().toString());
                        return false;
                    }
                });

                popupMenu.show();
            }
        });



    }
    //creamos un metodo que es el que permite llenar el viewPager
    //con las paginas que queremos y obligatoriamente debemos extender de FragmentPagerAdapter
    public class AdaptadorDePaginas extends FragmentPagerAdapter{
        //nos pide un constructor
        public AdaptadorDePaginas(FragmentManager fm) {
            super(fm);
        }

        //este metodo identifica los fragmen mostrados
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new PrincipalGrupos();
                case 1:
                    return new PrincipalEjercicios();
                default:
                    return new PrincipalGrupos();
            }

        }
        // este metodo indica la cantidad de paginas a mostrar
        @Override
        public int getCount() {
            return 2;
        }
        //sobre escribimos el metodo getPageTitle para que muestre el titulo de los botones del tab
        @Override
        public CharSequence getPageTitle(int position){

            switch (position){
                case 0:
                    return "Grupos";
                case 1:
                    return "Juegos";
                default:
                    return "pagina1";
            }

        }

    }

    public void opcion (String item){
        Intent intent;
        switch (item){
            case "Crear juego":
                 intent = new Intent(PrincipalActivity.this, PrincipalJuegos.class);
                startActivity(intent);

                break;
            case "Crear Grupo":
                intent = new Intent(PrincipalActivity.this, CrearGrupoActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
