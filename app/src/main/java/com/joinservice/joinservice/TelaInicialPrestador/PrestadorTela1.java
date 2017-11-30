package com.joinservice.joinservice.TelaInicialPrestador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.joinservice.joinservice.servico.DetalheServicoActivity;
import com.joinservice.joinservice.R;

import java.util.List;

import Fachada.Fachada;
import basica.Servico;


public class PrestadorTela1 extends Fragment {

    Fachada fachada;
    private ListView listaServicos;
    List<Servico> servicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestador_tela1, container, false);
        listaServicos = (ListView) view.findViewById(R.id.lvSericosAbertos);
        listaServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {

                Intent it = new Intent(getActivity(), DetalheServicoActivity.class);
                it.putExtra("SERVICO", servicos.get(pos));
                startActivity(it);
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        carregarLista();
    }

    public static PrestadorTela1 newInstance(String text) {
        PrestadorTela1 f = new PrestadorTela1();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }

    public void carregarLista() {
        fachada = Fachada.getInstance(getActivity());

        listaServicos = (ListView) getActivity().findViewById(R.id.lvSericosAbertos);
        /*List<Servico> servicos = null;
        servicos = fachada.ListarServicosUsuario(fachada.usuarioLogado());
        ListaAdapterServico adapterServico = new ListaAdapterServico(getActivity(), (ArrayList<Servico>) servicos);
        listaServicos.setAdapter(adapterServico);*/
    }
}
