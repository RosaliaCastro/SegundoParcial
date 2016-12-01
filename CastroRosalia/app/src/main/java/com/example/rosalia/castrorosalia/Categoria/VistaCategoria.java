package com.example.rosalia.castrorosalia.Categoria;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.rosalia.castrorosalia.R;

/**
 * Created by Jona on 22/11/2016.
 */
public class VistaCategoria {
    private ModeloCategoria modeloCategoria;
    private EditText nombreCategoria;
    private EditText descricionCategoria;
    //private ImageView nuevaImag;
    private Button crear;
    private Button tomarFoto;
    private CheckBox favorito;

    public VistaCategoria(ModeloCategoria mod, Activity activity, ControladorCategoria contCategoria){
        modeloCategoria = mod;

        nombreCategoria= (EditText)activity.findViewById(R.id.textNomCategoria);
        descricionCategoria=(EditText)activity.findViewById(R.id.textDesCategoria);
        //nuevaImag=(ImageView)activity.findViewById(R.id.imagenNueva);
        favorito=(CheckBox)activity.findViewById(R.id.checkFavorito);
        tomarFoto=(Button) activity.findViewById(R.id.btnTomarFoto);
        crear = (Button)activity.findViewById(R.id.btnCrear);

        favorito.setOnClickListener(contCategoria);
        crear.setOnClickListener(contCategoria);
        tomarFoto.setOnClickListener(contCategoria);
    }
    public String traerNomCategoria() {
        String nombre = nombreCategoria.getText().toString();
        return nombre;
    }

    public String traerDesCategoria() {
        String descripcion = descricionCategoria.getText().toString();
        return descripcion;
    }
    //public ImageView TraerImagen(){
       // return nuevaImag;
    //}

    public void Limpiar(){
        nombreCategoria.setText("");
        descricionCategoria.setText("");
    }
    //public void MostrarFoto(Uri imageView){
        //nuevaImag.setImageURI(imageView);
        //tomarFoto.setEnabled(false);
    //}
}
