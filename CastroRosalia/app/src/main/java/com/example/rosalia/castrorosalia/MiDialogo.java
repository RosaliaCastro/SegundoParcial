package com.example.rosalia.castrorosalia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by Jona on 24/11/2016.
 */
public class MiDialogo extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Atencion!!!");
        builder.setMessage("Has ingresado datos incorrectos o no se a registrado aun");

        ListenerAlert uno = new ListenerAlert();
        builder.setPositiveButton("Aceptar", uno);

        AlertDialog crear = builder.create();
        return crear;

    }


}
