package com.example.proyectob_pmdm_danielfernandez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectob_pmdm_danielfernandez.data.Museo;
import com.example.proyectob_pmdm_danielfernandez.data.MuseosGeneral;
import com.example.proyectob_pmdm_danielfernandez.utilApi.API;
import com.example.proyectob_pmdm_danielfernandez.utilApi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetalleActivity extends AppCompatActivity {

    MuseosGeneral museosGeneral;

    TextView tvNombre, tvDistrito, tvArea, tvDescripcion, tvHoraio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        String id = getIntent().getStringExtra("id");


        cargarAPI(id);


        tvNombre = findViewById(R.id.tvNombre);
        tvDistrito = findViewById(R.id.tvDistrito);
        tvArea = findViewById(R.id.tvArea);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvHoraio = findViewById(R.id.tvHorario);



    }

    private void cargarAPI(String id) {
        System.out.println("ENTRA EN LA CARGA DE API");
        System.out.println(id);
        Retrofit ret = RetrofitClient.getClient(API.URL);
        API api = ret.create(API.class);
        Call<MuseosGeneral> call = api.getMuseoID(id);
        System.out.println("COMENZAMOS LA CALL");
        call.enqueue(new Callback<MuseosGeneral>() {
            @Override
            public void onResponse(Call<MuseosGeneral> call, @NonNull Response<MuseosGeneral> response) {
                if (response.isSuccessful()) {
                    MuseosGeneral museosGeneral = response.body();
                    //cargarText(museosGeneral.getMuseos());
                    System.out.println("HA SALIDO DE LA API");
                    System.out.println(museosGeneral.getMuseos().get(0).getTitle());
                }else {
                    System.out.println("ERROR EN LA API");
                }
            }
            @Override
            public void onFailure(Call<MuseosGeneral> call, @NonNull Throwable t) {
                System.out.println("PROBLEMA CON LA API");
                Toast.makeText(DetalleActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();

            }
        });
    }


}