package com.joinservice.joinservice.register;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.EditText;

import com.joinservice.joinservice.R;
import com.joinservice.joinservice.principal.consumer.ListOrderConsumerActivity;

public class RegisterPhoneActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        EditText inputField = (EditText) findViewById(R.id.editTextCadastroPhone);
        inputField.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
    }

    public void proximo(View v) {
        Intent itProximo = new Intent(this, ListOrderConsumerActivity.class);
        startActivity(itProximo);
    }
}
