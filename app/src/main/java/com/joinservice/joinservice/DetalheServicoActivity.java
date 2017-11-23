package com.joinservice.joinservice;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joinservice.joinservice.maps.MapsFragment;

import basica.Categoria;
import basica.Servico;
import basica.Usuario;

public class DetalheServicoActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    Servico servico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_servico);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("SERVICO") != null) {
            servico = (Servico) intent.getSerializableExtra("SERVICO");
        }

        Fragment fragmentServico = new ServicoFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putSerializable("SERVICO", servico);
        fragmentServico.setArguments(args);
        fragmentTransaction.replace(R.id.frameLayoutContainerServico, fragmentServico);
        fragmentTransaction.commit();
    }
}
