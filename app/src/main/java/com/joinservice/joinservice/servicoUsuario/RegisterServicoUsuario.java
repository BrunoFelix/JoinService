package com.joinservice.joinservice.servicoUsuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.joinservice.joinservice.servico.DetalheServicoActivity;
import com.joinservice.joinservice.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import basica.Servico;
import basica.ServicoUsuario;
import basica.Usuario;
import Fachada.Fachada;

public class RegisterServicoUsuario extends AppCompatActivity {

    Servico servico;
    Usuario usuario;
    Fachada fachada;

    EditText valor, descricao;
    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_servico_usuario);

        fachada = Fachada.getInstance(this);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("servico") != null) {
            servico = (Servico) intent.getSerializableExtra("servico");
            usuario = fachada.usuarioLogado();
        }

        valor = (EditText) findViewById(R.id.editTextRegisterServicoUsuarioValor);
        descricao = (EditText) findViewById(R.id.editTextRegisterServicoUsuarioDescricao);

        btnConfirmar = (Button) findViewById(R.id.buttonProximoRegisterServicoUsuario);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    confirmar();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void confirmar() throws ParseException {
        ServicoUsuario servicoUsuario = new ServicoUsuario();
        servicoUsuario.setUsuario(usuario);
        servicoUsuario.setServico(servico);
        servicoUsuario.setValorOfertado(Double.parseDouble(valor.getText().toString()));
        servicoUsuario.setDescricao(descricao.getText().toString());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        servicoUsuario.setDataOferta(new java.sql.Date(dateFormat.parse(dateFormat.format(date)).getTime()));
        fachada.servicoUsuarioInserir(servicoUsuario);

        Intent it = new Intent(this, DetalheServicoActivity.class);
        it.putExtra("SERVICO", servico);
        startActivity(it);
        finish();
    }
}
