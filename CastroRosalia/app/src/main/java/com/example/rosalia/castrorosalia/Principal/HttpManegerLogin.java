package com.example.rosalia.castrorosalia.Principal;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UTFDataFormatException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jona on 28/11/2016.
 */
public class HttpManegerLogin {

    public byte[] login (String urlString, Uri.Builder postParametro) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection conexion =(HttpURLConnection) url.openConnection();
        conexion.setReadTimeout(10000);
        conexion.setConnectTimeout(15000);
        conexion.setRequestMethod("POST");
        //conexion.connect();
        conexion.setDoOutput(true);
        String query = postParametro.build().getEncodedQuery();
        OutputStream os = conexion.getOutputStream();
        BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        escribir.write(query);
        escribir.flush();
        escribir.close();
        os.close();
        int response = conexion.getResponseCode();
        Log.d("http", "Response code:" + response);
        if (response == 200 || response == 201){
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
