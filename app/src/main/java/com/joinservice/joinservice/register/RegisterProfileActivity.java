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
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        Intent intent  = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
    }

    public void proximo(View v) {

        nome = (EditText) findViewById(R.id.editTextCadastroNomeCompleto);
        senha = (EditText) findViewById(R.id.editTextCadastroSenha);
        confirmarSenha = (EditText) findViewById(R.id.editTextCadastroSenhaConfirmacao);

        if ((nome.getText().toString().isEmpty()) || (senha.getText().toString().isEmpty()) || (confirmarSenha.getText().toString().isEmpty())) {
            Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_RegisterProfile_01), Toast.LENGTH_SHORT).show();
        } else if ((!senha.getText().toString().equals(confirmarSenha.getText().toString()))) {
            Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_RegisterProfile_02), Toast.LENGTH_LONG).show();
        } else {
            usuario.setNome(nome.getText().toString());
            usuario.setSenha(senha.getText().toString());

            Intent itProximo = new Intent(this, RegisterPictureActivity.class);
            itProximo.putExtra("usuario", usuario);
            startActivity(itProximo);
        }

    }
}
