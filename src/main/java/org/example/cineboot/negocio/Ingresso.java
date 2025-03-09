package org.example.cineboot.negocio;

public abstract class Ingresso {
    protected double preco;

    public Ingresso(double preco) {
        this.preco = preco;
    }

    public abstract void exibirDetalhes();

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
