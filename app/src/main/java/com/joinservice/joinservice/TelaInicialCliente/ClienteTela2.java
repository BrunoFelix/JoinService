package com.joinservice.joinservice.TelaInicialCliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.registrer.RegisterOrderCategoryActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListaAdapterServico;
import Fachada.Fachada;
import basica.Servico;

public class ClienteTela2 extends Fragment {

    Fachada fachada;
    private ListView listaServicos;
    List<Servico> servicos;

    private FragmentManager fragmentManager;

    private FloatingActionButton cadastrarServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_tela2, container, false);
        listaServicos = (ListView) view.findViewById(R.id.lvSolicitacoesServicos2);

        return view;
    }

    public static ClienteTela2 newInstance(String text) {

        ClienteTela2 f = new ClienteTela2();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //
    }

    public void carregarLista() {
        fachada = Fachada.getInstance(getActivity());

        listaServicos = (ListView) getActivity().findViewById(R.id.lvSolicitacoesServicos2);
        servicos = fachada.ListarServicosDoUsuarioLogado(fachada.usuarioLogado(), "FINALIZADO");
        ListaAdapterServico adapterServico = new ListaAdapterServico(getActivity(), (ArrayList<Servico>) servicos);
        listaServicos.setAdapter(adapterServico);
    }

}
