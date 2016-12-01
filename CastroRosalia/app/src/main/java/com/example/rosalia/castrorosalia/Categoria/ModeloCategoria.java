package com.example.rosalia.castrorosalia.Categoria;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Jona on 22/11/2016.
 */
public class ModeloCategoria {
    private String nombreCategoria;
    private String descripcionCategoria;

    public ModeloCategoria(String cat, String des) {
        nombreCategoria=cat;
        descripcionCategoria= des;
    }
    public ModeloCategoria(){}

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
    static ModeloCategoria obtenerObj(String archivo)throws XmlPullParserException, IOException, JSONException {
        ModeloCategoria mod = new ModeloCategoria();
        JSONObject miObje= new JSONObject(archivo);
        String titulo=miObje.getString("titulo");
        String descripcion=miObje.getString("descripcion");
        mod.setNombreCategoria(titulo);
        mod.setDescripcionCategoria(descripcion);
        return mod;
    }
}
