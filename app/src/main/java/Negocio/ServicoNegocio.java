package Negocio;

import android.content.Context;

import java.text.ParseException;
import java.util.List;

import DAO.ServicoDAO;
import basica.Servico;
import basica.Usuario;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ServicoNegocio {

    ServicoDAO servicoDAO;

    public ServicoNegocio(Context context) {
        servicoDAO = new ServicoDAO(context);
    }

    public List<Servico> ListarServicosUsuario(Usuario usuarioLogado) {
        Servico servico = new Servico();
        return servicoDAO.buscarServico(servico, usuarioLogado);
    }

    public void inserir(Servico servico){
        servicoDAO.salvar(servico);
    }

}
