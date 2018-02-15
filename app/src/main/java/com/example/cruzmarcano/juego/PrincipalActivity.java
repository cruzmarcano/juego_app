package com.example.cruzmarcano.juego;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

public class PrincipalActivity extends AppCompatActivity {

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
}
