package com.joinservice.joinservice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joinservice.joinservice.TelaInicialCliente.ClienteTela1;

/**
 * Created by tj on 06/11/17.
 */

public class MyServicesFragment extends Fragment {

    private String title;
    private int page;

    public static Fragment getInstance(int position) {
        ClienteTela1 f = new ClienteTela1();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    //armazena variáveis de instância com base nos argumentos passados
    public static MyServicesFragment newInstance(int page, String title) {
        MyServicesFragment fragment = new MyServicesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 1);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contaier, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_tela1, contaier, false);
        return view;
    }

}
