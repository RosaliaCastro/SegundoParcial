package com.example.rosalia.castrorosalia.Registrar;

import com.example.rosalia.castrorosalia.Principal.ModeloPrincipal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jona on 22/11/2016.
 */
public class ModeloRegistrar {
    private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private String clave;
    private boolean error;
    private String mensaje;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEmail(Boolean error){return email;}

    public String getClave() {
        return clave;
    }

    public Boolean getError() {
        return error;
    }

    public String getMensaje(){return this.mensaje;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEmail(String email){this.email=email;}

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setError(Boolean error){this.error = error;}

    public void setMensaje(String mensaje) {this.mensaje = mensaje;}

    static ModeloRegistrar registrar (String archivo)throws XmlPullParserException, IOException, JSONException {
        ModeloRegistrar registrar= new ModeloRegistrar();
        JSONObject objetoJson = new JSONObject (archivo);
        try {
                Boolean error = objetoJson.getBoolean("error");
                String mensaje =objetoJson.getString("message");
                registrar.setError(error);
                registrar.setMensaje(mensaje);
        } catch (JSONException ejson){
            ejson.printStackTrace();
        }
        return registrar;
    }
}
