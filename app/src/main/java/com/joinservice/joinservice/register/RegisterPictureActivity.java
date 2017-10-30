package com.joinservice.joinservice.register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.joinservice.joinservice.R;

import basica.Usuario;

public class RegisterPictureActivity extends AppCompatActivity {

    private static int TIRAR_FOTO = 123;
    Usuario usuario;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_picture);
        this.imageView = (ImageView) this.findViewById(R.id.imageViewCadastroFoto);
        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");
        Button fotoB = (Button) this.findViewById(R.id.FotoButton);
        fotoB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TIRAR_FOTO);
            }
        });
    }

    /*public void addFoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TIRAR_FOTO);
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TIRAR_FOTO) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmap);
                    Button fotoB = (Button) findViewById(R.id.FotoButton);
                    fotoB.setText("Alterar Foto do Perfil");


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
