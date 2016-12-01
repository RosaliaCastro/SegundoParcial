package com.example.rosalia.castrorosalia.Lista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rosalia.castrorosalia.Categoria.Categoria;
import com.example.rosalia.castrorosalia.Categoria.ModeloCategoria;
import com.example.rosalia.castrorosalia.Principal.PantallaPrincipal;
import com.example.rosalia.castrorosalia.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jona on 22/11/2016.
 */
public class ListaCategoria extends AppCompatActivity implements MyOnItemClick, Handler.Callback {

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
            //mostrar la lista filtrada por favoritos.
            return true;
        }
        else
        if (id == R.id.opcion3){
            Log.d("Menu","Opcion3");
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
        setContentView(R.layout.activity_lista_de_categorias);

        ActionBar ab= getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        List<ModeloCategoria> modeloCategorias = new ArrayList<ModeloCategoria>();

        modeloCategorias.add(new ModeloCategoria("Mamiferos","Animales vertebrados con glándulas mamarias."));
        modeloCategorias.add(new ModeloCategoria("Aves","Animales vertebrados que pueden volar."));
        modeloCategorias.add(new ModeloCategoria("Anfibios", "Animales vertebrados que sufren metamorfosis."));
        modeloCategorias.add(new ModeloCategoria("Reptiles", "Animales vertebrados cubiertos de escamas"));
        modeloCategorias.add(new ModeloCategoria("Peces", "Animales vertebrados acuaticos"));
        modeloCategorias.add(new ModeloCategoria("Artropodos", "Animales invertebrados tienen exoesqueleto"));
        modeloCategorias.add(new ModeloCategoria("Moluscos", "Esta es una breve descrpcion de la categoria"));
        modeloCategorias.add(new ModeloCategoria("Esponjas", "Esta es una breve descrpcion de la categoria"));
        modeloCategorias.add(new ModeloCategoria("Celentereos", "Esta es una breve descrpcion de la categoria"));
        modeloCategorias.add(new ModeloCategoria("Equinodermos", "Esta es una breve descrpcion de la categoria"));

        RecyclerView list = (RecyclerView)findViewById(R.id.list_item);

        MyAdapter adapter = new MyAdapter(modeloCategorias, this);
        list.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        adapter.notifyDataSetChanged();

        ModeloLista modeloLista= new ModeloLista();
        ControladorLista controladorLista = new ControladorLista(modeloLista, this);
        VistaLista vistaLista = new VistaLista(modeloLista,this,controladorLista);
        controladorLista.setMiVista(vistaLista);
    }

    @Override
    public void onItemClick(int position) {
        String param1;
        String param2;
        ImageView imagen;
        int posicion= position;




        //List<ModeloCategoria> myLista;


        //Intent panteditar = new Intent();

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
