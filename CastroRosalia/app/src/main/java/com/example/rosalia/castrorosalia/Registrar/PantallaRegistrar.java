package com.example.rosalia.castrorosalia.Registrar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;

import com.example.rosalia.castrorosalia.MiDialogo;
import com.example.rosalia.castrorosalia.Principal.PantallaPrincipal;
import com.example.rosalia.castrorosalia.R;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Jona on 22/11/2016.
 */
public class PantallaRegistrar extends AppCompatActivity implements Handler.Callback {
    public static final int MENSAJE_REGISTRAR=1;
    HiloRegistrar hiloRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registrar);
        Handler.Callback callback = this;
        Handler handler = new Handler(callback);
        hiloRegistrar = new HiloRegistrar(handler);
        ModeloRegistrar modeloRegistrar = new  ModeloRegistrar();
        ControladorRegistrar controladorRegistrar= new ControladorRegistrar(modeloRegistrar,this, hiloRegistrar);
        VistaRegistrar vistaRegistrar = new VistaRegistrar(modeloRegistrar,this,controladorRegistrar);
        controladorRegistrar.setMiVista(vistaRegistrar);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 == MENSAJE_REGISTRAR){
            String archivoJson = null;
            try {
                archivoJson = new String((byte[]) msg.obj,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                ModeloRegistrar registrar;
                registrar= ModeloRegistrar.registrar(archivoJson);
                if(registrar.getError()== false){
                    Intent pantCat = new Intent(this, PantallaPrincipal.class);
                    startActivity(pantCat);
                }else if (registrar.getError()== true){
                    String mensaje;
                    mensaje = registrar.getMensaje();
                    //ver dialogo
                }

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
