package com.example.rosalia.castrorosalia.Categoria;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.rosalia.castrorosalia.Lista.ListaCategoria;
import com.example.rosalia.castrorosalia.R;

import java.io.File;


/**
 * Created by Jona on 22/11/2016.
 */
public class ControladorCategoria extends AppCompatActivity implements View.OnClickListener  {

    public static final int CAMARA=1;
    public static final int CAMARA_PERMISO=100;
    Uri uri;

    private ModeloCategoria miMod;
    private Activity myActivity;
    private VistaCategoria miVista;
    String nuevoNombre;
    String nuevaDescripcion;
    public ControladorCategoria(){}

    public ControladorCategoria(ModeloCategoria mod, Activity activity){
        miMod=mod;
        myActivity=activity;
    }
    public void setMiVista(VistaCategoria vista){miVista =vista;}

    public void cargarFoto(){
        ImageView imagen;
        imagen= (ImageView)myActivity.findViewById(R.id.imagenNueva);
        imagen.setImageURI(uri);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.btnTomarFoto){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File imageFolder = new File(Environment.getExternalStorageDirectory(), "NuevaCategoria");
            imageFolder.mkdirs();
            File image = new File(imageFolder, "image_001.jpg");
            uri= Uri.fromFile(image);
            if(ContextCompat.checkSelfPermission(myActivity, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(myActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(myActivity,new String[]{android.Manifest.permission.CAMERA},CAMARA_PERMISO);
                cargarFoto();
            } else{
                myActivity.startActivityForResult(intent, CAMARA);
            }
        }else if(view.getId()==R.id.btnCrear){
            nuevoNombre = miVista.traerNomCategoria();
            nuevaDescripcion = miVista.traerDesCategoria();
            miMod.setNombreCategoria(nuevoNombre);
            miMod.setDescripcionCategoria(nuevaDescripcion);

            //crear un objeto categoria y agregarlo a la lista

        }
    }


}
