package Negocio;

import android.content.Context;

import java.util.List;

import DAO.ServicoDAO;
import basica.Servico;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ServicoNegocio {

    ServicoDAO servicoDAO;

    public ServicoNegocio(Context context) {
        servicoDAO = new ServicoDAO(context);
    }

    public List<Servico> ListarServicosUsuario(){
        Servico servico = new Servico();
        return servicoDAO.buscarServico(servico);
    }

    public void inserir(Servico servico){
        servicoDAO.salvar(servico);
    }

}
