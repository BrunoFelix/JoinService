package com.joinservice.joinservice.register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.joinservice.joinservice.R;

import basica.Usuario;

public class RegisterPictureActivity extends AppCompatActivity {

    private static int TIRAR_FOTO = 1020394857;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_picture);

        Intent intent  = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
    }

    @SuppressLint("SimpleDateFormat")
    public void addFoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(getBaseContext(), "A captura foi cancelada",
                            Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getBaseContext(), "A câmera foi fechada",
                            Toast.LENGTH_SHORT);
                }
            }
        }
    }

    public void proximo(View v) {
            Intent itProximo = new Intent(this, RegisterPhoneActivity.class);
            itProximo.putExtra("usuario", usuario);
            startActivity(itProximo);
    }


}
