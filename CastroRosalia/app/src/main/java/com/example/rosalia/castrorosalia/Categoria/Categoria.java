package com.example.rosalia.castrorosalia.Categoria;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rosalia.castrorosalia.Lista.ControladorLista;
import com.example.rosalia.castrorosalia.Lista.ListaCategoria;
import com.example.rosalia.castrorosalia.Principal.PantallaPrincipal;
import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 22/11/2016.
 */
public class Categoria extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcion1){
            Log.d("Menu","Opcion1");
            Intent intent = new Intent(this, PantallaPrincipal.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.opcion2){
            Log.d("Menu","Opcion2");
            return true;
        }
        else
        if (id == R.id.opcion3){
            Log.d("Menu","Opcion3");
            Intent intent3 = new Intent(this,ListaCategoria.class);
            startActivity(intent3);
            return true;
        }
        else
        if (id == R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        ActionBar ab= getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ModeloCategoria modeloCategoria= new ModeloCategoria();
        ControladorCategoria controladorCategoria = new ControladorCategoria(modeloCategoria, this);
        VistaCategoria vistaCategoria = new VistaCategoria(modeloCategoria, this, controladorCategoria);
        controladorCategoria.setMiVista(vistaCategoria);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ControladorCategoria control=new ControladorCategoria();
        if (requestCode == ControladorCategoria.CAMARA){
            if (resultCode  == this.RESULT_OK){
                control.cargarFoto();
            }
        }
    }
}
