package com.joinservice.joinservice.TelaInicialPrestador;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joinservice.joinservice.R;

public class PrestadorTela2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cliente_tela2, container, false);
        return v;
    }

    public static PrestadorTela2 newInstance(String text) {

        PrestadorTela2 f = new PrestadorTela2();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
