package com.idw.project.moviecataloguesub3.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.idw.project.moviecataloguesub3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengaturanFragment extends Fragment {
    LinearLayout ll_ubah_bahasa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaturan, container, false);

        ll_ubah_bahasa = view.findViewById(R.id.ll_ubah_bahasa);

        ll_ubah_bahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UbahBahasa();
            }
        });

        return view;
    }

    private void UbahBahasa() {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);
    }

}
