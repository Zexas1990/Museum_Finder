package com.example.proyectob_pmdm_danielfernandez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectob_pmdm_danielfernandez.data.MuseosGeneral;
import com.example.proyectob_pmdm_danielfernandez.utilApi.API;
import com.example.proyectob_pmdm_danielfernandez.utilApi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnDatosListener {

    static final int LISTA = 0;
    static final int MAPA = 1;

    MuseosGeneral result;
    Button btnConsultar, btnFiltro;
    TextView tvFiltro;
    String distrito;
    int listaMapa;
    FragmentManager fm;
    FragmentTransaction ft;


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

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //confirmarModo();
                cargarFragmentoLista();

            }
        });
    }


    private void cargarFragmentoLista() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Fragment fragment = new ListaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("distrito", distrito);
        fragment.setArguments(bundle);
        ft.replace(R.id.frLayout, fragment);
        ft.commit();

    }

    private void confirmarModo() {
        if (listaMapa == LISTA) {
            cargarFragmentoLista();
        } else if (listaMapa == MAPA) {
            //TODO
        }
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