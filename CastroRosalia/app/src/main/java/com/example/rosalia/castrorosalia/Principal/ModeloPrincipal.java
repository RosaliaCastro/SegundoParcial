package com.example.rosalia.castrorosalia.Principal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jona on 22/11/2016.
 */
public class ModeloPrincipal {

    public ModeloPrincipal(){}

    private String miClave;
    private String miEmail;
    private boolean error;
    private String apiKey;
    public String getMiClave() {
        return miClave;
    }

    public void setMiClave(String miClave) {
        this.miClave = miClave;
    }

    public boolean getError(){return error;}

    public void setError (boolean error){this.error= error;}

    public String getApiKey(){return apiKey;}

    public void setApiKey(String apiKey){this.apiKey=apiKey;}

    public String getMiEmail() {
        return miEmail;
    }

    public void setMiEmail(String miEmail) {
        this.miEmail = miEmail;
    }

    static ModeloPrincipal validarLogin (String archivo)throws XmlPullParserException, IOException, JSONException{
        ModeloPrincipal miMod = new ModeloPrincipal();
        JSONObject objetoJson = new JSONObject(archivo);
        JSONArray jUsuarios = objetoJson.getJSONArray("");

        try {
            for (int i = 0; i < jUsuarios.length(); i++){
                JSONObject c = jUsuarios.getJSONObject(i);
                miMod.setError(c.getBoolean("error"));
                miMod.setApiKey(c.getString("apiKey"));
            }
        } catch (JSONException ejson){
            ejson.printStackTrace();
        }
        return miMod;
    }
}
