package basica;

import java.util.Date;

/**
 * Created by bruno.barbosa on 30/11/2017.
 */

public class ServicoUsuario {
    private Usuario usuario;
    private Servico servico;
    private double valorOfertado;
    private Date dataOferta;
    private String descricao;

    public ServicoUsuario(){
        setUsuario(new Usuario());
        setServico(new Servico());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getValorOfertado() {
        return valorOfertado;
    }

    public void setValorOfertado(double valorOfertado) {
        this.valorOfertado = valorOfertado;
    }

    public Date getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(Date dataOferta) {
        this.dataOferta = dataOferta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
