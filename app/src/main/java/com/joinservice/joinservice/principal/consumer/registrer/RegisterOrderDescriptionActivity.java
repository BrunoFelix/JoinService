package com.joinservice.joinservice.principal.consumer.registrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.ListOrderConsumerActivity;

import Fachada.Fachada;
import basica.Servico;

public class RegisterOrderDescriptionActivity extends AppCompatActivity {

    Fachada fachada;
    Servico servico;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order_description);

        fachada = Fachada.getInstance(this);

        Intent intent  = getIntent();
        servico = (Servico) intent.getSerializableExtra("servico");

        edittext = (EditText) findViewById(R.id.editTextRegisterOrderDescrition);


    }

    public void proximo(View v){
        servico.setDescricao(edittext.getText().toString());
        servico.setUsuario(fachada.usuarioLogado());
        fachada.servicoInserir(servico);
        Intent itEntrar = new Intent(this, ListOrderConsumerActivity.class);
        startActivity(itEntrar);
    }
}
