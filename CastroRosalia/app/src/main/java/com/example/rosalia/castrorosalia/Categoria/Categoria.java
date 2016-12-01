package com.example.rosalia.castrorosalia.Categoria;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rosalia.castrorosalia.Lista.ControladorLista;
import com.example.rosalia.castrorosalia.Lista.ListaCategoria;
import com.example.rosalia.castrorosalia.Principal.PantallaPrincipal;
import com.example.rosalia.castrorosalia.R;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Jona on 22/11/2016.
 */
public class Categoria extends AppCompatActivity implements Handler.Callback {
    public static final int MENSAJE_CATEGORIA= 1;
    HiloCategoria hiloCategoria;

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
        Intent intent = getIntent();
        Bundle dato = intent.getExtras();
        String apiKey = dato.getString("apiKey");

        Handler.Callback callback = this;
        Handler handler=new Handler(callback);
        hiloCategoria = new HiloCategoria(handler);


        ModeloCategoria modeloCategoria= new ModeloCategoria();
        ControladorCategoria controladorCategoria = new ControladorCategoria(modeloCategoria, this, hiloCategoria, apiKey);
        VistaCategoria vistaCategoria = new VistaCategoria(modeloCategoria, this, controladorCategoria);
        controladorCategoria.setMiVista(vistaCategoria);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ControladorCategoria control=new ControladorCategoria();
        if (requestCode == ControladorCategoria.CAMARA){
            if (resultCode  == this.RESULT_OK){
                control.cargarFoto();
                //en caso de guardar la foto.
            }
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == MENSAJE_CATEGORIA)
        {
            String archivoJS=null;
            try{
                archivoJS= new String ((byte[])msg.obj,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try{
                ModeloCategoria modeloCategoria;
                modeloCategoria= ModeloCategoria.obtenerObj(archivoJS);
                Intent intent = new Intent(this,ListaCategoria.class);
                intent.putExtra(modeloCategoria.getNombreCategoria(),"titulo");
                intent.putExtra(modeloCategoria.getDescripcionCategoria(),"descripcion");
                startActivity(intent);

            }catch (XmlPullParserException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
