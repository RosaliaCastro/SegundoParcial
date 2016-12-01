package com.example.rosalia.castrorosalia.Lista;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.rosalia.castrorosalia.Categoria.Categoria;
import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 23/11/2016.
 */
public class ControladorLista implements View.OnClickListener {
    ModeloLista myModelo;
    VistaLista myVista;
    Activity myActivity;
    String autorizacion;

    public ControladorLista(ModeloLista modelo, Activity activity, String apiKey){
        myModelo=modelo;
        myActivity=activity;
        autorizacion = apiKey;
    }
    public ControladorLista (VistaLista vistaLista){myVista=vistaLista;}
    public void setMiVista(VistaLista miVista) {
        this.myVista = miVista;
    }
    private void startActivity(Intent intent) {
        myActivity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fabAgregar){
            Intent intent = new Intent(myActivity, Categoria.class);
            intent.putExtra("apiKey", autorizacion);
            startActivity(intent);

        }
    }
}
