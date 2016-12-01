package com.example.rosalia.castrorosalia.Lista;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jona on 24/11/2016.
 */
public class ModeloLista {
    private String nombreCategoria;
    private String descripcionCategoria;

    public ModeloLista(String cat, String des) {
        nombreCategoria=cat;
        descripcionCategoria= des;
    }
    public ModeloLista(){}

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    static List<ModeloLista> obtenerLista(String archivo) throws XmlPullParserException, IOException, JSONException{
        List<ModeloLista> miList = new ArrayList<>();

        JSONObject objetoJson = new JSONObject(archivo);
        JSONArray jUsuarios = objetoJson.getJSONArray("categorias");
        try {
            for (int i = 0; i < jUsuarios.length(); i++){
              JSONObject c = jUsuarios.getJSONObject(i);
                String nombre=c.getString("titulo");
                String descripcion=c.getString("desc");
                miList.add(new ModeloLista(nombre,descripcion));
            }
        } catch (JSONException ejson){
            ejson.printStackTrace();
        }
        return miList;
    }
}
