package com.joinservice.joinservice.TelaInicial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.ListOrderConsumerActivity;
import com.joinservice.joinservice.principal.consumer.registrer.RegisterOrderCategoryActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListaAdapterServico;
import Fachada.Fachada;
import basica.Servico;
import basica.Usuario;


public class PrimeiraTela extends Fragment {

    Fachada fachada;
    private ListView listaServicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_primeira_tela, container, false);

        listaServicos = (ListView) getActivity().findViewById(R.id.listViewServicos);
        Button cadastrarServico = (Button) getActivity().findViewById(R.id.buttonCadastrarServico);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarLista();
    }


    public void AddServico(View v) {
        cadastrarServico();
    }

    public static PrimeiraTela newInstance(String text) {
        PrimeiraTela f = new PrimeiraTela();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }

    private ArrayList<Servico> listarServicos() {
        ArrayList<Servico> listaServico = (ArrayList<Servico>) fachada.ListarServicosUsuario();
        return listaServico;
    }


    public void cadastrarServico() {
        Intent intentRegisterOrder = new Intent(getActivity(), RegisterOrderCategoryActivity.class);
        startActivity(intentRegisterOrder);
    }

    public void carregarLista() {
        fachada = Fachada.getInstance(getContext());

        List<Servico> servicos = fachada.ListarServicosUsuario();
        ArrayAdapter<Servico> adapter = new ArrayAdapter<Servico>(getActivity(), android.R.layout.simple_list_item_1, servicos);

        //listaServicos.setAdapter(adapter);

    }
}
