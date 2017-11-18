package com.joinservice.joinservice.TelaInicial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joinservice.joinservice.R;

public class SegundaTela extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_segunda_tela, container, false);
        return v;
    }

    public static SegundaTela newInstance(String text) {

        SegundaTela f = new SegundaTela();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}