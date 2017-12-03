package com.joinservice.joinservice.TelaInicialCliente;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.joinservice.joinservice.maps.MapsFragment;
import com.joinservice.joinservice.servico.DetalheServicoActivity;
import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.registrer.RegisterOrderCategoryActivity;
import com.joinservice.joinservice.servico.ServicoFragment;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListaAdapterServico;
import Fachada.Fachada;
import basica.Servico;


public class ClienteTela1 extends Fragment {

    Fachada fachada;
    private ListView listaServicos;
    List<Servico> servicos;

    private FragmentManager fragmentManager;

    private FloatingActionButton cadastrarServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_tela1, container, false);
        listaServicos = (ListView) view.findViewById(R.id.lvSolicitacoesServicos);
        cadastrarServico = (FloatingActionButton) view.findViewById(R.id.btCadastrarServico);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.relativeLayoutContainerteste);

        if (fragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }

        fm = getActivity().getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.frameLayoutContainerMapServico);

        if (fragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }

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
        servicos = fachada.ListarServicosDoUsuarioLogado(fachada.usuarioLogado());
        ListaAdapterServico adapterServico = new ListaAdapterServico(getActivity(), (ArrayList<Servico>) servicos);
        listaServicos.setAdapter(adapterServico);

        listaServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
                int orientacao = getActivity().getResources().getConfiguration().orientation;

                if (orientacao == Configuration.ORIENTATION_PORTRAIT) {
                    Intent it = new Intent(getActivity(), DetalheServicoActivity.class);
                    it.putExtra("SERVICO", servicos.get(pos));

                    startActivity(it);
                }else if (orientacao == Configuration.ORIENTATION_LANDSCAPE) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragmentDestroy = fm.findFragmentById(R.id.relativeLayoutContainerteste);

                    if (fragmentDestroy != null) {
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.remove(fragmentDestroy);
                        ft.commit();
                    }

                    fragmentManager = getActivity().getSupportFragmentManager();

                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    Fragment fragment = new ServicoFragment();
                    Bundle args = new Bundle();
                    args.putSerializable("SERVICO", servicos.get(pos));
                    args.putSerializable("EXIBIRMAPA", false);
                    fragment.setArguments(args);
                    transaction.add(R.id.relativeLayoutContainerteste, fragment, "ServicoFragment");
                    transaction.addToBackStack(null);

                    fragmentManager = getActivity().getSupportFragmentManager();

                    //FragmentTransaction transaction = fragmentManager.beginTransaction();

                    fragment = new MapsFragment();
                    args = new Bundle();
                    args.putDouble("LONGITUDE", Double.parseDouble(servicos.get(pos).getLongitude()));
                    args.putDouble("LATITUDE", Double.parseDouble(servicos.get(pos).getLatitude()));
                    fragment.setArguments(args);

                    transaction.add(R.id.frameLayoutContainerMapServico, fragment, "MapsFragment" );

                    transaction.commitAllowingStateLoss();
                }
            }
        });

    }

    public void exibirDetalhesServico(){

    }

}
