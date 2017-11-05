package Fachada;

import android.content.Context;

import java.util.List;

import Negocio.ServicoNegocio;
import Negocio.UsuarioNegocio;
import Util.NegocioException;
import basica.Servico;
import basica.Usuario;

/**
 * Created by Bruno on 25/10/2017.
 */

public class Fachada {

    private static Fachada fachada;
    UsuarioNegocio usuarioNegocio;
    ServicoNegocio servicoNegocio;

    public static Fachada getInstance(Context context) {
        if (fachada == null)
            fachada = new Fachada(context);

        return fachada;
    }

    public Fachada(Context context){
        usuarioNegocio = new UsuarioNegocio(context);
        servicoNegocio = new ServicoNegocio(context);
    }

    //Usuário
    public Usuario usuarioLogar(String email, String senha) throws NegocioException {
        return usuarioNegocio.logar(email, senha);
    }
    public void usuarioAlterar (Usuario usuario){
       usuarioNegocio.alterar(usuario);
    }

    public void usuarioInserir(Usuario usuario){
        usuarioNegocio.inserir(usuario);
    }


    //Serviços
    public List<Servico> ListarServicosUsuario(){
        return servicoNegocio.ListarServicosUsuario();
    }

}
