package com.example.proyectob_pmdm_danielfernandez.rv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectob_pmdm_danielfernandez.DetalleActivity;
import com.example.proyectob_pmdm_danielfernandez.R;
import com.example.proyectob_pmdm_danielfernandez.data.Museo;

import java.util.List;

public class MuseosAdapter extends RecyclerView.Adapter<MuseosAdapter.MuseosVH> implements View.OnClickListener{

    private List<Museo> museos;
    private  View.OnClickListener listener;


    public MuseosAdapter(List<Museo> museos, View.OnClickListener listener) {
        this.museos = museos;
        this.listener = listener;

    }


    @NonNull
    @Override
    public MuseosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_recy, parent, false);
        v.setOnClickListener(this);
        return new MuseosVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseosVH holder, int position) {
        holder.bindMuseo(museos.get(position));
    }

    @Override
    public int getItemCount() {
        return museos.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }

    }


    public static class MuseosVH extends RecyclerView.ViewHolder{

        TextView tvNombre;


        public MuseosVH(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvMuseo);

        }

        public void bindMuseo(Museo museo){
            tvNombre.setText(museo.getTitle());
        }



    }



}