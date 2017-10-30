package Util;

import android.widget.EditText;

import com.joinservice.joinservice.EditProfile;
import com.joinservice.joinservice.R;

import basica.Usuario;

/**
 * Created by Maikon Silva on 29/10/2017.
 */

public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoEmail;
    private final EditText campoSenha;
    private final EditText campoConfirmarSenha;
    private final EditText campoTelefone;
    private Usuario usuario;

   /*
    *
    */

    public FormularioHelper(EditProfile activity){
        campoNome = (EditText) activity.findViewById(R.id.editTextEditarNomeCompleto);
        campoEmail = (EditText) activity.findViewById(R.id.editTextEditarEmail);
        campoSenha = (EditText) activity.findViewById(R.id.editTextEditarSenha);
        campoConfirmarSenha = (EditText) activity.findViewById(R.id.editTextEditarSenhaConfirmacao);
        campoTelefone = (EditText)  activity.findViewById(R.id.editTextEditarPhone);
        this.usuario = new Usuario();
    }
    public Usuario Pegausuario(){
        usuario.setNome(campoNome.getText().toString());
        usuario.setEmail(campoEmail.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        //usuario.setSenha(campoConfirmarSenha.getText().toString());
        usuario.setCelular(campoTelefone.getText().toString());
        return usuario;
    }
    public void preencheFormulario(Usuario usuario) {
        campoNome.setText(usuario.getNome());
        campoEmail.setText(usuario.getEmail());
        campoSenha.setText(usuario.getSenha());
        campoConfirmarSenha.setText(usuario.getSenha());
        campoTelefone.setText(usuario.getCelular());

        this.usuario = usuario;
    }
}
