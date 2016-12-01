package com.example.rosalia.castrorosalia.Categoria;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by Jona on 01/12/2016.
 */
public class HiloCategoria extends Thread {
    Handler myHandler;
    Uri.Builder myParametro;
    public HiloCategoria (Handler handler){myHandler=handler;}
    public void traerParametros(Uri.Builder parametro){
        myParametro=parametro;
    }
    @Override
    public void run() {
        super.run();
        String ruta="http://lkdml.myq-see.com/categorias";
        byte [] informacion=null;
        HttpManegerCategoria httpManegerCategoria = new HttpManegerCategoria();
        try{
            informacion=httpManegerCategoria.agregarCategoria(ruta,myParametro);
    }catch (IOException e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000);
            Message miMensage = new Message();
            miMensage.arg1=Categoria.MENSAJE_CATEGORIA;
            miMensage.obj=informacion;
            myHandler.sendMessage(miMensage);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
