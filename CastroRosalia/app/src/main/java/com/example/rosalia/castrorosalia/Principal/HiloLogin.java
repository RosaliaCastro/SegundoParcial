package com.example.rosalia.castrorosalia.Principal;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by Jona on 28/11/2016.
 */
public class HiloLogin extends Thread {
    private Handler handler;
    private Uri.Builder myParametro;
    public HiloLogin (Handler miHandler){
        handler=miHandler;
    }
    public void traerParametros(Uri.Builder parametro){
        myParametro=parametro;
    }

    @Override
    public void run() {
        super.run();
        String ruta="http://lkdml.myq-see.com/login";
        byte [] informacion= null;
        HttpManegerLogin httpManegerLogin = new HttpManegerLogin();
        try{
            informacion = httpManegerLogin.login(ruta,myParametro);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(1000);
            Message miMensage = new Message();
            miMensage.arg1=PantallaPrincipal.MENSAJE_LOGIN;
            miMensage.obj = informacion;
            handler.sendMessage(miMensage);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
