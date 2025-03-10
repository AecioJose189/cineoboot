package org.example.cineboot.negocio;

public abstract class Ingresso {
    protected Filme filme;
    protected Sessao sessao;
    protected double preco;

    public Ingresso(double preco, Filme filme, Sessao sessao) {
        this.preco = preco;
        this.filme = filme;
        this.sessao = sessao;
    }

    public abstract void exibirDetalhes();

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
