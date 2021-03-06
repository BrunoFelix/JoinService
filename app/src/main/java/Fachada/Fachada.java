package Fachada;

import android.content.Context;

import java.util.List;

import Negocio.ServicoNegocio;
import Negocio.ServicoUsuarioNegocio;
import Negocio.UsuarioNegocio;
import Util.NegocioException;
import basica.Servico;
import basica.ServicoUsuario;
import basica.Usuario;

/**
 * Created by Bruno on 25/10/2017.
 */

public class Fachada {

    private static Fachada fachada;
    UsuarioNegocio usuarioNegocio;
    ServicoNegocio servicoNegocio;
    ServicoUsuarioNegocio servicoUsuarioNegocio;

    public static Fachada getInstance(Context context) {
        if (fachada == null)
            fachada = new Fachada(context);
        return fachada;
    }

    public Fachada(Context context) {
        usuarioNegocio = new UsuarioNegocio(context);
        servicoNegocio = new ServicoNegocio(context);
        servicoUsuarioNegocio = new ServicoUsuarioNegocio(context);
    }

    //Usuário
    public Usuario usuarioLogar(String email, String senha) throws NegocioException {
        return usuarioNegocio.logar(email, senha);
    }

    public void usuarioAlterar(Usuario usuario) {
        usuarioNegocio.alterar(usuario);
    }

    public void usuarioInserir(Usuario usuario) {
        usuarioNegocio.inserir(usuario);
    }

    public Usuario usuarioLogado() {
        return usuarioNegocio.usuarioLogado();
    }

    public void usuarioExcluirLogado() {
        usuarioNegocio.usuarioExcluirLogado();
    }

    public void usuarioAtualizarUsuarioLogado(Usuario usuario){ usuarioNegocio.usuarioAtualizarUsuarioLogado(usuario);}

    public void usuarioAtualizarLocalizacaoUsuarioLogado(Usuario usuario){ usuarioNegocio.usuarioAtualizarLocalizacaoUsuarioLogado(usuario);}

    //Serviços
    public List<Servico> ListarServicosDoUsuarioLogado(Usuario usuarioLogado, String filtrarStatus) {
        return servicoNegocio.ListarServicosDoUsuarioLogado(usuarioLogado, filtrarStatus);
    }

    public List<Servico> ListarTodosOsServicos(Usuario usuarioLogado) {
        return servicoNegocio.ListarTodosOsServicos(usuarioLogado);
    }

    public void servicoInserir(Servico servico) {
        servicoNegocio.inserir(servico);
    }

    public void excluirServico(Servico servico) {
        servicoUsuarioNegocio.excluirTodosDeUmServico(servico);
        servicoNegocio.excluirServico(servico);
    }

    //Usuários profissionais vinculados a um determinado serviço
    public List<ServicoUsuario> ListarProfIntServico(Servico servico) {
        return servicoUsuarioNegocio.ListarProfIntServico(servico);
    }

    public void servicoUsuarioInserir(ServicoUsuario servicoUsuario) { servicoUsuarioNegocio.inserir(servicoUsuario); }

    public List<Servico> ListarServicosProfVinc(Usuario usuarioLogado, String filtro)  { return servicoUsuarioNegocio.ListarServicoProfVinc(usuarioLogado, filtro); }

}
