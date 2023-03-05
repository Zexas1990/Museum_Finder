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
import android.widget.FrameLayout;
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
    int listaMapa = 0;
    FrameLayout frLayout;
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
        frLayout = findViewById(R.id.frLayout);


        btnFiltro.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaMapa == LISTA){
                    distrito = "";
                    FiltroDialog dialog = new FiltroDialog();
                    dialog.show(getSupportFragmentManager(), "FiltroDialog");
                    dialog.setCancelable(false);
                    tvFiltro.setText("Distrito selecionado");
                    frLayout.removeAllViews();
                }else{

                }
            }
        }));

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarModo();
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

    private void cargarFragmentoMapa() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Fragment fragment = new MapaFragment();
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
            cargarFragmentoMapa();
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
            listaMapa = LISTA;
            Toast.makeText(this, R.string.cambioLista, Toast.LENGTH_SHORT).show();
            tvFiltro.setText("Distrito seleccionado");
            distrito = "";
            btnConsultar.setText("Consultar Listado");
            frLayout.removeAllViews();

        } else if (item.getItemId() == R.id.mnMapa) {
            listaMapa = MAPA;
            Toast.makeText(this, R.string.cambioMapa, Toast.LENGTH_SHORT).show();
            tvFiltro.setText("");
            btnConsultar.setText("Consultar Mapa");
            frLayout.removeAllViews();
            distrito = "";


        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAeptarDatosListenerDistrito(String distrito) {
        this.distrito = distrito;
        tvFiltro.setText("Distrito: " + distrito);
    }


}