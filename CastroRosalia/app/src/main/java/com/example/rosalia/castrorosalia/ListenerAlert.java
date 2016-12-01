package com.example.rosalia.castrorosalia;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.View;

/**
 * Created by Jona on 24/11/2016.
 */
public class ListenerAlert implements OnClickListener {
    @Override
    public void onClick(DialogInterface dialogInterface, int whitc) {
        Log.d("dialog","Click!");

    }
}
