package com.example.rosalia.castrorosalia.Registrar;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 22/11/2016.
 */
public class VistaRegistrar {
    ModeloRegistrar miModelo;
    private EditText nombre;
    private EditText apellido;
    private EditText usuario;
    private EditText email;
    private EditText clave;
    private Button registrarme;
    private Button cancelar;

    public VistaRegistrar(ModeloRegistrar mod, Activity activity, ControladorRegistrar controladorRegistrar){

        miModelo = mod;
        nombre=(EditText)activity.findViewById(R.id.editTextNombreR);
        apellido=(EditText)activity.findViewById(R.id.editTextApellidoR);
        usuario=(EditText)activity.findViewById(R.id.editTextUsuarioR);
        email=(EditText)activity.findViewById(R.id.editTextEmailR);
        clave=(EditText)activity.findViewById(R.id.editTextClaveR);
        registrarme=(Button)activity.findViewById(R.id.btnRegistrarseR);
        cancelar=(Button)activity.findViewById(R.id.btnCancelarR);
        registrarme.setOnClickListener(controladorRegistrar);
        cancelar.setOnClickListener(controladorRegistrar);
    }
    public String TraerNombre(){
        String miNombre;
        miNombre = nombre.getText().toString();
        return miNombre;
    }
    public String TraerApellido(){
        String myApellido;
        myApellido = apellido.getText().toString();
        return myApellido;
    }
    public String TraerUsuario(){
        String myUsuario;
        myUsuario = usuario.getText().toString();
        return myUsuario;
    }
    public String TraerClave(){
        String myClave;
        myClave = clave.getText().toString();
        return myClave;
    }
    public String TraerEmail(){
        String myEmail;
        myEmail = email.getText().toString();
        return myEmail;
    }
    public void Limpiar(){
        nombre.setText("");
        apellido.setText("");
        usuario.setText("");
        email.setText("");
        clave.setText("");

    }
}
