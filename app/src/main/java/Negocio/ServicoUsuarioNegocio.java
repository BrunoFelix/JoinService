package Negocio;

import android.content.Context;

import java.util.List;

import DAO.ServicoUsuarioDAO;
import basica.Servico;
import basica.ServicoUsuario;
import basica.Usuario;

/**
 * Created by bruno.barbosa on 30/11/2017.
 */

public class ServicoUsuarioNegocio {

    ServicoUsuarioDAO servicoUsuarioDAO;

    public ServicoUsuarioNegocio(Context context) {
        servicoUsuarioDAO = new ServicoUsuarioDAO(context);
    }

    public List<ServicoUsuario> ListarProfIntServico(Servico servico) {
        return servicoUsuarioDAO.buscarProfInt(servico);
    }

    public List<Servico> ListarServicoProfVinc(Usuario usuarioLogado, String filtro) {
        return servicoUsuarioDAO.buscarServicosProfVinc(usuarioLogado, filtro);
    }

    public void inserir(ServicoUsuario servicoUsuario){
        servicoUsuarioDAO.salvar(servicoUsuario);
    }

    public void excluir(ServicoUsuario servicoUsuario) {
        servicoUsuarioDAO.excluir(servicoUsuario);
    }

    public void excluirTodosDeUmServico(Servico servico){
        servicoUsuarioDAO.excluirTodosDeUmServico(servico);
    }
}
