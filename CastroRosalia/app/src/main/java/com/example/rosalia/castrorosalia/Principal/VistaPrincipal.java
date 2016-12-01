package com.example.rosalia.castrorosalia.Principal;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 22/11/2016.
 */
public class VistaPrincipal {

    private ModeloPrincipal miModeloP;
    private Button registrarme;
    private Button ingresar;
    private CheckBox recordarme;
    private EditText email;
    private EditText clave;


    public VistaPrincipal(ModeloPrincipal modeloPrincipal, Activity activity, ControladorPrincipal controladorPrincipal) {

        miModeloP = modeloPrincipal;

        registrarme = (Button) activity.findViewById(R.id.btnRegistrarme);
        ingresar = (Button) activity.findViewById(R.id.btnIngresar);

        email = (EditText) activity.findViewById(R.id.editTextEmail);
        clave = (EditText) activity.findViewById(R.id.editTextClave);

        recordarme=(CheckBox)activity.findViewById(R.id.checkboxRecordar);

        registrarme.setOnClickListener(controladorPrincipal);
        ingresar.setOnClickListener(controladorPrincipal);
        recordarme.setOnClickListener(controladorPrincipal);
    }

    public String traerEmail() {
        String miEmail = email.getText().toString();
        return miEmail;
    }

    public String traerClave() {
        String miClave = clave.getText().toString();
        return miClave;
    }
    public void Limpiar(){
        this.clave.setText("");
        this.email.setText("");
    }

}
