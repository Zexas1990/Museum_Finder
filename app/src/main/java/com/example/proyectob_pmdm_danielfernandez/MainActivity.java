package com.example.proyectob_pmdm_danielfernandez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnDatosListener {

    Button btnConsultar;
    Button btnFiltro;
    TextView tvFiltro;
    String distrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btnConsultar = findViewById(R.id.btnConsultar);
        btnFiltro = findViewById(R.id.btnFiltro);
        tvFiltro = findViewById(R.id.tvFiltro);


        btnFiltro.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltroDialog dialog = new FiltroDialog();
                dialog.show(getSupportFragmentManager(), "FiltroDialog");

            }
        }));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnListado) {
            //TODO
        } else if (item.getItemId() == R.id.mnMapa) {
            //TODO
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAeptarDatosListenerDistrito(String distrito) {
        this.distrito = distrito;
        tvFiltro.setText("Distrito: " + distrito);
    }
}