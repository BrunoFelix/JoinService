package com.joinservice.joinservice.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joinservice.joinservice.R;

import basica.Usuario;

public class RegisterEmailActivity extends AppCompatActivity {

    EditText email;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);
    }

    public void proximo(View v) {
        usuario = new Usuario();
        email = (EditText) findViewById(R.id.editTextCadastroEmail);

        if (email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_RegiterEmail), Toast.LENGTH_SHORT).show();
        } else {
            usuario.setEmail(email.getText().toString());
            Intent itProximo = new Intent(this, RegisterProfileActivity.class);
            itProximo.putExtra("usuario", usuario);
            startActivity(itProximo);
        }


    }
}
