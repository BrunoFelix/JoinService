package Fachada;

import android.content.Context;

import Negocio.UsuarioNegocio;
import Util.NegocioException;
import basica.Usuario;

/**
 * Created by Bruno on 25/10/2017.
 */

public class Fachada {

    private static Fachada fachada;
    UsuarioNegocio usuarioNegocio;

    public static Fachada getInstance(Context context) {
        if (fachada == null)
            fachada = new Fachada(context);

        return fachada;
    }

    public Fachada(Context context){
        usuarioNegocio = new UsuarioNegocio(context);
    }

    //Usuário
    public Usuario usuarioLogar(String email, String senha) throws NegocioException {
        return usuarioNegocio.logar(email, senha);
    }
    public  void usuarioAlterar (Usuario usuario){
       usuarioNegocio.alterar(usuario);
    }

}
