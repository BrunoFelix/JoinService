package Negocio;

import android.content.Context;

import java.util.List;

import DAO.ServicoUsuarioDAO;
import basica.Servico;
import basica.ServicoUsuario;

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

    public void inserir(ServicoUsuario servicoUsuario){
        servicoUsuarioDAO.salvar(servicoUsuario);
    }
}
