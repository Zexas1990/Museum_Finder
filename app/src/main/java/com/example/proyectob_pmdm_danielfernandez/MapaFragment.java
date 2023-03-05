package com.example.proyectob_pmdm_danielfernandez;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectob_pmdm_danielfernandez.data.Museo;
import com.example.proyectob_pmdm_danielfernandez.data.MuseosGeneral;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapaFragment extends Fragment {

    private GoogleMap gMap;
    private List<Museo> museos;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapaFragment.this.gMap = googleMap;

            LatLng alcobendas = new LatLng(40.5474600, -3.6419700);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alcobendas, 10));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


            if (museos != null) {
                for (Museo museos : museos) {

                    System.out.println(museos.getTitle());
                    LatLng location = new LatLng(museos.getLocation().getLatitude(), museos.getLocation().getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(location).title(museos.getTitle()));
                }
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}