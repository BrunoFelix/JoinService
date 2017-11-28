package com.joinservice.joinservice.TelaInicialPrestador;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.joinservice.joinservice.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListaAdapterServico;
import Fachada.Fachada;
import basica.Servico;


public class PrestadorTela3 extends Fragment {
    Fachada fachada;
    private ListView listaServicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestador_tela3, container, false);
        listaServicos = (ListView) view.findViewById(R.id.lvTodosServicos);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarLista();
    }

    public static PrestadorTela3 newInstance(String text) {

        PrestadorTela3 f = new PrestadorTela3();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    public void carregarLista() {
        fachada = Fachada.getInstance(getActivity());

        listaServicos = (ListView) getActivity().findViewById(R.id.lvTodosServicos);
        List<Servico> servicos = null;
        servicos=fachada.ListarServicosUsuario(fachada.usuarioLogado());

        ListaAdapterServico adapterServico = new ListaAdapterServico(getActivity(), (ArrayList<Servico>) servicos);
        listaServicos.setAdapter(adapterServico);

    }

}
