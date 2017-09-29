package com.joinservice.joinservice.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.joinservice.joinservice.R;

public class RegisterEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);
    }

    public void proximo(View v) {
        Intent itProximo = new Intent(this, RegisterProfileActivity.class);
        startActivity(itProximo);
    }
}
