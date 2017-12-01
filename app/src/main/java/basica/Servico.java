package basica;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bruno on 03/11/2017.
 */

public class Servico implements Serializable {
    private int id;
    private Usuario usuario;
    private String descricao;
    private int prazo;
    private Categoria categoria;
    private String status;
    private Date dataInsercao;
    private Usuario profissional;
    private double valorProfissional;
    //TODO o cliente poderá informar qual o valor está disposto a pagar
    //private Double orçamento;

    //Salvar latitude e longitude para poder exibir no google maps
    private String latitude;
    private String longitude;

    public Servico(){
        usuario = new Usuario();
        categoria = new Categoria();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(Date dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    @Override
    public String toString(){
        return descricao + " - " + prazo;
    }

    public Usuario getProfissional() {
        return profissional;
    }

    public void setProfissional(Usuario profissional) {
        this.profissional = profissional;
    }

    public double getValorProfissional() {
        return valorProfissional;
    }

    public void setValorProfissional(double valorProfissional) {
        this.valorProfissional = valorProfissional;
    }
}
