package com.example.proyectob_pmdm_danielfernandez;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectob_pmdm_danielfernandez.data.Museo;
import com.example.proyectob_pmdm_danielfernandez.data.MuseosGeneral;
import com.example.proyectob_pmdm_danielfernandez.rv.MuseosAdapter;
import com.example.proyectob_pmdm_danielfernandez.utilApi.API;
import com.example.proyectob_pmdm_danielfernandez.utilApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ListaFragment extends Fragment  {
    RecyclerView rv;
    List<Museo> museos = new ArrayList<>();
    LinearLayoutManager llmanager;
    MuseosAdapter adapter;

    Museo museo;


    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //adapter = new MuseosAdapter(museos);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista, container, false);

        rv = v.findViewById(R.id.rvMuseos);
        llmanager = new LinearLayoutManager(getContext());
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llmanager);

        String distrito = getArguments().getString("distrito");
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(distrito);

        consultarApiDistrito(distrito);


        return v;


    }

    private void consultarApiDistrito(String distrito) {
        Retrofit ret = RetrofitClient.getClient(API.URL);
        API api = ret.create(API.class);
        Call<MuseosGeneral> call = null;

        if (distrito != null) {
            call = api.getMuseosDistrito(API.KEY, distrito);
        }else {
            call = api.getMuseos(API.KEY);
        }

        call.enqueue(new Callback<MuseosGeneral>() {
            @Override
            public void onResponse(Call<MuseosGeneral> call, @NonNull Response<MuseosGeneral> response) {
                if (response.isSuccessful()) {
                    MuseosGeneral museosGeneral = response.body();
                    cargarRV(museosGeneral.getMuseos());

                }
            }
            @Override
            public void onFailure(Call<MuseosGeneral> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error en la consulta", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void cargarRV(List<Museo> museos) {

        llmanager = new LinearLayoutManager(getContext());
        adapter = new MuseosAdapter(museos);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llmanager);
        rv.setAdapter(adapter);
    }
/*
    public void onClick(View v) {
        int pos = rv.getChildAdapterPosition(v);
        String nombre = museos.get(pos).getTitle();
        String distrito = String.valueOf(museos.get(pos).getAddress());
        String descripcion = museos.get(pos).getRelation();

        Intent i = new Intent(getContext(), DetalleActivity.class);
        i.putExtra("nombre", nombre);
        i.putExtra("distrito", distrito);
        i.putExtra("descripcion", descripcion);
        startActivity(i);

    }


    @Override
    public void onClick(View v) {
        int pos = rv.getChildAdapterPosition(v);
        String id = museos.get(pos).getId();
        Intent i = new Intent(v.getContext(), DetalleActivity.class);
        System.out.println(museo.getAddress().getDistrict().getId());
        String districtId = museo.getAddress().getDistrict().getId();
        String[] partsDis = districtId.split("/");

    }
    */

}