package basica;

import java.io.Serializable;

/**
 * Created by Bruno on 03/11/2017.
 */

public class Servico implements Serializable {
    private int id;
    private Usuario usuario;
    private String descricao;
    private int prazo;
    private Categoria categoria;

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

    @Override
    public String toString(){
        return descricao + " - " + prazo;
    }
}
