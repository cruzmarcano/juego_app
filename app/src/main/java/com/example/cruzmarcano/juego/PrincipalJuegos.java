package com.example.cruzmarcano.juego;


import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;


public class PrincipalJuegos extends AppCompatActivity implements View.OnClickListener {
    ImageButton memoria, atencion, lenguage,visual, orientacion;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_juegos);

        viewPager =(ViewPager)findViewById(R.id.paginaJuego);

        viewPager.setAdapter(new AdaptadorDePaginas (getSupportFragmentManager()));
        memoria=(ImageButton)findViewById(R.id.memoBtn);
        lenguage=(ImageButton)findViewById(R.id.lengBtn);
        atencion=(ImageButton)findViewById(R.id.atenBtn);
        orientacion=(ImageButton)findViewById(R.id.orienBtn);
        visual=(ImageButton)findViewById(R.id.visualBtn);

        memoria.setOnClickListener(this);
        lenguage.setOnClickListener(this);
        atencion.setOnClickListener(this);
        orientacion.setOnClickListener(this);
        visual.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.memoBtn:
                viewPager.setCurrentItem(0);
                break;
            case R.id.lengBtn:
                viewPager.setCurrentItem(1);
                break;
            case R.id.atenBtn:
                viewPager.setCurrentItem(2);
                break;
            case R.id.orienBtn:
                viewPager.setCurrentItem(3);

                break;
            case R.id.visualBtn:

                viewPager.setCurrentItem(4);
                break;
        }
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

    }

}

