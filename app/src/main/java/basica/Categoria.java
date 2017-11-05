package basica;

/**
 * Created by Bruno on 03/11/2017.
 */

public class Categoria {
    private int id;
    private String descricao;
    private String caminhoImagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String imagem) {
        this.caminhoImagem = imagem;
    }
}
