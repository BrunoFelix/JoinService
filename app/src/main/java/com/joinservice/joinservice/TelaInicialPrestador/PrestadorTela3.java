package com.joinservice.joinservice.TelaInicialPrestador;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joinservice.joinservice.R;


public class PrestadorTela3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_prestador_tela3, container, false);
        return v;
    }

    public static PrestadorTela3 newInstance(String text) {

        PrestadorTela3 f = new PrestadorTela3();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
