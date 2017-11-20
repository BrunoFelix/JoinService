package com.joinservice.joinservice.TelaInicialCliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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


public class ClienteTela1 extends Fragment {

    Fachada fachada;
    private ListView listaServicos;
    private FloatingActionButton cadastrarServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_tela1, container, false);
        listaServicos = (ListView) view.findViewById(R.id.lvSolicitacoesServicos);
        cadastrarServico = (FloatingActionButton) view.findViewById(R.id.btCadastrarServico);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedBundle) {
        cadastrarServico = (FloatingActionButton) view.findViewById(R.id.btCadastrarServico);
        cadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), RegisterOrderCategoryActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarLista();
    }

    public static ClienteTela1 newInstance(String text) {
        ClienteTela1 f = new ClienteTela1();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }

    public void carregarLista() {
        fachada = Fachada.getInstance(getActivity());

        listaServicos = (ListView) getActivity().findViewById(R.id.lvSolicitacoesServicos);
        List<Servico> servicos = fachada.ListarServicosUsuario(fachada.usuarioLogado());
        ListaAdapterServico adapterServico = new ListaAdapterServico(getActivity(), (ArrayList<Servico>) servicos);
        listaServicos.setAdapter(adapterServico);

    }
}
