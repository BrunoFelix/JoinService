package com.joinservice.joinservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Button button =(Button)findViewById(R.id.btnEntrar);
        Button button1 =(Button)findViewById(R.id.btnCadastrar);
    }
    public  void entrar(View v){
        Intent itEntrar = new Intent(TelaLogin.this,TelaPrincipal.class);
        startActivity(itEntrar);
    }

    public  void cadastrar(View v){
        Intent itCadastro = new Intent(TelaLogin.this,TelaCadastro.class);
        startActivity(itCadastro);
    }
}
