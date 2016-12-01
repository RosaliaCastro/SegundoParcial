package com.example.rosalia.castrorosalia.Lista;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;

import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 23/11/2016.
 */
public class VistaLista {

    FloatingActionButton agregar;
    ModeloLista myModelo;

    public VistaLista(ModeloLista mod, Activity activity, ControladorLista controladorLista){
        myModelo=mod;
        agregar=(FloatingActionButton)activity.findViewById(R.id.fabAgregar);
        agregar.setOnClickListener(controladorLista);
    }
}
