package org.example.cineboot;

import javafx.scene.image.ImageView;
import org.example.cineboot.negocio.Sessao;

public class Filme {
    private String titulo;
    private String descricao;
    private String duracao;
    private String classificacao;
    private String imagem;
    private Sessao sessao;


    public Filme(String titulo, String descricao, String imagem, String duracao, String classificacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.duracao = duracao;
        this.classificacao = classificacao;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}
