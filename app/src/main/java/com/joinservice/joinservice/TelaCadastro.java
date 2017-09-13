package com.joinservice.joinservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TelaCadastro extends AppCompatActivity {

    private static final String[] ESTADOS = new String[]{
            "AC", "AL", "AP", "AM", "BA", "CE", "DF",
            "ES", "GO", "MA", "MT", "MS", "MG", "PA",
            "PB", "PR", "PE", "PI", "RJ", "RN", "RS",
            "RO", "RR", "SC", "SP", "SE", "TO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        Spinner combo = (Spinner) findViewById(R.id.estados);
        ArrayAdapter adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ESTADOS);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combo.setAdapter(adp);
    }
}



