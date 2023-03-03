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
    private View.OnClickListener listener;


    public MuseosAdapter(List<Museo> museos) {
        this.museos = museos;

    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public MuseosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_recy, parent, false);

        return new MuseosVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseosVH holder, int position) {
        Museo museo = museos.get(position);
        holder.bindMuseo(museo);
    }

    @Override
    public int getItemCount() {
        return museos.size();
    }



    public static class MuseosVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNombre;
        Museo museo;

        public MuseosVH(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvMuseo);
            itemView.setOnClickListener(this);
        }

        public void bindMuseo(Museo museo){

            tvNombre.setText(museo.getTitle());
        }


        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent intent = new Intent(context, DetalleActivity.class);
            //intent.putExtra("nombre", museo.getTitle());
            //intent.putExtra("distrito", String.valueOf(museo.getAddress()));
            //intent.putExtra("descripcion", museo.getRelation());
            context.startActivity(intent);
        }
    }



}
