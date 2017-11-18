package com.joinservice.joinservice.register;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.StartCliente;

import basica.Usuario;
import Fachada.Fachada;

public class RegisterPhoneActivity extends AppCompatActivity {

    EditText celular;
    Usuario usuario;
    Fachada fachada;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        EditText inputField = (EditText) findViewById(R.id.editTextCadastroPhone);
        inputField.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));

        Intent intent  = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        fachada = Fachada.getInstance(this);
    }

    public void proximo(View v) {
        celular = (EditText) findViewById(R.id.editTextCadastroPhone);

        if (celular.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "O campo \"Celular\" precisa ser preenchido!", Toast.LENGTH_SHORT).show();
        }else{
            usuario.setCelular(celular.getText().toString());
            fachada.usuarioInserir(usuario);
            Intent itProximo = new Intent(this, StartCliente.class);
            itProximo.putExtra("usuario", usuario);
            startActivity(itProximo);
        }


    }
}
