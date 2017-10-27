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

public class RegisterPictureActivity extends AppCompatActivity {

    private static int TIRAR_FOTO = 1020394857;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_picture);
    }

    @SuppressLint("SimpleDateFormat")
    public void addFoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TIRAR_FOTO);
    }

    /*Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(i, 123);*/


        /*Intent itProximo = new Intent(this, RegisterPhoneActivity.class);
        startActivity(itProximo);*/


    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                //usuário tirou a foto
            } else {
                Toast.makeText(this, "Captura Cancelada", Toast.LENGTH_SHORT).show();
                //usuário não tirou a foto
            }
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TIRAR_FOTO) {
            if (resultCode == RESULT_OK) {
                if(data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getBaseContext(), "A captura foi cancelada",
                            Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getBaseContext(), "A câmera foi fechada",
                            Toast.LENGTH_SHORT);
                }
            }
        }
    }


}
