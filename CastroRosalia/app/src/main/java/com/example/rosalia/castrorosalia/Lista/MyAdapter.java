package com.example.rosalia.castrorosalia.Lista;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rosalia.castrorosalia.Categoria.ModeloCategoria;
import com.example.rosalia.castrorosalia.R;

import java.util.List;

/**
 * Created by Jona on 22/11/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<ModeloCategoria> lista;
    private MyOnItemClick listener;

    public MyAdapter(List<ModeloCategoria> lista, MyOnItemClick listener){
        this.lista = lista;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layaout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(vista, listener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        ModeloCategoria lisCat = lista.get(position);
        holder.txtCategoria.setText(lisCat.getNombreCategoria());
        holder.txtDescripcion.setText(lisCat.getDescripcionCategoria());
        holder.setPosition(position);


    }
    @Override
    public int getItemCount(){
        return lista.size();
    }
}
