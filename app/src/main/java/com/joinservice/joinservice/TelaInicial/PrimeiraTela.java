package com.joinservice.joinservice.TelaInicial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joinservice.joinservice.R;

public class PrimeiraTela extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_primeira_tela, container, false);

        TextView tv = (TextView) v.findViewById(R.id.tvPrimFrag);
        tv.setText("Meus Serviços");


        return v;
    }

    public static PrimeiraTela newInstance(String text) {

        PrimeiraTela f = new PrimeiraTela();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
