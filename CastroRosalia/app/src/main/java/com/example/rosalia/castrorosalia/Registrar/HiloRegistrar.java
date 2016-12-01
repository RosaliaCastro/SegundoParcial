package com.example.rosalia.castrorosalia.Registrar;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;

/**
 * Created by Jona on 28/11/2016.
 */
public class HiloRegistrar extends Thread {
    private Handler handler;
    private Uri.Builder myParametro;

    public HiloRegistrar (Handler miHandler){
        handler=miHandler;
    }
    public void traerParametros(Uri.Builder parametro){
        myParametro=parametro;
    }

    @Override
    public void run() {
        super.run();
        String ruta="http://lkdml.myq-see.com/register";
        byte [] informacion= null;
        HttpManegerRegistar httpManegerRegistar = new HttpManegerRegistar();
        try{
            informacion=httpManegerRegistar.registrarUsuario(ruta,myParametro);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(1000);
            Message miMensage = new Message();
            miMensage.arg1= PantallaRegistrar.MENSAJE_REGISTRAR;
            miMensage.obj=informacion;
            handler.sendMessage(miMensage);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
