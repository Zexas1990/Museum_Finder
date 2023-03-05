package com.example.proyectob_pmdm_danielfernandez;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FiltroDialog extends DialogFragment{

    OnDatosListener listener;

    Spinner spnDis;

    MainActivity mainActivity;



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.filtro, null);

        spnDis = v.findViewById(R.id.spnDistrito);




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle("Eliga un Distrito").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onAeptarDatosListenerDistrito(spnDis.getSelectedItem().toString());

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);


        AlertDialog dialog = builder.create();
        return dialog;
    }


    public void onAttach(@NonNull android.app.Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnDatosListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDatosListener");
        }
    }


    @Override
    public void onDetach() {
        if(listener != null){
            listener = null;
        }
        super.onDetach();
    }

}


