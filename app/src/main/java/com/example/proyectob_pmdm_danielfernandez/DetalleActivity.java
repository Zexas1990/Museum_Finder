package com.example.proyectob_pmdm_danielfernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    TextView tvNombre, tvDistrito, tvArea, tvDescripcion, tvHoraio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvNombre = findViewById(R.id.tvNombre);
        tvDistrito = findViewById(R.id.tvDistrito);
        tvArea = findViewById(R.id.tvArea);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvHoraio = findViewById(R.id.tvHorario);


    }
}