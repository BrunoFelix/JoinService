package com.joinservice.joinservice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.joinservice.joinservice.principal.consumer.StartCliente;

import Fachada.Fachada;
import Util.FormularioHelper;
import basica.Usuario;

public class EditProfile extends AppCompatActivity {
    private FormularioHelper helper;

    private static int TIRAR_FOTO = 123;
    Usuario usuario;
    private ImageView imageView;
    Fachada fachada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.imageView = (ImageView) this.findViewById(R.id.imageViewFotoPerfil);
        fachada = Fachada.getInstance(this);
        Intent intent  = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
        helper = new FormularioHelper(this);
        if (usuario != null){
            helper.preencheFormulario(usuario);
        }

        Button fotoB = (Button) this.findViewById(R.id.AlterarFoto);
        fotoB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TIRAR_FOTO);
            }
        });
    }


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

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(getBaseContext(), getString(R.string.texto_toast_edit_profile_01) ,
                            Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.texto_toast_edit_profile_02),
                            Toast.LENGTH_SHORT);
                }
            }
        }
    }


    public void salvar(View view){
        Usuario usuario = helper.Pegausuario();
        fachada.usuarioAlterar(usuario);

        Intent itEntrar = new Intent(this, StartCliente.class);
        itEntrar.putExtra("usuario", usuario);
        startActivity(itEntrar);
        finish();

    }
}

