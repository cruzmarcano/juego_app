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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {
    ImageButton botonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //castemos la barra de herramienta que creamos
        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        //decimos que esta sera nuestra action bar
        //setSupportActionBar(toolbar);
        //decimos que la muestre
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                        Toast.makeText(PrincipalActivity.this, item.getTitle(),Toast.LENGTH_LONG).show();
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
        switch (item){
            case "Crear juego":
                Intent intent = new Intent(PrincipalActivity.this, MemoriaActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
