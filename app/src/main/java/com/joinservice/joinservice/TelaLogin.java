package com.joinservice.joinservice;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joinservice.joinservice.principal.consumer.ListOrderConsumerActivity;
import com.joinservice.joinservice.register.RegisterEmailActivity;

import java.io.Serializable;

import Fachada.Fachada;
import Util.NegocioException;
import basica.Usuario;

public class TelaLogin extends AppCompatActivity {

    EditText email, senha;
    Fachada fachada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Button button = (Button) findViewById(R.id.btnEntrar_login);
        Button button1 = (Button) findViewById(R.id.btnCadastrar);
        fachada = Fachada.getInstance(this);
    }

    public void entrar(View v) {

        email = (EditText) findViewById(R.id.editText_email_login);
        senha = (EditText) findViewById(R.id.editText_senha_login);


        Usuario usuario;
        try {
            usuario = fachada.usuarioLogar(email.getText().toString(), senha.getText().toString());

            if (usuario.getId() > 0) {
                Toast.makeText(getApplicationContext(), "Redirecionando, aguarde...!", Toast.LENGTH_LONG).show();
                Intent itEntrar = new Intent(TelaLogin.this, ListOrderConsumerActivity.class);
                itEntrar.putExtra("usuario", usuario);
                startActivity(itEntrar);
            } else {
                Toast.makeText(getApplicationContext(), "Dados inválidos, tente novamente!", Toast.LENGTH_LONG).show();
            }
        } catch (NegocioException e) {
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrar(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Deseja se cadastrar como: ");

        alert.setPositiveButton("Usuário", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Usuario usuario = new Usuario();
                usuario.setTipo("USUARIO");
                Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
                startActivity(itCadastro);
            }
        })
                .setNeutralButton("Prestador", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Usuario usuario = new Usuario();
                        usuario.setTipo("PRESTADOR");
                        Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
                        startActivity(itCadastro);
                    }
                });

        alert.show();
    }
}
