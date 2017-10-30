package com.joinservice.joinservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joinservice.joinservice.principal.consumer.ListOrderConsumerActivity;

import Fachada.Fachada;
import Util.FormularioHelper;
import basica.Usuario;

public class EditProfile extends AppCompatActivity {
    private FormularioHelper helper;
    Fachada fachada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fachada = Fachada.getInstance(this);
        Intent intent  = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
        helper = new FormularioHelper(this);
        if (usuario != null){
            helper.preencheFormulario(usuario);
        }
    }

    public void salvar(View view){
        Usuario usuario = helper.Pegausuario();
        fachada.usuarioAlterar(usuario);

        Intent itEntrar = new Intent(this, ListOrderConsumerActivity.class);
        itEntrar.putExtra("usuario", usuario);
        startActivity(itEntrar);

    }
}

