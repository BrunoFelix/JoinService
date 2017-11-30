package com.joinservice.joinservice;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joinservice.joinservice.principal.consumer.StartCliente;
import com.joinservice.joinservice.principal.consumer.StartPrestador;
import com.joinservice.joinservice.register.RegisterEmailActivity;

import Fachada.Fachada;
import Util.NegocioException;
import basica.Usuario;

public class TelaLogin extends AppCompatActivity {

    EditText email, senha;
    Fachada fachada;
    private SQLiteOpenHelper mDBHelper;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Button button = (Button) findViewById(R.id.btnEntrar_login);
        Button button1 = (Button) findViewById(R.id.btnCadastrar);
        fachada = Fachada.getInstance(this);

        usuario = fachada.usuarioLogado();

        if (usuario.getId() > 0) {
            verificarTipoUsuario(usuario.getTipo());
        }
    }

    public void entrar(View v) {

        email = (EditText) findViewById(R.id.editText_email_login);
        senha = (EditText) findViewById(R.id.editText_senha_login);

        try {
            usuario = fachada.usuarioLogar(email.getText().toString(), senha.getText().toString());

            if (usuario.getId() > 0) {
                verificarTipoUsuario(usuario.getTipo());
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_01), Toast.LENGTH_LONG).show();
            }
        } catch (NegocioException e) {
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void verificarTipoUsuario(String tipo){

        Toast.makeText(getApplicationContext(), getString(R.string.texto_toast_02), Toast.LENGTH_LONG).show();
        Intent itEntrar;

        if (tipo.equals("Prestador")) {
            itEntrar = new Intent(TelaLogin.this, StartPrestador.class);
        }else{
            itEntrar = new Intent(TelaLogin.this, StartCliente.class);
        }

        itEntrar.putExtra("usuario", usuario);
        startActivity(itEntrar);
        finish();
    }

    public void cadastrar(View v) {
        Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
        startActivity(itCadastro);
        /*
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Deseja se cadastrar como: ");

        alert.setPositiveButton("Usuário", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Usuario usuario = new Usuario();
                usuario.setTipo("USUARIO");
                Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
                startActivity(itCadastro);
            }
        })
                .setNeutralButton("Prestador", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Usuario usuario = new Usuario();
                        usuario.setTipo("PRESTADOR");
                        Intent itCadastro = new Intent(TelaLogin.this, RegisterEmailActivity.class);
                        startActivity(itCadastro);
                    }
                });

        alert.show();*/

    }
}
