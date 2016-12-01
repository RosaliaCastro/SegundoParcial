package com.example.rosalia.castrorosalia.Lista;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jona on 28/11/2016.
 */
public class HttpManegerLista {
    public byte[] obtenerLista (String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conexion =(HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.connect();
        int response = conexion.getResponseCode();
        Log.d("http", "Response code:" + response);
        if (response == 200){
            InputStream is = conexion.getInputStream();
            ByteArrayOutputStream baos= new ByteArrayOutputStream();
            byte[] buffet = new byte[1024];
            int length = 0;
            while((length = is.read(buffet))!= -1){
                baos.write(buffet,0,length);
            }
            is.close();
            conexion.disconnect();
            return baos.toByteArray();
        }else
            throw new IOException();
    }
}
