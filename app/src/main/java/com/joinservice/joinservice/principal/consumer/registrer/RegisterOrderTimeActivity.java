package com.joinservice.joinservice.principal.consumer.registrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.joinservice.joinservice.R;

import basica.Servico;

public class RegisterOrderTimeActivity extends AppCompatActivity {

    NumberPicker np;
    Servico servico;
    Button proximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order_time);

        Intent intent  = getIntent();
        servico = (Servico) intent.getSerializableExtra("servico");

        np = (NumberPicker) findViewById(R.id.numberPickerPrazoCadastroServico);

        proximo = (Button) findViewById(R.id.buttonProximoPrazoCadastroServico);

        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(15);

        np.setWrapSelectorWheel(true);
    }

    public void proximo(View v){
        servico.setPrazo(np.getValue());
        Intent itProximo = new Intent(RegisterOrderTimeActivity.this, RegisterOrderLocationActivity.class);
        itProximo.putExtra("servico", servico);
        startActivity(itProximo);
    }
}
