package com.example.cruzmarcano.juego;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;


public class PrincipalJuegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_juegos);

        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabJuego);
        ViewPager viewPager =(ViewPager)findViewById(R.id.paginaJuego);

        viewPager.setAdapter(new AdaptadorDePaginas (getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
    }

    //creamos un metodo que es el que permite llenar el viewPager
    //con las paginas que queremos y obligatoriamente debemos extender de FragmentPagerAdapter
    public class AdaptadorDePaginas extends FragmentPagerAdapter {
        //nos pide un constructor
        public AdaptadorDePaginas(FragmentManager fm) {

            super(fm);

        }

        //este metodo identifica los fragmen mostrados
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new PrincipalJuegosA();
                case 1:
                    return new PrincipalJuegosB();
                case 2:
                    return new PrincipalJuegosC();
                case 3:
                    return new PrincipalJuegosD();
                case 4:
                    return new PrincipalJuegosE();
                default:
                    return new PrincipalJuegosA();
            }

        }
        // este metodo indica la cantidad de paginas a mostrar
        @Override
        public int getCount() {
            return 5;
        }
        //sobre escribimos el metodo getPageTitle para que muestre el titulo de los botones del tab
        @Override
        public CharSequence getPageTitle(int position){

            switch (position){
                case 0:
                    return "Memoria";
                case 1:
                    return "Lenguaje";
                case 2:
                    return "Orientacion";
                case 3:
                    return "Atencion";
                case 4:
                    return "Visual";
                default:
                    return "Memoria";
            }

        }

    }

}

