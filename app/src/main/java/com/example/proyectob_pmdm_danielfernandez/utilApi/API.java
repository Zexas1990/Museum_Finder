package com.example.proyectob_pmdm_danielfernandez.utilApi;

import com.example.proyectob_pmdm_danielfernandez.data.MuseosGeneral;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    public static final String URL = "https://datos.madrid.es/egob/catalogo/";

    public static final String KEY = "201132-0-museos.json";

    @GET("{KEY}")
    Call<MuseosGeneral> getMuseos(
            @Path("KEY") String key
    );

    @GET("{KEY}")
    Call<MuseosGeneral> getMuseosDistrito(
            @Path("KEY") String key,
            @Query("distrito_nombre") String distrito
    );

    @GET("tipo/entidadesyorganismos/{id}")
    Call<MuseosGeneral> getMuseoID(
            @Path("id") String id
    )
   ;



    //TODO: Añadir parámetros de búsqueda

}
