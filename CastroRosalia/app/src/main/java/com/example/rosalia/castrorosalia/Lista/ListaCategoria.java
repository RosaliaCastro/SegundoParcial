package com.example.rosalia.castrorosalia.Lista;

import android.bluetooth.le.AdvertiseData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Type;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
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
import com.example.rosalia.castrorosalia.Registrar.ModeloRegistrar;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jona on 22/11/2016.
 */
public class ListaCategoria extends AppCompatActivity implements MyOnItemClick, Handler.Callback {
    public static final int MENSAJE_LISTA = 1;
    HiloLista hiloLista;
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

        Intent intent = getIntent();
        Bundle dato = intent.getExtras();
        String apiKey = dato.getString("apiKey");
        Uri.Builder parametro = new Uri.Builder();
        parametro.appendQueryParameter(apiKey,"AUTHORIZATION");

        Handler.Callback callback = this;
        Handler handler= new Handler(callback);
        hiloLista= new HiloLista(handler);
        hiloLista.obtenerAutorizacion(parametro);
        hiloLista.start();

        //modeloCategorias.add(new ModeloCategoria("Mamiferos","Animales vertebrados con glándulas mamarias."));
        //modeloCategorias.add(new ModeloCategoria("Aves","Animales vertebrados que pueden volar."));
        //modeloCategorias.add(new ModeloCategoria("Anfibios", "Animales vertebrados que sufren metamorfosis."));
        ModeloLista modeloLista= new ModeloLista();
        ControladorLista controladorLista = new ControladorLista(modeloLista, this,apiKey);
        VistaLista vistaLista = new VistaLista(modeloLista,this,controladorLista);
        controladorLista.setMiVista(vistaLista);
    }

    @Override
    public void onItemClick(int position) {
        String param1;
        String param2;

        int posicion= position;

        //List<ModeloCategoria> myLista;
        //Intent panteditar = new Intent();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == MENSAJE_LISTA){
            String archivoJson = null;
            try {
                archivoJson = new String((byte[]) msg.obj,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            List<ModeloLista> modeloListas;
            try {
                modeloListas= ModeloLista.obtenerLista(archivoJson);
                RecyclerView list = (RecyclerView)findViewById(R.id.list_item);
                MyAdapter adapter = new MyAdapter(modeloListas, this);
                list.setAdapter(adapter);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
                list.setLayoutManager(manager);
                adapter.notifyDataSetChanged();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
