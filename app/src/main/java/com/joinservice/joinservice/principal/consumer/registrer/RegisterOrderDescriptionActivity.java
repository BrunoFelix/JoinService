package com.joinservice.joinservice.principal.consumer.registrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.StartCliente;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.UsuarioDAO;
import Fachada.Fachada;
import Negocio.UsuarioNegocio;
import basica.Servico;
import basica.Usuario;

public class RegisterOrderDescriptionActivity extends AppCompatActivity {

    Fachada fachada;
    Servico servico;
    EditText edittext;
    Usuario usuarioServ = fachada.usuarioLogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order_description);

        fachada = Fachada.getInstance(this);

        Intent intent  = getIntent();
        servico = (Servico) intent.getSerializableExtra("servico");

        edittext = (EditText) findViewById(R.id.editTextRegisterOrderDescrition);
    }

    public void proximo(View v) throws ParseException {
        servico.setDescricao(edittext.getText().toString());
        servico.setUsuario(fachada.usuarioLogado());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        servico.setDataInsercao(new java.sql.Date(dateFormat.parse(dateFormat.format(date)).getTime()));
        servico.setStatus("ABERTO");
        fachada.servicoInserir(servico);
        servico.setUsuario(usuarioServ);
        Intent itEntrar = new Intent(this, StartCliente.class);
        startActivity(itEntrar);
        fileList();
    }
}
