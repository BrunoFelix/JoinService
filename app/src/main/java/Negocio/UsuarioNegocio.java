package Negocio;

import android.content.Context;

import DAO.UsuarioDAO;
import Util.NegocioException;
import basica.Usuario;

/**
 * Created by Bruno on 25/10/2017.
 */

public class UsuarioNegocio {
    UsuarioDAO usuarioDAO;

    public UsuarioNegocio(Context context){
        usuarioDAO = new UsuarioDAO(context);
    }

    public Usuario logar(String email, String senha) throws NegocioException {
        validarLogin(email, senha);
        return usuarioDAO.logar(email, senha);
    }

    //############################## VALIDAÇÕES ############################## //

    public void validarLogin(String email, String senha) throws NegocioException {
        if (((email == null) || (email.isEmpty())) && ((senha == null) || (senha.isEmpty()))) {
            throw new NegocioException("Os campos \"Email\" e \"Senha\" precisam ser preenchidos!");
        }
        if ((email == null) || (email.isEmpty()))
         {
            throw new NegocioException("O campo \"Email\" precisa ser preenchido!");
        }
        if ((senha == null) || (senha.isEmpty()))
        {
            throw new NegocioException("O campo \"Senha\" precisa ser preenchido!");
        }
    }
}