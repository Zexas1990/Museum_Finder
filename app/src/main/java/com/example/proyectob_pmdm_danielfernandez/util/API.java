package com.example.proyectob_pmdm_danielfernandez.util;

import com.example.proyectob_pmdm_danielfernandez.data.MuseosGeneral;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    String URL = "https://datos.madrid.es/egob/catalogo/";

    @GET("201132-0-museos.json")
    Call<MuseosGeneral> getMuseos(
            @Query("distrito_nombre") String districto
    );

}
