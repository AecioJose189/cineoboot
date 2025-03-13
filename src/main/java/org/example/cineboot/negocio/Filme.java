package org.example.cineboot.negocio;

import java.util.ArrayList;

public class Filme {
    private String titulo;
    private String descricao;
    private String duracao;
    private String classificacao;
    private String imagem;
    private ArrayList<Sessao> sessoes;
    private Long id;


    public Filme(Long id, String titulo, String descricao, String imagem, String duracao, String classificacao, ArrayList<Sessao> sessoes ) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.sessoes = sessoes;
        this.id = id;
    }

    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(ArrayList<Sessao> sessoes) {
        this.sessoes = sessoes;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

}
