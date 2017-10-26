package com.joinservice.joinservice.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joinservice.joinservice.R;

import basica.Usuario;

public class RegisterProfileActivity extends AppCompatActivity {

    EditText nome, senha, confirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);
    }

    public void proximo(View v) {

        Usuario usuario = new Usuario();
        nome = (EditText) findViewById(R.id.editTextCadastroNomeCompleto);
        senha = (EditText) findViewById(R.id.editTextCadastroSenha);
        confirmarSenha = (EditText) findViewById(R.id.editTextCadastroSenhaConfirmacao);

        if ((nome.getText().toString().isEmpty()) && (!senha.getText().toString().isEmpty()) && (!confirmarSenha.getText().toString().isEmpty())) {
            Toast.makeText(getApplicationContext(), "Todos os campos precisam ser preenchidos!", Toast.LENGTH_SHORT).show();
        }
        else if(senha.getText().toString() != confirmarSenha.getText().toString()){
            Toast.makeText(getApplicationContext(), "O campo \"Senha\" e \"Confirmar Senha\" precisam ser igualmente preenchidos!", Toast.LENGTH_SHORT).show();
        }else{
            usuario.setNome(nome.getText().toString());
            usuario.setSenha(senha.getText().toString());

            Intent itProximo = new Intent(this, RegisterPictureActivity.class);
            startActivity(itProximo);
        }
    }
}
