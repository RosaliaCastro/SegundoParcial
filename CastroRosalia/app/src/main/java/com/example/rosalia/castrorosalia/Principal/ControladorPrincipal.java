package com.example.rosalia.castrorosalia.Principal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.rosalia.castrorosalia.Lista.ListaCategoria;
import com.example.rosalia.castrorosalia.MiDialogo;
import com.example.rosalia.castrorosalia.R;
import com.example.rosalia.castrorosalia.Registrar.PantallaRegistrar;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jona on 22/11/2016.
 */
public class ControladorPrincipal implements View.OnClickListener {

    private ModeloPrincipal miModelo;
    private VistaPrincipal miVista;
    private Activity miActivity;
    String email;
    String clave;
    String apikey;
    //private MiDialogo miDialogo;
    SharedPreferences miPreferences;
    //private List<ModeloPrincipal> ListaUser;
    private HiloLogin myHilo;

    public ControladorPrincipal(ModeloPrincipal modelo, Activity actividad, SharedPreferences preferences,HiloLogin hiloLogin) {
        miModelo = modelo;
        miActivity = actividad;
        miPreferences = preferences;
        //ListaUser=list;
       //miDialogo=myDialogo;
        myHilo = hiloLogin;
    }
    public ControladorPrincipal(){}
    public ControladorPrincipal(VistaPrincipal vista) {
        miVista = vista;
    }

    public void setMiVista(VistaPrincipal miVista) {
        this.miVista = miVista;
    }

    private void startActivity(Intent intent) {
        miActivity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnRegistrarme) {
            Intent intent1 = new Intent(miActivity, PantallaRegistrar.class);
            startActivity(intent1);
            miVista.Limpiar();
        } else if (view.getId() == R.id.checkboxRecordar && view.getId() == R.id.btnIngresar) {
                clave = miVista.traerClave();
                email = miVista.traerEmail();
                ConsultarUsuario(email, clave);
                SharedPreferences.Editor editor = miPreferences.edit();//lo guardo, sharedPreferences para recordarlo.
                editor.putString("apiKey",apikey);
                editor.commit();
                }else if (view.getId() == R.id.btnIngresar) {
                    clave = miVista.traerClave();
                    email = miVista.traerEmail();
                    ConsultarUsuario(email,clave);
                }
    }

    public void ConsultarUsuario(String email, String clave) {
        Uri.Builder parametro = new Uri.Builder();
        parametro.appendQueryParameter("email",email);
        parametro.appendQueryParameter("password",clave);
        myHilo.traerParametros(parametro);
        myHilo.start();
    }
    public void guardarApiKey (String apiKey){
        apikey=apiKey;
    }
}

//for(ModeloPrincipal mod:ListaUser){
//if (mod.getMiEmail().equals(email)&& (mod.getMiClave().equals(clave))){
//respuesta=true;
//}
//}