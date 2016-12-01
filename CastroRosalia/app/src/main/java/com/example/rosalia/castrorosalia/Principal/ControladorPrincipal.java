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
    private HiloLogin myHilo;

    public ControladorPrincipal(ModeloPrincipal modelo, Activity actividad,HiloLogin hiloLogin) {
        miModelo = modelo;
        miActivity = actividad;
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
}
