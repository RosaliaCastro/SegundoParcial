package com.example.rosalia.castrorosalia.Principal;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rosalia.castrorosalia.Lista.ListaCategoria;
import com.example.rosalia.castrorosalia.MiDialogo;
import com.example.rosalia.castrorosalia.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class PantallaPrincipal extends AppCompatActivity implements Handler.Callback {
    public static final int MENSAJE_LOGIN=1;
    private HiloLogin miHilo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Handler.Callback callback = this;
        Handler handler = new Handler(callback);
        miHilo = new HiloLogin(handler);
        SharedPreferences preferences =getSharedPreferences("recordarme", Context.MODE_PRIVATE);
        if (this.ValidadPreferencia(preferences) ==true){
            Intent pantalla = new Intent(this, ListaCategoria.class);
            startActivity(pantalla);
        }
        ModeloPrincipal modeloPrincipal = new ModeloPrincipal();
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(modeloPrincipal,this,miHilo);
        VistaPrincipal vistaPrincipal = new VistaPrincipal(modeloPrincipal,this,controladorPrincipal);
        controladorPrincipal.setMiVista(vistaPrincipal);
    }

    public boolean ValidadPreferencia(SharedPreferences preferences){
        Boolean res=true;
        String dat1= preferences.getString("apiKey","null");
        if(dat1 != null){
            res=false;
        }
        return res;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1  == MENSAJE_LOGIN){
            String archivoJson = null;
            try {
                archivoJson = new String((byte[]) msg.obj,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                ModeloPrincipal miMod;
                miMod= ModeloPrincipal.validarLogin(archivoJson);

                if (miMod.getError()== false && new Boolean(String.valueOf(R.id.checkboxRecordar)) == true){
                    String apiKey= null;
                    apiKey = miMod.getApiKey();
                    SharedPreferences miPreferences = null;
                    SharedPreferences.Editor editor = miPreferences.edit();//lo guardo, sharedPreferences para recordarlo.
                    editor.putString("apiKey",apiKey);
                    editor.commit();
                    Intent intent2 = new Intent(this, ListaCategoria.class);
                    startActivity(intent2);
                }else if (miMod.getError()== false){
                    Intent intent2 = new Intent(this, ListaCategoria.class);
                    String api=miMod.getApiKey();
                    intent2.putExtra("apiKey",api);
                    startActivity(intent2);
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
