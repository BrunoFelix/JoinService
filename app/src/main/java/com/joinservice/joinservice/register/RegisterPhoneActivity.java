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
import com.joinservice.joinservice.principal.consumer.ListOrderConsumerActivity;

import basica.Usuario;

public class RegisterPhoneActivity extends AppCompatActivity {

    EditText celular;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        EditText inputField = (EditText) findViewById(R.id.editTextCadastroPhone);
        inputField.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
    }

    public void proximo(View v) {

        Usuario usuario = new Usuario();
        celular = (EditText) findViewById(R.id.editTextCadastroPhone);

        if (!celular.getText().toString().isEmpty()){
            usuario.setCelular(celular.getText().toString());
        }else{
            Toast.makeText(getApplicationContext(), "O campo \"Celular\" precisa ser preenchido!", Toast.LENGTH_SHORT).show();
        }

        Intent itProximo = new Intent(this, ListOrderConsumerActivity.class);
        startActivity(itProximo);
    }
}
