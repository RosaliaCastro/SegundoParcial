package com.example.rosalia.castrorosalia.Registrar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.rosalia.castrorosalia.Lista.ListaCategoria;
import com.example.rosalia.castrorosalia.Principal.PantallaPrincipal;
import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 22/11/2016.
 */
public class ControladorRegistrar implements View.OnClickListener {

    ModeloRegistrar miModelo;
    Activity miActivity;
    VistaRegistrar miVista;
    HiloRegistrar myHiloRegistrar;

    String nombre;
    String apellido;
    String usuario;
    String email;
    String clave;

    public ControladorRegistrar(ModeloRegistrar mod, Activity actividad, HiloRegistrar hilo){
        miModelo = mod;
        miActivity= actividad;
        myHiloRegistrar=hilo;
    }
    private void startActivity(Intent intent){
        miActivity.startActivity(intent);
    }
    public void setMiVista(VistaRegistrar vistaRegistrar){this.miVista = vistaRegistrar;}

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.btnRegistrarseR){
            nombre= miVista.TraerNombre();
            apellido=miVista.TraerApellido();
            usuario=miVista.TraerUsuario();
            email=miVista.TraerEmail();
            clave=miVista.TraerClave();
            Registrar(nombre, apellido, usuario,email,clave);
            miVista.Limpiar();
        }else if(view.getId()== R.id.btnCancelarR){
            miVista.Limpiar();
            Intent volverPrincipal = new Intent(miActivity,PantallaPrincipal.class);
            startActivity(volverPrincipal);
        }

    }
    public void Registrar(String myNombre, String myApellido, String myUsuario, String myEmail, String myClave){
        Uri.Builder parametro = new Uri.Builder();
        parametro.appendQueryParameter("nombre",myNombre);
        parametro.appendQueryParameter("apellido",myApellido);
        parametro.appendQueryParameter("usuario",myUsuario);
        parametro.appendQueryParameter("email",myEmail);
        parametro.appendQueryParameter("password",myClave);
        myHiloRegistrar.traerParametros(parametro);
        myHiloRegistrar.start();
    }
}

