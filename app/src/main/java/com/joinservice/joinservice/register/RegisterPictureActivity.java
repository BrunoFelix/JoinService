package com.joinservice.joinservice.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.joinservice.joinservice.R;

public class RegisterPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_picture);
    }

    public void proximo(View v) {
        Intent itProximo = new Intent(this, RegisterPhoneActivity.class);
        startActivity(itProximo);
    }
}
