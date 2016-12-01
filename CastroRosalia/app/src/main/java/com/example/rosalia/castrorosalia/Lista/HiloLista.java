package com.example.rosalia.castrorosalia.Lista;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by Jona on 28/11/2016.
 */
public class HiloLista extends Thread {
    private Handler myHandler;
    Uri.Builder myParametro;
    public HiloLista(Handler handler){myHandler=handler;}
    public void obtenerAutorizacion(Uri.Builder parametro){
        myParametro=parametro;
    }

    @Override
    public void run() {
        super.run();
        String ruta="http://lkdml.myq-see.com/categorias";
        byte [] informacion=null;
        HttpManegerLista httpManegerLista = new HttpManegerLista();
        try{
            informacion = httpManegerLista.obtenerLista(ruta, myParametro);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(10000);
            Message miMensaje= new Message();
            miMensaje.arg1=ListaCategoria.MENSAJE_LISTA;
            miMensaje.obj=informacion;
            myHandler.sendMessage(miMensaje);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
