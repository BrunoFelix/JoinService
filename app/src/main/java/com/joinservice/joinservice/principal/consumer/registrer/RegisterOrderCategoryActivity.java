package com.joinservice.joinservice.principal.consumer.registrer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.joinservice.joinservice.R;

import basica.Servico;

public class RegisterOrderCategoryActivity extends Activity {

    Servico servico;
    Button btnCatCel, btnCatEletro, btnCatMovDec, btnCatAdmin, btnCatReformas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order_category);

        btnCatCel = (Button) findViewById(R.id.buttomCelETelRegisterOrderCategory);
        btnCatEletro = (Button) findViewById(R.id.buttomEletroRegisterOrderCategory);
        btnCatMovDec = (Button) findViewById(R.id.buttomMovEDecRegisterOrderCategory);
        btnCatAdmin = (Button) findViewById(R.id.buttomAdminRegisterOrderCategory);
        btnCatReformas = (Button) findViewById(R.id.buttomReformasRegisterOrderCategory);

        btnCatCel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proximo(1);
            }
        });

        btnCatEletro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proximo(2);
            }
        });

        btnCatMovDec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proximo(3);
            }
        });

        btnCatAdmin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proximo(4);
            }
        });

        btnCatReformas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proximo(5);
            }
        });
    }


    public void proximo(int categoria){
        servico = new Servico();
        servico.getCategoria().setId(categoria);
        Intent itProximo = new Intent(this, RegisterOrderTimeActivity.class);
        itProximo.putExtra("servico", servico);
        startActivity(itProximo);
    }
}
