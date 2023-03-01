package com.example.proyectob_pmdm_danielfernandez.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MuseosGeneral {

    @SerializedName("@context")
    @Expose
    private Context context;
    @SerializedName("@graph")
    @Expose
    private List<Museo> museos;

    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public List<Museo> getMuseos() {

        return museos;
    }

    public void setMuseos(List<Museo> museo) {

        this.museos = museo;
    }

}