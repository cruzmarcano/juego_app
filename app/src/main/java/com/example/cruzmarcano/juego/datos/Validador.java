package com.example.cruzmarcano.juego.datos;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.cruzmarcano.juego.R;

import java.util.ArrayList;

/**
 * Created by cruzmarcano on 18/1/18.
 */

public class Validador {

    public Validador() {

    }

    public void validaTesto(final EditText e ){



        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().matches(".*[^a-zA-Z].*")){
                    int c = e.getText().length()-1;
                    String t=e.getText().toString();
                    e.setText(t.substring(0,c));
                    e.setSelection(c);
                    e.setError("solo puede ingresar letras");
                }

                if (charSequence.length()>3){
                    e.setBackgroundResource(R.color.blanco);

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


    }

    public void validaClave(final EditText e ){

        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.length()<4){
                    e.setError("debe ingresar 4 numeros");
                }
                if (charSequence.length()>3){
                    e.setBackgroundResource(R.color.blanco);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    public Boolean revision (ArrayList<EditText> a){

        int n=0;

        for (int i=0; i<=a.size()-1;i++){
            if(a.get(i).getText().length()<=3){
                a.get(i).setBackgroundResource(R.color.rosa);
                n=n+1;

            };
        }

        if(n!=0){
            return true;
        }else{
            return false;
        }

    };
}
